<%-- 
    Document   : administracion
    Created on : 08-feb-2019, 0:17:27
    Author     : Salvi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/cssAdministracion/administracion.css" media="screen" />
        <title>Mantenimiento</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <style>
            #search{

                margin: 0 auto;
                margin-top: 50px;
                margin-bottom: 50px;
                width:20%
            }
            #mytable{
                width: 100%;
            }
        </style>
        <script>
            // Write on keyup event of keyword input element
            $(document).ready(function () {
                $("#search").keyup(function () {
                    _this = this;
                    // Show only matching TR, hide rest of them
                    $.each($("#mytable tbody tr"), function () {
                        if ($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1)
                            $(this).hide();
                        else
                            $(this).show();
                    });
                });
            });
        </script>
    </head>
    <body>


    <jsp:useBean id="usuario" class="DTO.Usuario" scope="request">
        <jsp:setProperty name="usuario" property = "*" />
    
    </jsp:useBean>
    
        
        
        <div id="logo">
            <form action="index.jsp">
                <input type="image" name="imagenLogo" src="images/logo.png"/>

            </form>

        </div>
        <c:if test="${!empty param.botonInsertar}">
            <jsp:include page="insertar"/>
            <c:out value="${requestScope.mensaje}"/>
            <p>${requestScope.mensaje}</p>
        </c:if> 

        <c:if test="${!empty param.botonBorrar}">
            <jsp:include page="borrar"/>
            <c:out value="${requestScope.mensaje}"/>
        </c:if> 
        <c:if test="${!empty param.tabla}">
            <jsp:include page="consultaTodo"/>
        </c:if>

        <h1>Bienvenido ${sessionScope.usu.nombreUsuario}</h1>


        <div id="botones">        
            <form action="administracion.jsp">
                <input type="submit" value="usuario" name="tabla"/>
            </form> 
            <form action="administracion.jsp">
                <input type="submit" value="mascota" name="tabla"/>
            </form> 
            <form action="administracion.jsp">
                <input type="submit" value="cita" name="tabla"/>
            </form> 
            <form action="administracion.jsp">
                <input type="submit" value="historialpedido" name="tabla"/>
            </form> 
            <form action="administracion.jsp">
                <input type="submit" value="producto" name="tabla"/>
            </form> 
            <form action="administracion.jsp">
                <input type="submit" value="factura" name="tabla"/>
            </form>                   
        </div>


        <div id="tablaDatos"> 
            <div class="form-group">
                <input type="text" class="form-control pull-right" id="search" placeholder="Buscador">
            </div>
            <c:if test="${!empty param.tabla}"> 
                <table class="table-bordered table pull-right" id="mytable" cellspacing="0" border="2">

                    <tr>
                        <c:forEach var="reg4" items="${requestScope.columName}">

                            <th> <c:out value="${reg4}"/></th>

                        </c:forEach>        
                    </tr>

                    <c:choose>

                        <c:when test = "${param.tabla.equals('mascota')}">
                            <form action="administracion.jsp" method="post"  enctype="MULTIPART/FORM-DATA">
                                <tr>
                                    <td></td>
                                    <td><input type="text" name="nombreMascota"/></td>
                                    <td><input type="date" name="fechaNacimientoMascota"/></td>
                                    <td><input type="text" name="genero"/></td>
                                    <td><input type="text" name="especie"/></td>
                                    <td><input type="text" name="raza"/></td>
                                    <td><input type="text" name="descripcionMascota"/></td>
                                    <td><input type="file" name="imagencitaMascota"/></td>

                                    <jsp:include page="traerUsuarios"/>
                                    <td><select name="codUsuario">
                                            <c:forEach var="usua" items="${requestScope.listaUsuarios}">
                                                <option value="${usua.codUsuario}">${usua.nombreUsuario}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="submit" name="botonInsertar" value="InsertarMascota"</td>
                            </form>

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>

                                    <tr>
                                <form action="administracion.jsp">

                                    <td>${objeto.codMascota}</td>
                                    <td>${objeto.nombreMascota}</td>
                                    <td>${objeto.fechaNacimientoMascota}</td>
                                    <td>${objeto.genero}</td>
                                    <td>${objeto.especie}</td>
                                    <td>${objeto.raza}</td>
                                    <td>${objeto.descripcionMascota}</td>
                                    <td><img src="images/animales/${objeto.imagenMascota}"/></td>
                                    <td>${objeto.codUsuario.codUsuario}</td>
                                    <td><input type="submit" name="boton" value="cambiar mascota"/></td>  
                                </form>                                    
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codMascota" value="${objeto.codMascota}"/>
                                    <td><input type="submit" name="botonBorrar" value="borrar mascota"/></td>
                                </form>

                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>

                        <c:when test = "${param.tabla.equals('usuario')}">

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>
                                    <tr>
                                <form action="administracion.jsp">
                                    <td>${objeto.codUsuario}</td>
                                    <td>${objeto.nombreUsuario}</td>
                                    <td>${objeto.passwordUsuario}</td>
                                    <td>${objeto.nombre}</td>
                                    <td>${objeto.apellidos}</td>
                                    <td>${objeto.correoUsuario}</td>
                                    <td>${objeto.fechaRegistroUsuario}</td>
                                    <td>${objeto.telefonoUsuario}</td>
                                    <td>${objeto.direccion}</td>
                                    <td><input type="submit" name="boton" value="cambiar usuario"/></td>
                                </form>                                     
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codUsuario" value="${objeto.codUsuario}"/>
                                    <td><input type="submit" name="botonBorrar" value="borrar usuario"/></td>
                                </form>

                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>

                        <c:when test = "${param.tabla.equals('producto')}">

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>
                                    <tr>
                                <form action="administracion.jsp">
                                    <td>${objeto.codProducto}</td>
                                    <td>${objeto.nombreProducto}</td>
                                    <td>${objeto.precioProducto}</td>
                                    <td>${objeto.existenciasProducto}</td>
                                    <td>${objeto.tipoProducto}</td>
                                    <td>${objeto.descripcionProducto}</td>

                                    <td><img src="images/productos/${objeto.imagenProducto}"/></td>
                                    <td><input type="submit" name="boton" value="cambiar producto"/></td>
                                </form>                                   
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codProducto" value="${objeto.codProducto}"/>
                                    <td><input type="submit" name="botonBorrar" value="borrar producto"/></td>

                                </form>
                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>

                        <c:when test = "${param.tabla.equals('cita')}">

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>
                                    <tr>
                                <form action="administracion.jsp">
                                    <td>${objeto.codCita}</td>
                                    <td>${objeto.fechaCita}</td>
                                    <td>${objeto.horaCita}</td>
                                    <td>${objeto.tipoCita}</td>
                                    <td>${objeto.codUsuario.codUsuario}</td>
                                    <td><input type="submit" name="boton" value="cambiar cita"/></td>
                                </form>
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codCita" value="${objeto.codCita}"/>                       
                                    <td><input type="submit" name="botonBorrar" value="borrar cita"/></td>
                                </form>                            
                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>

                        <c:when test = "${param.tabla.equals('historialpedido')}">

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>
                                    <tr>
                                <form action="administracion.jsp">
                                    <td>${objeto.historialpedidoPK.codPedido}</td>
                                    <td>${objeto.usuario.codUsuario}</td>
                                    <td>${objeto.producto.codProducto}</td>
                                    <td>${objeto.cantidad}</td>

                                </form>  
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codPedidoPk" value="${objeto.historialpedidoPK.codPedido}"/>
                                    <input type="hidden" name="codUsuarioPk" value="${objeto.historialpedidoPK.codUsuario}"/>
                                    <input type="hidden" name="codProductoPk" value="${objeto.historialpedidoPK.codProducto}"/>
                                    <td><input type="submit" name="botonBorrar" value="borrar historialpedido"/></td>
                                </form>                                     
                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>   
                        <c:when test = "${param.tabla.equals('factura')}">

                            <c:forEach var="objeto" items="${requestScope.lista}">
                                <tbody>
                                    <tr>
                                <form action="administracion.jsp">
                                    <td>${objeto.codPedido}</td>
                                    <td>${objeto.codUsuario}</td>
                                    <td>${objeto.fecha}</td>
                                    <td>${objeto.importeTotal}</td>

                                </form>  
                                <form action="administracion.jsp">
                                    <input type="hidden" name="codPedidoF" value="${objeto.codPedido}"/> 
                                    <input type="hidden" name="codUsuarioF" value="${objeto.codUsuario}"/>   
                                    <td><input type="submit" name="botonBorrar" value="borrar factura"/></td>
                                </form>  
                                </tr>
                                </tbody>
                            </c:forEach> 

                        </c:when>  
                        <c:otherwise>

                        </c:otherwise>


                    </c:choose>        
                </table>
            </c:if>













        </div>

    </body>
</html>
