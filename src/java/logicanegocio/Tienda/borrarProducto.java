/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Tienda;

import DAO.CestatemporalJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Cestatemporal;
import DTO.CestatemporalPK;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class borrarProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Usuario usu = (Usuario) sesion.getAttribute("usu");
        String cod = request.getParameter("codProducto");
        int codProducto = Integer.parseInt(cod);
        
        //ArrayList<Cestatemporal> miCesta = (ArrayList<Cestatemporal>) sesion.getAttribute("miCesta");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CestatemporalJpaController ctrCesta = new CestatemporalJpaController(emf);

        CestatemporalPK cPK = new CestatemporalPK();
        cPK.setCodProducto(codProducto);
        cPK.setCodUsuario(usu.getCodUsuario());
        
        try {
            ctrCesta.destroy(cPK);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(borrarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("tienda.jsp");

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
