/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Administracion;

import DAO.*;
import DTO.*;
import bd.MetodosBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author salvi
 */
public class consultaTodo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        String tabla = request.getParameter("tabla");
        if(tabla.equals("usuario")){
            UsuarioJpaController ctrUsuario = new UsuarioJpaController(emf);
            List <Usuario>lista= ctrUsuario.findUsuarioEntities();
            request.setAttribute("lista", lista);
            MetodosBD.ObtenerCabecera(request,"usuario");
        }else if(tabla.equals("cita")){
            CitaJpaController crtCita = new CitaJpaController(emf);
            List <Cita>lista= crtCita.findCitaEntities();
            request.setAttribute("lista", lista);  
            MetodosBD.ObtenerCabecera(request,"cita");
        }else if(tabla.equals("mascota")){
            MascotaJpaController crtMascota = new MascotaJpaController(emf);
            List <Mascota>lista= crtMascota.findMascotaEntities();
            request.setAttribute("lista", lista); 
            MetodosBD.ObtenerCabecera(request,"mascota");
        }else if(tabla.equals("producto")){
            ProductoJpaController crtProducto = new ProductoJpaController(emf);
            List <Producto>lista= crtProducto.findProductoEntities();
            request.setAttribute("lista", lista); 
            MetodosBD.ObtenerCabecera(request,"producto");            
        }else if(tabla.equals("historialpedido")){
            HistorialpedidoJpaController ctrHistorialPedido= new HistorialpedidoJpaController(emf);
            List <Historialpedido>lista= ctrHistorialPedido.findHistorialpedidoEntities();
            request.setAttribute("lista", lista);
            MetodosBD.ObtenerCabecera(request,"historialpedido");
        } 
        else if(tabla.equals("factura")){
            FacturaJpaController ctrFactura= new FacturaJpaController(emf);
            List <Factura>lista= ctrFactura.findFacturaEntities();
            request.setAttribute("lista", lista);
            MetodosBD.ObtenerCabecera(request,"factura");
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
