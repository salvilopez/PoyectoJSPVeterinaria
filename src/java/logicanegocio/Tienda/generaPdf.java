/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicanegocio.Tienda;

import DAO.CestatemporalJpaController;
import DAO.FacturaJpaController;
import DAO.HistorialpedidoJpaController;
import DAO.ProductoJpaController;
import DTO.Cestatemporal;
import DTO.Factura;
import DTO.Producto;
import DTO.Usuario;
import bd.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvi
 */
@WebServlet(urlPatterns = {"/generaPDF"})
public class generaPdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        HttpSession sesion = request.getSession();
        Usuario usuR = (Usuario) sesion.getAttribute("usu");
        ArrayList<Cestatemporal> miCesta = (ArrayList<Cestatemporal>) sesion.getAttribute("miCesta");        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicaVeterinariaPU");
        CestatemporalJpaController crtCesta = new CestatemporalJpaController(emf);
        ProductoJpaController crtProd = new ProductoJpaController(emf);
        FacturaJpaController ctrFac = new FacturaJpaController(emf);
        List<Factura> listaFac = ctrFac.findFacturaEntities();
        String cadena = String.valueOf(listaFac.size());
        OutputStream out = response.getOutputStream();
        String ruta = usuR.getNombreUsuario() + "_" + cadena;

        try {

            try {
                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/pdf/" + ruta + ".pdf");

                Document documento = new Document();
                PdfWriter.getInstance(documento, new FileOutputStream(new File(path)));
                PdfWriter.getInstance(documento, out);
                documento.open();
                Paragraph par1 = new Paragraph();
                context = request.getServletContext();
                String path2 = context.getRealPath("/images/logo.png");
                Image imagenes = Image.getInstance(path2);
                imagenes.setAlignment(Element.ALIGN_LEFT);
                imagenes.scaleToFit(200, 200);
                documento.add(imagenes);
                Font fuentetitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLUE);
                par1.add(new Phrase("Factura de " + usuR.getNombre(), fuentetitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                par1.add(new Phrase(Chunk.NEWLINE));
                documento.add(par1);
                PdfPTable tabla = new PdfPTable(5);
                Font fuentetabla = new Font(Font.FontFamily.HELVETICA, 10);
                PdfPCell celda1 = new PdfPCell(new Paragraph("ImagenProducto", fuentetabla));
                PdfPCell celda2 = new PdfPCell(new Paragraph("NombreProducto", fuentetabla));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Cantidad", fuentetabla));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Precio", fuentetabla));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Total", fuentetabla));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                tabla.addCell(celda5);
                List<Producto> listaProd = new ArrayList();
                List<Integer> listaCantidad = new ArrayList();
                double total = 0;
                for (int i = 0; i < miCesta.size(); i++) {
                    Producto prod = miCesta.get(i).getProducto();
                    listaCantidad.add(miCesta.get(i).getCantidad());
                    listaProd.add(prod);
                }
                for (int m = 0; m < listaProd.size(); m++) {
                    PdfPCell celda8 = new PdfPCell(new Paragraph(listaProd.get(m).getImagenProducto(), fuentetabla));
                    tabla.addCell(celda8);
                    celda8 = null;
                    celda8 = new PdfPCell(new Paragraph(listaProd.get(m).getNombreProducto(), fuentetabla));
                    tabla.addCell(celda8);
                    celda8 = null;
                    String sSelectivityRatef = String.valueOf(listaCantidad.get(m));
                    celda8 = new PdfPCell(new Paragraph(sSelectivityRatef, fuentetabla));
                    tabla.addCell(celda8);
                    celda8 = null;

                    String sSelectivityRate = String.valueOf(listaProd.get(m).getPrecioProducto());
                    celda8 = new PdfPCell(new Paragraph(sSelectivityRate + "€", fuentetabla));
                    tabla.addCell(celda8);
                    celda8 = null;
                    total = total + (listaProd.get(m).getPrecioProducto() * listaCantidad.get(m));
                    String sSelectivityRate1 = String.valueOf(listaProd.get(m).getPrecioProducto() * listaCantidad.get(m));
                    celda8 = new PdfPCell(new Paragraph(sSelectivityRate1 + "€", fuentetabla));
                    tabla.addCell(celda8);
                    celda8 = null;

                }

                documento.add(tabla);
                String sSelectivityRate2 = String.valueOf(total);
                Paragraph celda8 = new Paragraph("Total  " + sSelectivityRate2 + "€");
                celda8.setAlignment(Element.ALIGN_CENTER);
                Paragraph celda11 = new Paragraph(" ");
                Paragraph celda9 = new Paragraph("Direccion: " + usuR.getDireccion());
                celda9.setAlignment(Element.ALIGN_CENTER);
                Paragraph celda12 = new Paragraph("Su pedido llegara en 24/48 horas");
                celda12.setAlignment(Element.ALIGN_CENTER);
                Paragraph celda10 = new Paragraph("MUCHAS GRACIAS POR SU COMPRA QUE TENGA UN MARAVILLOSO DIA!!");
                celda10.setAlignment(Element.ALIGN_CENTER);
                documento.add(celda8);
                documento.add(celda11);
                documento.add(celda12);
                documento.add(celda9);
                documento.add(celda10);
                /*  //añadimos la imagen
              Image imagenes = Image.getInstance("C:\\Users\\salvi\\Documents\\NetBeansProjects\\EjemploPdf\\web\\imagenes\\pdf.jpg");
              imagenes.setAlignment(Element.ALIGN_CENTER);
              imagenes.scaleToFit(100,100);
              documento.add(imagenes);             
                 */
                documento.close();

            } catch (Exception ex) {
                ex.getMessage();
            }

        } finally {
            out.close();
        }

        mandarCorreo(usuR.getCorreoUsuario(), "Pedido realizado correctamente", "Su pedido se realizo Correctamente\n debera llegar entre 24/48 horas \n muchas gracias por su confianza", request, ruta);

    }

    public static void mandarCorreo(String CorreoDestino, String Asunto, String Cuerpo, HttpServletRequest request, String ruta) {
        // El correo gmail de envío
        String correoEnvia = "happypetsronda@gmail.com";
        String claveCorreo = "victorsalvi";

        // La configuración para enviar correo
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        int aviso = 0;
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Clinica HappyPets"));

            // Los destinatarios
            InternetAddress[] internetAddresses = {new InternetAddress(CorreoDestino)};
//		     new InternetAddress("correo2@gmail.com"),
//		     new InternetAddress("correo3@gmail.com") };

            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Agregar el asunto al correo
            mimeMessage.setSubject(Asunto);

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(Cuerpo);

            MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/pdf/" + ruta + ".pdf");
            mimeBodyPartAdjunto.attachFile(path);

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPartAdjunto);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            aviso = 1;
        }
        if (aviso == 0) {
            String resultado = "Correo Enviado correctamente";
            request.setAttribute(resultado, "resultado");
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
