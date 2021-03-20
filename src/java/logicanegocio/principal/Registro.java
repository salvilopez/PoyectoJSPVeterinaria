package logicanegocio.principal;

import DAO.UsuarioJpaController;
import DTO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mail.mail;

/**
 *
 * @author Salvi
 */
public class Registro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        UsuarioJpaController ctrUsuario = new UsuarioJpaController(emf);
        String nombreUsuario = request.getParameter("nombreUsuarioRegistro");
        String pass = request.getParameter("passRegistro");
        String nombre = request.getParameter("nombreRegistro");
        String apellidos = request.getParameter("apellidosRegistro");
        String email = request.getParameter("emailRegistro");
        String tel = request.getParameter("telefonoRegistro");
        int telefono = Integer.parseInt(tel);
        String direccion = request.getParameter("direccionRegistro");
        Usuario usu = new Usuario();
        Date fechaRegistro = new Date();
        if (nombreUsuario != null && pass != null && nombre != null && apellidos != null && email != null && tel != null && direccion != null) {
            usu.setNombreUsuario(nombreUsuario);
            usu.setPasswordUsuario(pass);
            usu.setNombre(nombre);
            usu.setApellidos(apellidos);
            usu.setCorreoUsuario(email);
            usu.setTelefonoUsuario(telefono);
            usu.setDireccion(direccion);
            usu.setFechaRegistroUsuario(fechaRegistro);
            ctrUsuario.create(usu);
            mail.mandarCorreo(email, "Su Registro En Happy pets ha sido realizado con exito", "Le adjuntamos sus datos de acceso \n Usuario: "+nombreUsuario+"\n Contraseña: "+pass, request);
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
