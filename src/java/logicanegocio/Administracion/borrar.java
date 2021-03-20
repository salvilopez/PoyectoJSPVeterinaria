/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Administracion;

import DAO.CestatemporalJpaController;
import DAO.CitaJpaController;
import DAO.FacturaJpaController;
import DAO.HistorialpedidoJpaController;
import DAO.MascotaJpaController;
import DAO.ProductoJpaController;
import DAO.UsuarioJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Cestatemporal;
import DTO.Historialpedido;
import DTO.HistorialpedidoPK;
import java.io.IOException;
import java.text.ParseException;
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

/**
 *
 * @author salvi
 */
public class borrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        String botonBorrar = request.getParameter("botonBorrar");
        String mensaje = "";
        switch (botonBorrar) {
            case "borrar mascota":
                MascotaJpaController crtMascota = new MascotaJpaController(emf);
                String cod = request.getParameter("codMascota");
                int codMascota = Integer.parseInt(cod);

                try {
                    crtMascota.destroy(codMascota);
                    mensaje = "MJascota borrada correctamente";
                } catch (NonexistentEntityException ex) {
                    mensaje = "Fallo al borrar Mascota";
                }

                break;
            case "borrar usuario":
                UsuarioJpaController ctrUsuario = new UsuarioJpaController(emf);
                String coU = request.getParameter("codUsuario");
                int codUsuario = Integer.parseInt(coU);
                
                //PRIMERO BORRAMOS EL HISTORIAL CON LOS USUARIO
                HistorialpedidoJpaController ctrHistorialpedido = new HistorialpedidoJpaController(emf);
                List<Historialpedido> histBorrar = ctrHistorialpedido.findHistorialpedidoEntities();
                for (Historialpedido historial : histBorrar) {
                    int aux = historial.getUsuario().getCodUsuario();
                    if (aux == codUsuario) {
                        try {
                            ctrHistorialpedido.destroy(historial.getHistorialpedidoPK());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //TAMBIEN BORRAMOS LOS USUARIOS DE LA CESTA
                CestatemporalJpaController ctrCesta = new CestatemporalJpaController(emf);
                List<Cestatemporal> cestaBorrar = ctrCesta.findCestatemporalEntities();
                for (Cestatemporal cesta : cestaBorrar) {
                    if (cesta.getUsuario().getCodUsuario()==codUsuario) {
                        try {
                            ctrCesta.destroy(cesta.getCestatemporalPK());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                try {
                    ctrUsuario.destroy(codUsuario);
                    mensaje = "Usuario borrado correctamente";
                } catch (IllegalOrphanException ex) {
                    ex.getMessage();
                } catch (NonexistentEntityException ex) {
                    mensaje = "Fallo al borrar Usuario";
                }

                break;
            case "borrar producto":

                ProductoJpaController ctrProducto = new ProductoJpaController(emf);
                String coP = request.getParameter("codProducto");
                int codProducto = Integer.parseInt(coP);

                //PRIMERO BORRAMOS EL HISTORIAL CON LOS PRODUCTOS
                ctrHistorialpedido = new HistorialpedidoJpaController(emf);
                histBorrar = ctrHistorialpedido.findHistorialpedidoEntities();
                for (Historialpedido historial : histBorrar) {
                    if (historial.getProducto().getCodProducto() == codProducto) {
                        try {
                            ctrHistorialpedido.destroy(historial.getHistorialpedidoPK());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //TAMBIEN BORRAMOS LOS PRODUCTOS DE LA CESTA
                ctrCesta = new CestatemporalJpaController(emf);
                cestaBorrar = ctrCesta.findCestatemporalEntities();
                for (Cestatemporal cesta : cestaBorrar) {
                    if (cesta.getProducto().getCodProducto() == codProducto) {
                        try {
                            ctrCesta.destroy(cesta.getCestatemporalPK());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                try {
                    ctrProducto.destroy(codProducto);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case "borrar cita":
                CitaJpaController ctrCita = new CitaJpaController(emf);
                String coC = request.getParameter("codCita");
                int codCita = Integer.parseInt(coC);

                try {
                    ctrCita.destroy(codCita);
                    mensaje = "Cita borrada correctamente";
                } catch (NonexistentEntityException ex) {
                    mensaje = "Fallo al borrar la cita";
                }

                break;
            case "borrar historialpedido":
                ctrHistorialpedido = new HistorialpedidoJpaController(emf);

                String coPedPk = request.getParameter("codPedidoPk");
                int codPedidoPk = Integer.parseInt(coPedPk);

                String codUsuPk = request.getParameter("codUsuarioPk");
                int codUsuarioPk = Integer.parseInt(codUsuPk);

                String coProdPk = request.getParameter("codProductoPk");
                int codProductoPk = Integer.parseInt(coProdPk);

                HistorialpedidoPK hpPk = new HistorialpedidoPK();
                hpPk.setCodPedido(codPedidoPk);
                hpPk.setCodProducto(codProductoPk);
                hpPk.setCodUsuario(codUsuarioPk);

                 {
                    try {
                        ctrHistorialpedido.destroy(hpPk);
                        mensaje = "pedido borrado correctamente";
                    } catch (NonexistentEntityException ex) {
                        mensaje = "Fallo al borrar el pedido";
                    }
                }

                break;
            case "borrar factura":
                FacturaJpaController ctrFactura = new FacturaJpaController(emf);
                String coPf = request.getParameter("codPedidoF");
                int codPedidoF = Integer.parseInt(coPf);

                try {
                    ctrFactura.destroy(codPedidoF);
                    mensaje = "Factura borrada correctamente";
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(borrar.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            default:
                mensaje = "Fallo en el borrado";
                break;
        }
        request.setAttribute("mensaje", mensaje);
    }

    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
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
