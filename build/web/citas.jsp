<%-- 
    Document   : citas
    Created on : 27-feb-2019, 11:48:58
    Author     : Salvi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:if test="${!empty param.language}">
            <fmt:setLocale value="${param.language}"/>
            <c:set var="language" scope="session" value="${param.language}"/>
        </c:if>
        <fmt:setBundle basename="internacionalizacion.mensajes" />
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2.0, minimum-scale=1.0,  user-scalable=yes">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Pets - Citas</title>
        <link rel="stylesheet" type="text/css" href="css/cssCitas/estiloCitas.css">
           <link rel="stylesheet" type="text/css" href="css/cssCitas/estiloImprimir.css" media="print" />
    </head>
    <body>
        <c:if test="${!empty param.codCita}">
            <jsp:include page="borrarCita"/>
        </c:if>  

        <c:if test="${!empty param.botonRegistro}">
            <jsp:include page="Registro"/>
        </c:if>  
        <c:if test="${!empty param.descon}">
            <jsp:include page="desconectarSesion"/>
            <c:redirect url="index.jsp"/>
        </c:if>  
        <c:if test="${!empty param.botonfecha}">
            <jsp:include page="traerFecha"/>           
        </c:if>
        <c:if test="${!empty param.botonReserva}">
            <jsp:include page="hacerReserva"/>           
        </c:if>
                <c:if test="${!empty sessionScope.usu}">
            <jsp:include page="traerCitas"/>
        </c:if> 
        <div id="contenedor">
            <div class="row bordebotuonblue m15 mencabe">
                <div class="col-12 col-lg-3 infoContacto text-center">
                    <span><fmt:message key="tUrgencias"/> 651-526-682</span>
                </div>
                <div class="col-12 col-lg-3 infoContacto text-center">
                    <span><fmt:message key="direccion"/></span>
                </div>
                <div class="col-12 col-lg-3 infoContacto text-center">
                    <span>happypetsronda@gmail.com</span>
                </div> 
                <div class="container-banderas col-lg-3 text-center col-12">
                    <form action="citas.jsp">
                        <input type="hidden" name="language" value="en">
                        <input type="image" src="images/idioma/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                    </form>

                    <form action="citas.jsp">
                        <input type="hidden" name="language" value="es">
                        <input type="image" src="images/idioma/spain_flag.jpg" alt="Cambiar idioma a Español"/>
                    </form>
                </div>
            </div>
            <!--header -------------------------------------------------------------   --> 

                        <header class="row">                
                <div class="col-6">
                    <a href="index.jsp"><img src="images/logo.png" class="img-fluid w-100"/></a>
                </div>
                <div class="col-6">
                    <div class="row">
                        <c:if test="${empty sessionScope.usu}">
                            <div class="col-sm-6 col-12 mt-4">
                                <p data-toggle="modal" data-target="#exampleModal"><a href="#"><fmt:message key="logReg"/></a></p>
                            </div>
                            <div class="col-sm-3 col-6 mt-4">
                                <a href="#">  <img class="icono img-fluid logb mr-1 ml-1 " data-toggle="modal" data-target="#exampleModal" id="imagenUsuario" src="images/usuario.png"/></a>
                            </div>
                            <div class="col-sm-3 col-6 mt-4 ">
                                <form action="tienda.jsp">
                                    <input type="image" class="icono img-fluid" id="imagenCarrito" src="images/carrito.png"/>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${!empty sessionScope.usu}">
                            <div class="col-sm-6 col-12 mt-4">
                                <p>Bienvenido ${sessionScope.usu.nombreUsuario}</p>
                            </div>

                            <div class="col-sm-3 col-6 mt-4">
                                <form action="index.jsp">
                                    <input type="image" class="icono img-fluid mr-1 ml-1" src="images/logout.png">
                                    <input type="hidden" name="descon" value="descon"/>
                                </form>                       
                            </div>
                            <div class="col-sm-3 col-6 mt-4 ">
                                <form action="tienda.jsp">
                                    <input type="image" class="img-fluid icono" id="imagenCarrito" src="images/carrito.png"/>
                                </form>
                            </div>               
                        </c:if>
                    </div>
                </div>
            </header>

            <!--nav----------------------------------------------------------------------------------------->
            <nav class="row p-0 m-0 navbar navbar-dark navbar-expand-sm mt-5 mt-pr" id="navP">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="col-12 p-0 m-0 collapse navbar-collapse justify-content-end" id="menu">
                    <ul class="row liststylelone p-0 m-0 navbar-nav">
                        <li class="col-12 col-sm-3 p-0 m-0 nav-item">
                            <a tabindex="1" title="Enlace a la sección Principal" href="index.jsp" class="d-flex flex-column align-items-center"><img src="images/iconos/001-casa.png"/><span><fmt:message key="inicio"/></span></a>
                        </li> 
                        <li class="col-12 col-sm-3 p-0 m-0 nav-item">                         
                            <a tabindex="2" title="Enlace a la sección Tienda" href="tienda.jsp" class="d-flex flex-column align-items-center"><img src="images/iconos/006-bolsa-de-la-compra.png"/><span><fmt:message key="tienda"/></span></a>
                        </li>
                        <li class="col-12 col-sm-3 p-0 m-0 nav-item">
                            <a tabindex="3" title="Enlace a la sección Citas" href="citas.jsp" class="d-flex flex-column align-items-center"><img src="images/iconos/007-calendario.png"/><span><fmt:message key="citas"/></span></a>
                        </li> 
                        <li class="col-12 col-sm-3 p-0 m-0 nav-item">
                            <a tabindex="4" title="Enlace a la sección Contacto" href="contacto.jsp" class="d-flex flex-column align-items-center"><img src="images/iconos/004-correo.png"/><span><fmt:message key="contacto"/></span></a>
                        </li>  
                    </ul> 
                </div>
            </nav>  
            <!--nav----------------------------------------------------------------------------------------->            
            <ul class="breadcrumb">
                <li><a href="index.jsp"><fmt:message key="inicio"/></a></li>
                <li><fmt:message key="citas"/></li>
            </ul> 
            <main>
                <div id="titulo">
                    <h1><fmt:message key="citas"/></h1> 
                </div>
                <div id="cita">
                    <form action="citas.jsp">
                        <h3><fmt:message key="titleReserva"/></h3>
                        <div>
                            <label for="fecha"><fmt:message key="selFecha"/></label>
                            <input type="date" name="fecha" id="fecha" value="${requestScope.fechaSel}">
                            <input type="submit" name="botonfecha" value="<fmt:message key="btFecha"/>"/>
                        </div>
                        <div>
                            <label for="horaSeleccionada"><fmt:message key="selHora"/></label>
                            <select name="horaSeleccionada" id="horaSeleccionada">
                                <c:forEach var="fechaDisp" items="${requestScope.citasDisponibles}">
                                    <option value="${fechaDisp}">${fechaDisp}:00</option>
                                </c:forEach>             
                            </select>
                        </div>
                        <div>
                            <label for="tipoCita"><fmt:message key="selCita"/></label>
                            <select name="tipoCita" id="tipoCita">
                                <option>Registro de Mascota</option>
                                <option>Operación</option>
                                <option>Consulta</option>
                                <option>Peluqueria</option>
                                <option>Vacunas</option>
                                <option>Radiografia</option>
                                <option>Microchip</option>
                                <option>Otro</option>
                            </select>
                            <c:if test="${!empty sessionScope.usu}">
                                <input type="submit" name="botonReserva" value="<fmt:message key="botonReservar"/>"/>
                            </c:if>
                            <c:if test="${empty sessionScope.usu}">
                                <input type="submit" name="botonReserva" value="<fmt:message key="botonReservar"/>" disabled/>
                                <p>Debe Loguearse</p>

                            </c:if>                           
                        </div>
                    </form>
                </div>
                            <div class="contenido">
                    <h2><fmt:message key="citaTitle"/></h2>
                    <table id="citatorio">
                        <tr>
                            <th><fmt:message key="alusion"/></th>
                            <th><fmt:message key="fecha"/></th>
                            <th><fmt:message key="hora"/></th>
                        </tr>                        
                        <c:forEach var="cita" items="${requestScope.listaCitas}" >
                            <form action="citas.jsp">
                                <tr>  
                                    <td>${cita.tipoCita}</td>
                                    <td>                                 
                                    <fmt:formatDate pattern="yy-MMM-dd" value="${cita.fechaCita}" />
                                    </td>
                                    <td>${cita.horaCita}:00</td>                                         
                                    <td>
                                        <input type="hidden" name="codCita" value="${cita.codCita}">
                                        <input class="enlace" type="submit" value="<fmt:message key="cancelCita"/>"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </div>
                <div class="contenido">
                    <h2><fmt:message key="sTitulo1"/></h2>
                    <p><fmt:message key="texto1"/></p>
                </div> 
                <div class="contenido">
                    <h2><fmt:message key="sTitulo2"/></h2>
                    <p><fmt:message key="texto2"/></p>
                </div>
                <div class="contenido">
                    <h2><fmt:message key="sTitulo3"/></h2>
                    <p><fmt:message key="texto3"/></p>
                </div>                   
                <div class="contenido mt-5 mt-sm-2">
                    <h2><fmt:message key="sTitulo4"/></h2>
                    <p><fmt:message key="texto4"/></p>
                </div>  
            </main> 
            <!--Login-------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title text-center" id="exampleModalLabel">Login</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="index.jsp">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-12 col-lg-4 offset-md-1 sb">
                                        <label for="nombreUsuario">Nombre Usuario: </label> 
                                    </div>
                                    <div class="col-md-12 col-lg-6 offset-md-1 sb">
                                        <input type="text" name="nombreUsuario" id="nombreUsuario"/>
                                    </div>                        
                                </div>
                                <div class="row">
                                    <div class="col-md-12 col-lg-4 offset-md-1 sb">
                                        <label for="passwordUsuario">Contraseña: </label> 
                                    </div>
                                    <div class="col-md-12 col-lg-6 offset-md-1 sb">
                                        <input type="password" name="passwordUsuario" id="passwordUsuario"/> 
                                    </div> 

                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                <input type="submit" name="boton" value="entrar" class="btn btn-primary"/>
                            </div>
                            <div class="modal-footer">
                                <div class="col-md-12 col-12 offset-md-1 sb">
                                    <p class="text-center"> Si no estas registrado , Registrate</p>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="modal" data-target="#exampleModal2">Registrarse</button>
                                </div> 

                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--Fin Login-------------------------------------------------------------------------------------------------------------------------------------------->
            <!--Registro-------------------------------------------------------------------------------------------------------------------------------------------->

            <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModal1Label" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <form action="citas.jsp">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title text-center" id="exampleModal1Label">Registro</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body row">
                                <div class="col-4 offset-1"><label for="nombreUsuarioRegistro">Nombre Usuario</label></div>
                                <div class="col-5"><input type="text" name="nombreUsuarioRegistro" id="nombreUsuarioRegistro" placeholder=" Nombre de Usuario" required></div>

                                <div class="col-4 offset-1"><label for="passRegistro">Contraseña</label></div>
                                <div class="col-5"><input type="text" name="passRegistro" id="passRegistro" placeholder="Contraseña" required>    </div>
                                <div class="col-4 offset-1"><label for="nombreRegistro">Nombre</label></div>
                                <div class="col-5"><input type="text" name="nombreRegistro" id="nombreRegistro" placeholder="Nombre" required>    </div> 
                                <div class="col-4 offset-1"><label for="apellidosRegistro">Apellidos</label></div>
                                <div class="col-5"><input type="text" name="apellidosRegistro" id="apellidosRegistro" placeholder=" Apellidos" required>    </div> 
                                <div class="col-4 offset-1"><label for="emailRegistro">Email</label></div>
                                <div class="col-5"><input type="text" name="emailRegistro" id="emailRegistro" placeholder=" Email" required>    </div> 
                                <div class="col-4 offset-1"><label for="telefonoRegistro">Telefono</label></div>
                                <div class="col-5"><input type="text" name="telefonoRegistro" id="telefonoRegistro" placeholder=" Telefono" required>    </div>  
                                <div class="col-4 offset-1"><label for="direccionRegistro">Dirección</label></div>
                                <div class="col-5"><input type="text" name="direccionRegistro" id="direccionRegistro" placeholder=" Direccion" required>    </div>                              
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                <input type="submit" name="botonRegistro" value="Registrarse" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!--Fin Registro-------------------------------------------------------------------------------------------------------------------------------------------->   
        </div>
        <footer class="colheader">
            <div class="fondofooter">
            </div>
        </footer>    
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
