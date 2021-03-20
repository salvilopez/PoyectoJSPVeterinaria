/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Citas;

import DAO.CitaJpaController;
import DAO.UsuarioJpaController;
import DTO.Cita;
import DTO.Usuario;
import bd.Conexion;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import static org.eclipse.persistence.jpa.jpql.utility.CollectionTools.array;

/**
 *
 * @author Salvi
 */
public class traerFecha extends HttpServlet {

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CitaJpaController ctrCita = new CitaJpaController(emf);
        String fechaRecogida=request.getParameter("fecha");
        ArrayList<Cita> listaCitasPorDia = traerFecha.buscarCitasporDia(fechaRecogida, request);
       ArrayList<Integer> citasDisponibles = new ArrayList<Integer>();
       
       citasDisponibles.add(10);
       citasDisponibles.add(11);
       citasDisponibles.add(12);
       citasDisponibles.add(13);
       citasDisponibles.add(17);
       citasDisponibles.add(18);
       citasDisponibles.add(19);
       
       for(int z=0;z<listaCitasPorDia.size();z++){
           
          for(int x=0;x<citasDisponibles.size();x++){
              if(listaCitasPorDia.get(z).getHoraCita()==citasDisponibles.get(x)){
                  citasDisponibles.remove(x);
              }
          }
       
       }
        request.setAttribute("fechaSel", fechaRecogida);
       request.setAttribute("citasDisponibles", citasDisponibles);

       
       
       
       
      

    }
    public int obtenerMes(Date date) {

        if (null == date) {

            return 0;

        } else {

            String formato = "mm";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));

        }

    }
    public int obtenerDia(Date date) {

        if (null == date) {

            return 0;

        } else {

            String formato = "dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));

        }

    }
    public static ArrayList buscarCitasporDia(String fecha,HttpServletRequest request){

        Connection conexion =Conexion.conectar();
        ResultSet rs=null;
        ArrayList<Cita> arrayCita = new ArrayList<Cita>();
        try {
            Statement stmt =conexion.createStatement();
            String sql="select * from cita where fechaCita='"+fecha+"';";
           
            
         
                 rs=stmt.executeQuery(sql);
                while(rs.next()) {
                  Cita cita=  new Cita();
                  cita.setCodCita(rs.getInt("codCita"));
                  cita.setFechaCita(rs.getDate("fechaCita"));
                  cita.setHoraCita(rs.getInt("horacita"));
                  arrayCita.add(cita);
                }
                 
                //hat que hacerle el Result porque el ResultSet no puede subirse a JSP

        } catch (SQLException ex) {
          
        }
        return arrayCita;
        
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
