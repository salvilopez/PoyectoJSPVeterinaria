/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Tienda;

import DAO.CestatemporalJpaController;
import DAO.FacturaJpaController;
import DAO.HistorialpedidoJpaController;
import DAO.ProductoJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Cestatemporal;
import DTO.CestatemporalPK;
import DTO.Factura;
import DTO.Historialpedido;
import DTO.HistorialpedidoPK;
import DTO.Producto;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usuario
 */
public class FinalizarCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession();
        Usuario usu = (Usuario) sesion.getAttribute("usu");
        ArrayList<Cestatemporal> miCesta = (ArrayList<Cestatemporal>) sesion.getAttribute("miCesta");

        String importe = request.getParameter("importeTotal");
        double importeTotal = Double.parseDouble(importe);
        double importeDosDecimales = ((double) Math.round(importeTotal * 100d) / 100d);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");

        /* ------------ CONTROLADORES ---------------*/
        HistorialpedidoJpaController ctrHistorial = new HistorialpedidoJpaController(emf);
        CestatemporalJpaController ctrCesta = new CestatemporalJpaController(emf);
        FacturaJpaController ctrFactura = new FacturaJpaController(emf);
        ProductoJpaController ctrProd = new ProductoJpaController(emf);

        /* ------------ CREAR FACTURA ---------------*/
        Date fecha = new Date();

        Factura factura = new Factura();
        factura.setCodPedido(null);
        factura.setCodUsuario(usu.getCodUsuario());
        factura.setFecha(fecha);
        factura.setHistorialpedidoList(ctrHistorial.findHistorialpedidoEntities());
        factura.setImporteTotal(importeDosDecimales);

        try {
            ctrFactura.create(factura);
        } catch (Exception ex) {
            Logger.getLogger(FinalizarCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* ------------ CREAR HISTORIALPEDIDO ---------------*/
        List<Cestatemporal> productosCesta = ctrCesta.findCestatemporalEntities();
        Historialpedido historial = new Historialpedido();

        int codUsu = usu.getCodUsuario();
        for (Cestatemporal pCesta : productosCesta) {

            int codUsuCesta = pCesta.getUsuario().getCodUsuario();
            if (codUsu == codUsuCesta) {

                HistorialpedidoPK hPK = new HistorialpedidoPK();
                hPK.setCodPedido(factura.getCodPedido());
                hPK.setCodProducto(pCesta.getProducto().getCodProducto());
                hPK.setCodUsuario(pCesta.getUsuario().getCodUsuario());

                historial.setCantidad(pCesta.getCantidad());
                historial.setFactura(factura);
                historial.setHistorialpedidoPK(hPK);
                historial.setProducto(pCesta.getProducto());
                historial.setUsuario(pCesta.getUsuario());

                try {
                    ctrHistorial.create(historial);
                } catch (Exception ex) {
                    Logger.getLogger(FinalizarCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /* ------------ ACTUALIZAR STOCK ---------------*/
        Producto productoEdit = null;
        
        for (Cestatemporal cestatemporal : miCesta) {
            int codProdCesta = cestatemporal.getProducto().getCodProducto();

            productoEdit = ctrProd.findProducto(codProdCesta);
            
            int existencias = productoEdit.getExistenciasProducto();
            int existenciasActualizado = existencias - cestatemporal.getCantidad();
            
            productoEdit.setExistenciasProducto(existenciasActualizado);

            try {
                ctrProd.edit(productoEdit);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FinalizarCarrito.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FinalizarCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        /* ------------ VACIAR CESTA ---------------*/
        CestatemporalPK ctPK = new CestatemporalPK();
        for (Cestatemporal cestatemporal : miCesta) {
            int codUsuCesta = cestatemporal.getUsuario().getCodUsuario();
            if (codUsu == codUsuCesta) {

                ctPK.setCodProducto(cestatemporal.getProducto().getCodProducto());
                ctPK.setCodUsuario(cestatemporal.getUsuario().getCodUsuario());
                try {
                    ctrCesta.destroy(ctPK);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FinalizarCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//class Finalizar Carrito

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
