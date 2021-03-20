
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <title>Happy Pets - Tienda</title>
        <link rel="stylesheet" type="text/css" href="css/cssTienda/estiloTienda.css">
           <link rel="stylesheet" type="text/css" href="css/cssTienda/estiloImprimir.css" media="print" />
        <script>
            $(document).ready(function () {
                var mostrado = false;
                $("#cesta").hide();
                $("#botonEliminarCesta").on("click", function () {
                    $( "#cesta" ).toggle( "scale" );
                });
                $("#imagenCarritoCesta").on("click", function () {
                    if (mostrado == true) {
                       $( "#toggle" ).toggle({ effect: "scale", direction: "horizontal" });
                        mostrado = true;
                    } else {
                         $( "#cesta" ).toggle( "scale" );
                        mostrado = false;
                    }
                });
            });
        </script>
    </head>
    <body>
               <c:if test="${!empty param.botonBoletin}">
            <jsp:include page="Newsletter"/>
        </c:if>
        <c:if test="${!empty param.botonCategoria}">
            <jsp:include page="Categorias"/>

        </c:if>
        <c:if test="${!empty param.botonRegistro}">
            <jsp:include page="Registro"/>
        </c:if>  
        <c:if test="${!empty param.descon}">
            <jsp:include page="desconectarSesion"/>
            <c:redirect url="index.jsp"/>

        </c:if>  
        <c:if test="${!empty param.boton}">
            <jsp:include page="comprobarUsuario"/>
            <c:out value="${requestScope.mensaje}"/>

        </c:if>
        <c:if test="${!empty param.botonAgregarCesta}">
            <jsp:include page="AnadirCesta"/>  
        </c:if>
        <c:if test="${empty param.botonCategoria||param.botonTodasCategoria=='Todas las Categorias'}">
            <jsp:include page="consulta"/>
        </c:if>
        
        <c:if test="${!empty param.botonPagar}">  
            <div class="mensaje" id="mensajeGracias">
                <p>Muchas Gracias por comprar en nuestra Web!!</p>
            </div>
        </c:if>
        
        <c:if test="${requestScope.disponibilidadExistencias == false}">  
            <div class="mensaje" id="mensajeStock">
                <p>Lo sentimos, no disponemos de Stock suficiente</p>
            </div>
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
                    <form action="tienda.jsp">
                        <input type="hidden" name="language" value="en">
                        <input type="image" src="images/idioma/british_flag.jpg" alt="Cambiar idioma a Inglés"/>
                    </form>

                    <form action="tienda.jsp">
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
                       
                                    <img type="image" class="icono img-fluid" id="imagenCarrito" src="images/carrito.png" data-toggle="modal" data-target="#exampleModal"/>
                 
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
                                
                                    <img type="image" class="img-fluid icono" id="imagenCarritoCesta" src="images/carrito.png"/>
                                
                            </div>               
                        </c:if>
                    </div>
                </div>
            </header>

            <!--nav----------------------------------------------------------------------------------------->
            <nav class="row p-0 m-0 navbar navbar-dark navbar-expand-sm mt-5" id="navP">
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
                <li><fmt:message key="tienda"/></li>
                    <c:if test="${!empty param.botonCategoria}">
                    <li>${param.botonCategoria}</li>
                    </c:if>                
            </ul>

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
                    <form action="tienda.jsp">
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
            <main>
                <c:if test="${!empty sessionScope.usu}">
                    <jsp:include page="consultaCesta"/>
                    <div id="cesta">                        
                        <table border="2">

                         
                            <img src="images/eliminar2.png" id="botonEliminarCesta"/>
                            

                            <tr>
                                <th></th>
                                <th>Producto</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th>Total</th>
                            </tr>
                            <c:set var = "subtotal" scope = "session" value = "${0}"/>
                            <c:set var = "total" scope = "session" value = "${0}"/>
                            <c:forEach var="productoCesta" items="${sessionScope.miCesta}" >
                                <c:set var="subtotal" value="${ subtotal + ( productoCesta.cantidad * productoCesta.producto.precioProducto ) }" /> 
                                <tr>
                                    <td><input type="image" src="images/productos/${productoCesta.producto.imagenProducto}" disabled></td>
                                    <td>${productoCesta.producto.nombreProducto}</td>                                                       
                                    <td>${productoCesta.cantidad}</td> 
                                    <td>
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${productoCesta.producto.precioProducto}" /> €
                                    </td>  
                                    <td>
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${productoCesta.cantidad * productoCesta.producto.precioProducto}" /> €
                                    </td>
                                    <td>
                                        <form action="borrarProducto">
                                            <input type="hidden" value="${productoCesta.producto.codProducto}" name="codProducto"/>
                                            <input type="image" src="images/eliminar.png"/>
                                        </form>
                                    </td>
                                </tr>                                    

                            </c:forEach>
                            <tr>                        
                                <td colspan="3" class="tablaTotales">Subtotal: </td>
                            
                                <td colspan="3">
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${subtotal}" /> €
                                </td>
                            </tr>
                            <tr>                           
                                <td colspan="3" class="tablaTotales">IVA (21%): </td>
                               
                                <td colspan="3">
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${subtotal*0.21}" /> €                                    
                                </td>
                            </tr>                            

                            <c:set var="total" value="${subtotal*1.21}" />
                            <tr>
                                <td colspan="3" class="tablaTotales">TOTAL: </td>
                                
                                <td colspan="3">
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}" /> €
                                </td>
                            </tr>
                        </table>
                        <form action="carrito.jsp">
                            <input type="hidden" value="${total}" name="importeTotal"/>
                            <input type="submit" value="TramitarPedido" class="btn btn-primary" id="botonConfirmarCompra"/>
                        </form>                        
                    </div>
                </c:if>

                <section id="prodPadre">  
                    <h1><fmt:message key="tiendaTitulo"/></h1>
                    <div id="productos">

                        <c:forEach var="producto" items="${requestScope.listaProductos}" >
                            <article>
                                <form action="tienda.jsp" class="align-content-end" class="justify-content-end align-content-end">
                                    <div> <label for="cantidad"><fmt:message key="cantidad"/></label> 
                                        <input type="number" name="cantidad" value="1" id="cantidad"/>
                                    </div>
                                    <input type="hidden" name="productoAnadido" value="${producto.getCodProducto()}">
                                    <img src="images/productos/${producto.getImagenProducto()}" class="imagenes"/>
                                    <span><fmt:message key="stock"/>${producto.getExistenciasProducto()}</span>
                                    <input type="submit" value="<fmt:message key="anadir"/>" name="botonAgregarCesta" class="btn btn-primary boton  align-content-end"/>
                                </form>
                            </article>
                        </c:forEach>
                    </div>
                </section>

                <aside>
                    <div id="categorias">
                        <h2><fmt:message key="titleCategoria"/></h2>
                        <ul>     
                            <form action="tienda.jsp">
                                <li><input class="enlace" type="submit" name="botonCategoria" value="<fmt:message key="Alimentacion"/>" class="btn btn-primary col-10 mb-2"/></li>
                                <li><input class="enlace" type="submit" name="botonCategoria" value="<fmt:message key="Antiparasitario"/>" class="btn btn-primary col-10 mb-2"/></li>
                                <li><input class="enlace" type="submit" name="botonCategoria" value="<fmt:message key="Higiene"/>" class="btn btn-primary col-10 mb-2"/></li>
                                <li><input class="enlace" type="submit" name="botonCategoria" value="<fmt:message key="Accesorio"/>" class="btn btn-primary col-10 mb-2"/></li>
                                <li><input class="enlace" type="submit" name="botonCategoria" value="<fmt:message key="Comedero"/>" class="btn btn-primary col-10 mb-2"/></li>
                                <li><input type="submit" name="botonTodasCategoria" value="<fmt:message key="allCategorias"/>" class="btn btn-primary col-10 mb-2"/></li>
                            </form>
                        </ul>
                    </div>

                    <div id="newsletter"> 
                        <h2>NEWSLETTER</h2>
                        <p>¡Sucríbete a nuestro boletín!</p>
                        <p>Te enviaremos un email cada vez que publiquemos una noticia nueva.</p>
                        <form action="tienda.jsp">
                            <input type="email" placeholder="Dirección de E-mail" name="email"/>
                            <span class="icon-paper-plane-o"></span><input type="submit" name="botonBoletin" value="Enviar" class="btn btn-primary mt-2"/>		
                        </form>
                    </div>      
                </aside>

            </main>

        </div>
        <footer class="colheader">
            <div class="fondofooter">

            </div>
        </footer>
        <!-- Contenedor Principal----->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>