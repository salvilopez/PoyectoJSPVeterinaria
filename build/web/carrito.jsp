<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <c:if test="${!empty param.language}">
            <fmt:setLocale value="${param.language}"/>
            <c:set var="language" scope="session" value="${param.language}"/>
        </c:if>
        <fmt:setBundle basename="internacionalizacion.mensajes" />
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2.0, minimum-scale=1.0,  user-scalable=yes">
        <link rel="stylesheet" type="text/css" href="css/cssCarrito/estiloCarrito.css">
                <link rel="stylesheet" type="text/css" href="css/cssCarrito/estiloImprimir.css" media="print" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Pets - Carrito</title>
    </head>
    <body>
        <c:if test="${!empty param.botonRegistro}">
            <jsp:include page="Registro"/>
        </c:if>  
        <c:if test="${!empty param.descon}">
            <jsp:include page="desconectarSesion"/>
            <c:redirect url="index.jsp"/>
        </c:if>  
        <c:if test="${!empty param.botonPagar}">            
            <jsp:include page="FinalizarCarrito"/> 
            <jsp:forward page="tienda.jsp"/>            
        </c:if>

        <div id="contenedor">
            <div id="encabezado" class="mencabe">
                <div>
                    <span><fmt:message key="tUrgencias"/> 651-526-682</span>
                </div>
                <div>
                    <span><fmt:message key="direccion"/></span>
                </div>
                <div>
                    <span>happypetsronda@gmail.com</span>
                </div>                
                <div class="container-banderas">
                    <form action="carrito.jsp">
                        <input type="hidden" name="language" value="en">
                        <input type="image" src="images/idioma/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                    </form>

                    <form action="carrito.jsp">
                        <input type="hidden" name="language" value="es">
                        <input type="image" src="images/idioma/spain_flag.jpg" alt="Cambiar idioma a Español"/>
                    </form>
                </div>
            </div>


            <header class="row flexstart bordebotoncabecera ">                
                <div class="col-sm-4 col-10 offset-sm-0">
                    <a href="index.jsp"><img src="images/logo.png" class="img-fluid logomin offset-2 offset-lg-0"/></a>
                </div>
                <div class="col-auto d-flex flex-row " >
                    <c:if test="${empty sessionScope.usu}">
                        <p class=" col-4 offset-1 margintop offset-sm-3 logb" data-toggle="modal" data-target="#exampleModal"><a href="#"><fmt:message key="logReg"/></a></p>
                    </c:if>

                    <c:if test="${!empty sessionScope.usu}">
                        <p class="col-4 offset-1 margintop offset-sm-3 ">Bienvenido ${sessionScope.usu.nombreUsuario}</p>
                        <div class="col-auto margintop offset-3 offset-sm-0">
                            <form action="index.jsp">
                                <input type="image" class="icono img-fluid mr-1 ml-1" src="images/logout.png">
                                <input type="hidden" name="descon" value="descon"/>
                            </form>                       
                        </div>
                        <div class="col-auto margintop mr-5 ">
                            <form action="tienda.jsp">
                                <input type="image" class="img-fluid icono" id="imagenCarrito" src="images/carrito.png"/>
                            </form>
                        </div>               
                    </c:if>
                    <c:if test="${empty sessionScope.usu}">
                        <div class="col-auto margintop">
                            <a href="#">  <img class="icono img-fluid logb mr-1 ml-1 " data-toggle="modal" data-target="#exampleModal" id="imagenUsuario" src="images/usuario.png"/></a>
                        </div>
                        <div class="col-auto margintop mr-5">
                            <form action="tienda.jsp">
                                <input type="image" class="icono img-fluid" id="imagenCarrito" src="images/carrito.png"/>
                            </form>
                        </div>
                    </c:if>
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
                <li><a href="tienda.jsp"><fmt:message key="tienda"/></a></li>
                <li><fmt:message key="miCesta"/></li>
            </ul>

            <jsp:include page="consultaCesta"/>
            <div id="cesta">    

                <h1><fmt:message key="carritoTitulo"/></h1>
                <table id="carrito">  
                    <tr>
                        <th></th>
                        <th></th>
                        <th><fmt:message key="precio"/></th>
                        <th><fmt:message key="cantidad"/></th>
                    </tr>
                    <c:set var = "subtotal" scope = "session" value = "${0}"/>
                    <c:set var = "total" scope = "session" value = "${0}"/>
                    <c:forEach var="productoCesta" items="${sessionScope.miCesta}" >
                        <c:set var="subtotal" value="${ subtotal + ( productoCesta.cantidad * productoCesta.producto.precioProducto ) }" /> 
                        <tr class="bTop">
                            <td><input type="image" src="images/productos/${productoCesta.producto.imagenProducto}" disabled></td>
                            <td>${productoCesta.producto.descripcionProducto}</td> 
                            <td>
                                <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${productoCesta.producto.precioProducto}" /> €
                            </td> 
                            <td><input type="text" value="${productoCesta.cantidad}" width="25px"></td>
                        <tr class="bBottom">
                            <td>
                                <form action="borrarProducto">
                                    <input type="hidden" value="${productoCesta.producto.codProducto}" name="codProducto"/>
                                    <input class="enlace" type="submit" value="<fmt:message key="bEliminar"/>"/>                                    
                                </form>
                            </td>
                        </tr>
                        </tr>                                    

                    </c:forEach>
                    <tr>                        
                        <td colspan="2" class="tablaTotales">Subtotal:</td>
                        <td></td>
                        <td>
                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${subtotal}" /> €
                        </td>
                    </tr>
                    <tr>                           
                        <td colspan="2" class="tablaTotales">IVA (21%): </td>
                        <td></td>
                        <td>
                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${subtotal*0.21}" /> €                                    
                        </td>
                    </tr>                            

                    <c:set var="total" value="${subtotal*1.21}" />
                    <tr>
                        <td colspan="2" class="tablaTotales">Total:</td>
                        <td></td>
                        <td>
                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}" /> €
                        </td>
                    </tr>
                </table>  
                        <div id="botones">
                <form action="tienda.jsp">
                    <input type="submit" value="<fmt:message key="bSeguir"/>" class="btn btn-primary"/>
                </form>

                <form action="carrito.jsp">
                    <input type="hidden" name="importeTotal" value="${param.importeTotal}" class="btn btn-secondary"/>
                    <input class="bAnimate btn btn-primary" type="submit" value="<fmt:message key="bPagar"/>" name="botonPagar"/>            
                </form>

              
                    <form action="generaPdf">
                        <input type="submit" value="<fmt:message key="bPdf"/>"class="btn btn-primary"/>
                    </form>
                
                    </div>
            </div  

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
                    <form action="contacto.jsp">
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
