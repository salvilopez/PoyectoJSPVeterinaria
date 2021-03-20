/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Citas;

import DAO.CitaJpaController;
import DTO.Cita;
import DTO.Usuario;
import bd.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Salvi
 */
public class traerCitas extends HttpServlet {

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
        
      traerProdPorCategoria(request);
             
 
    }
public static void traerProdPorCategoria(HttpServletRequest request) {
        Connection conexion = Conexion.conectar();
        List<Cita> listaCitas = new ArrayList();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CitaJpaController ctrCita = new CitaJpaController(emf);
        HttpSession sesion = request.getSession();
        Usuario usu=(Usuario) sesion.getAttribute("usu");
        try {
            Statement stmt = conexion.createStatement();
            boolean resultado = stmt.execute("select * from cita where codUsuario = " + usu.getCodUsuario());

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setCodCita(rs.getInt("codCita"));
                cita.setCodUsuario(usu);
                cita.setFechaCita(rs.getDate("fechaCita"));
                cita.setHoraCita(rs.getInt("horaCita"));
                cita.setTipoCita(rs.getString("tipoCita"));
                 
                listaCitas.add(cita);

            }

        } catch (SQLException ex) {

        }
        request.setAttribute("listaCitas", listaCitas);

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
