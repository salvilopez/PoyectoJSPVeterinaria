/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Citas;

import DAO.CitaJpaController;
import DTO.Cita;
import DTO.Usuario;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.mail;

/**
 *
 * @author Salvi
 */
public class hacerReserva extends HttpServlet {

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
        String tipoCita=request.getParameter("tipoCita");
        String fecha=request.getParameter("fecha");
        Date fechaR=Date.valueOf(fecha);
        String horaSeleccionada=request.getParameter("horaSeleccionada");
        int hora=Integer.parseInt(horaSeleccionada);
        HttpSession sesion = request.getSession();
        Usuario usu=(Usuario) sesion.getAttribute("usu"); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CitaJpaController ctrCita = new CitaJpaController(emf);
        Cita cita =new Cita();
        cita.setCodUsuario(usu);
        
        cita.setFechaCita(fechaR);
        cita.setHoraCita(hora);
        cita.setTipoCita(tipoCita);
        ctrCita.create(cita);
          mail.mandarCorreo(usu.getCorreoUsuario(), "Su cita se Registro correctamente", "Datos de su Cita: \n Dia : "+cita.getFechaCita()+" \n Hora :  "+cita.getHoraCita()+":00\n Motivo : "+cita.getTipoCita(), request);
        
        
        
        
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
