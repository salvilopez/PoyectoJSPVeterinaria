/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Tienda;

import DTO.Producto;
import bd.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salvi
 */
public class Categorias extends HttpServlet {

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
        String categoria = request.getParameter("botonCategoria");

        switch (categoria) {
            case "Alimentacion":
                Categorias.traerProdPorCategoria(request, "Alimentacion");
                break;
            case "Nutricion":
                Categorias.traerProdPorCategoria(request, "Alimentacion");
                break;
            case "Antiparasitario":
                Categorias.traerProdPorCategoria(request, "Antiparasitario");
                break;
            case "Antiparasitic":
                Categorias.traerProdPorCategoria(request, "Antiparasitario");
                break;
            case "Higiene":
                Categorias.traerProdPorCategoria(request, "Higiene");
                break;
            case "Hygiene":
                Categorias.traerProdPorCategoria(request, "Higiene");
                break;
            case "Accesorio":
                Categorias.traerProdPorCategoria(request, "Accesorio");
                break;
            case "Accesory":
                Categorias.traerProdPorCategoria(request, "Accesorio");
                break;
            case "Comedero":
                Categorias.traerProdPorCategoria(request, "Comedero");
                break;
            case "Feeder":
                Categorias.traerProdPorCategoria(request, "Comedero");
                break;

            default:
            // code block
        }

    }

    public static void traerProdPorCategoria(HttpServletRequest request, String categoria) {
        Connection conexion = Conexion.conectar();
        List<Producto> listaProductos = new ArrayList();

        try {
            Statement stmt = conexion.createStatement();
            boolean resultado = stmt.execute("select * from producto where tipoProducto = '" + categoria + "';");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Producto Producto = new Producto();
                Producto.setNombreProducto(rs.getString("nombreProducto"));
                Producto.setCodProducto(rs.getInt("codProducto"));
                Producto.setPrecioProducto(rs.getFloat("precioProducto"));
                Producto.setExistenciasProducto(rs.getInt("existenciasProducto"));
                Producto.setTipoProducto(rs.getString("tipoProducto"));
                Producto.setDescripcionProducto(rs.getString("descripcionProducto"));
                Producto.setImagenProducto(rs.getString("imagenProducto"));
                listaProductos.add(Producto);

            }

        } catch (SQLException ex) {

        }
        request.setAttribute("listaProductos", listaProductos);

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
