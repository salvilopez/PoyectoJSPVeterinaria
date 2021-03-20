

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
        <link href="css/jquery.easy_slides.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=2.0, minimum-scale=1.0,  user-scalable=yes">
        <link rel="stylesheet" type="text/css" href="css/cssPrincipal/estiloPrincipal.css" media="screen" />
         <link rel="stylesheet" type="text/css" href="css/cssPrincipal/estiloImprimir.css" media="print" />
        <script src="js/jssor.slider-27.5.0.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Happy Pets - Inicio</title>
    </head>
    <body>
        <c:if test="${!empty param.descon}">
            <jsp:include page="desconectarSesion"/>
            <c:redirect url="index.jsp"/>
        </c:if>
        <c:if test="${!empty param.botonRegistro}">
            <jsp:include page="Registro"/>
        </c:if>  
        <c:if test="${!empty param.botonIrTienda}">
            <jsp:forward page="tienda.jsp"/>
        </c:if>       
        <c:if test="${!empty param.boton}">
            <jsp:include page="comprobarUsuario"/>
            <c:out value="${requestScope.mensaje}"/>
        </c:if>
        <c:if test="${requestScope.configuracion=='si'}">
            <jsp:forward page="administracion.jsp"/>
        </c:if>
        <c:if test="${requestScope.login=='si'}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <jsp:include page="imagenMascota"/>        
        <div class="container fondito">
            <div class="row bordebotuonblue m15">
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
                    <form action="index.jsp">
                        <input type="hidden" name="language" value="en">
                        <input type="image" src="images/idioma/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                    </form>

                    <form action="index.jsp">
                        <input type="hidden" name="language" value="es">
                        <input type="image" src="images/idioma/spain_flag.jpg" alt="Cambiar idioma a Español"/>
                    </form>
                </div>
            </div>

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
            <nav class="row p-0 m-0 navbar navbar-dark navbar-expand-sm" id="navP">
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

            <div class="row marginbajo bordebotoncabecera">
                <div class="col-12">
                    <ul class="breadcrumb">
                        <li><fmt:message key="inicio"/></li>
                    </ul>
                </div>
            </div>
            <!--carrusel-------------------------------------------------------------------------------------------------------------------------------------------->
            <div class="row" id="carrusel">
                <div class="col-12">
                    <div class="container text-center my-3">
                        <h5><fmt:message key="carruselTitle"/></h5>
                        <div id="recipeCarousel" class="carousel slide w-100" data-ride="carousel">
                            <div class="carousel-inner w-100" role="listbox">
                                <div class="carousel-item row no-gutters active">
                                    <c:forEach var="fotomascota2" items="${requestScope.listaMascota2}">
                                        <div class="col-3 float-left margimagen"><img class="img-fluid imagenCarrusel" src="images/animales/${fotomascota2.imagenMascota}" width="100%" height="100%"></div>
                                        </c:forEach>
                                </div>
                                <div class="carousel-item row no-gutters">
                                    <c:forEach var="fotomascota1" items="${requestScope.listaMascota1}">
                                        <div class="col-3 float-left margimagen"><img class="img-fluid imagenCarrusel" src="images/animales/${fotomascota1.imagenMascota}" width="100%" height="100%"></div>
                                        </c:forEach>
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#recipeCarousel" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Anteriores</span>
                            </a>
                            <a class="carousel-control-next" href="#recipeCarousel" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Siguientes</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!--FIn carrusel-------------------------------------------------------------------------------------------------------------------------------------------->
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
                    <form action="index.jsp">
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
            <!-- main-------------------------------------------------------------------------------------------------------------------------------------------->            
            <div class="row mbf" id="main">
                <div class="col-lg-4 col-md-12">
                    <div class="row">
                        <!--mapa---------------------------------------------------------->   
                        <div class="col-lg-10 offset-lg-1  col-md-12 btm mt-3 pt-3 mr-2 text-center align-items-center"  id="mapa">
                            <h5><fmt:message key="encuentranos"/></h5>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d405466.6573488486!2d-1.5398892652198135!3d37.44399613592239!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x567b1ac8b591c200!2sHappy+Pets!5e0!3m2!1ses!2ses!4v1550825454081" width="300" height="200" frameborder="0" style="border:0" allowfullscreen></iframe>                
                        </div> 
                        <!--fin mapa---------------------------------------------------------->              
                    </div>
                </div>
                <!--texto---------------------------------------------------------->    
                <div class="col-l2 col-xs-8   col-sm-10 col-md-10 offset-0 offset-md-0 offset-sm-1 col-lg-8">
                    <div class="row">   
                        <div class="col-8 col-xs-8 offset-1 ml-3 pl-3 text-center">
                            <h3><fmt:message key="InicioTitle1"/></h3>
                            <p><fmt:message key="InicioParraf1"/> </p>
                        </div>
                        <div class="col-3 mt-5 perro">
                            <img src="images/noticias/leishmaniasis.jpg" width="150px"/>
                        </div>

                        <div class="col-8 col-xs-8 offset-1 ml-3 pl-3 text-center">
                            <h3><fmt:message key="InicioTitle2"/></h3>
                            <p><fmt:message key="InicioParraf2"/> </p>
                        </div> 
                        <div class="col-3 mt-5 perro">
                            <img src="images/noticias/noticia1.jpg" width="150px"/>
                        </div>

                    </div>   
                </div>
                <!--Fin texto---------------------------------------------------------->     
            </div>   
            <!--Fin main-------------------------------------------------------------------------------------------------------------------------------------------->           
            <!--footer-------------------------------------------------------------------------------------------------------------------------------------------->           

        </div>
        <footer class="row colheader">
            <div class="fondofooter">
            </div>
        </footer>
        <!--Fin footer-------------------------------------------------------------------------------------------------------------------------------------------->           
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
