/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.principal;

import DAO.MascotaJpaController;
import DTO.Mascota;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class imagenMascota extends HttpServlet {

    /*Este metodo se trae todas  las mascotas y las divide en 3 arraylist para divir las mascotas para el slyder de 
    imagenes*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        MascotaJpaController ctrMascota = new MascotaJpaController(emf);
        List<Mascota> listamascotas = ctrMascota.findMascotaEntities();
        ArrayList<Mascota> listaMascota1 = new ArrayList<Mascota>();
        ArrayList<Mascota> listaMascota2 = new ArrayList<Mascota>();
        Random generadorAleatorios = new Random();
        int aleatorio;
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota1.add(listamascotas.get(aleatorio));
            listamascotas.remove(aleatorio);
            
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota1.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota1.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota1.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
            
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota2.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota2.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota2.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
               aleatorio=0+generadorAleatorios.nextInt(listamascotas.size());
            listaMascota2.add(listamascotas.get(aleatorio));
                listamascotas.remove(aleatorio);
        

        
        request.setAttribute("listaMascota1", listaMascota1);
        request.setAttribute("listaMascota2", listaMascota2);

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
