/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.principal;

import DAO.UsuarioJpaController;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author salvi
 */
public class comprobarUsuario extends HttpServlet {

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
    String mensaje="";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        UsuarioJpaController ctrCliente = new UsuarioJpaController(emf);
        String nombreUsuario=request.getParameter("nombreUsuario");
        String passwordUsuario=request.getParameter("passwordUsuario");
        int codUsuarioEncontrado=0;
        List <Usuario> lista =ctrCliente.findUsuarioEntities();
        
        for(int x=0;x<lista.size();x++){
            
            if(lista.get(x).getNombreUsuario().equals(nombreUsuario)){
            
                if(lista.get(x).getPasswordUsuario().equals(passwordUsuario)){
                     codUsuarioEncontrado=lista.get(x).getCodUsuario();
                }else{
                
                       mensaje="Contraseña Incorrecta";
                }
            }           
        }
        if(codUsuarioEncontrado!=0){
             HttpSession sesion = request.getSession();
             Usuario usu=ctrCliente.findUsuario(codUsuarioEncontrado);
             
             
             if(nombreUsuario.equals("admin")&&usu.getCodUsuario()==1){
                 request.setAttribute("configuracion", "si");
             }
  
             sesion.setAttribute("usu", usu);
        
        }else{
        
        mensaje="No existe ese Usuario";
        
        
        }
        request.setAttribute("mensaje", mensaje);

    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
