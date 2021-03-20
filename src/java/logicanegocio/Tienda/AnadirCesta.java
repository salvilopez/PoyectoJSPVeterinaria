/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Tienda;

import DAO.CestatemporalJpaController;
import DAO.ProductoJpaController;
import DAO.UsuarioJpaController;
import DAO.exceptions.PreexistingEntityException;
import DTO.Cestatemporal;

import DTO.Producto;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
public class AnadirCesta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession();
        Usuario usu = (Usuario) sesion.getAttribute("usu");
        ArrayList<Cestatemporal> miCesta = (ArrayList<Cestatemporal>) sesion.getAttribute("miCesta");

        /* ----- CONTROLADORES -------*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CestatemporalJpaController ctrCesta = new CestatemporalJpaController(emf);
        ProductoJpaController ctrProducto = new ProductoJpaController(emf);

        /* ----- Recuperar Datos -------*/
        String productoAnadido = request.getParameter("productoAnadido");
        int cod_prod = Integer.parseInt(productoAnadido);
        String cantidad = request.getParameter("cantidad");
        int cant = Integer.parseInt(cantidad);

        /* ----- Auxiliares -------*/
        boolean productoExiste = false;
        Cestatemporal cestaEdit = null;
        int totalCantCesta = cant;

        //comprobar si ya existe el producto en la cesta        
        for (Cestatemporal cestatemporal : miCesta) {
            int codProdCesta = cestatemporal.getProducto().getCodProducto();
            if (cod_prod == codProdCesta) {
                productoExiste = true;
                int cantCesta = cestatemporal.getCantidad();
                totalCantCesta += cantCesta;
                cestaEdit = cestatemporal;
                cestaEdit.setCantidad(totalCantCesta);
            }
        }        
          
        Producto productoAñadido = ctrProducto.findProducto(cod_prod);
        boolean disponibilidadExistencias = true;

        //Comprobar Existencias      
        if (totalCantCesta > productoAñadido.getExistenciasProducto()) {
            disponibilidadExistencias = false;
            request.setAttribute("disponibilidadExistencias", disponibilidadExistencias);

        } else {

            if (productoExiste) {
                try {
                    ctrCesta.edit(cestaEdit);
                } catch (Exception ex) {
                    Logger.getLogger(AnadirCesta.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                Producto producto = ctrProducto.findProducto(cod_prod);

                Cestatemporal nuevaCesta = new Cestatemporal();
                nuevaCesta.setCantidad(cant);
                nuevaCesta.setProducto(producto);
                nuevaCesta.setUsuario(usu);

                try {
                    ctrCesta.create(nuevaCesta);
                } catch (Exception ex) {
                    Logger.getLogger(AnadirCesta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

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
