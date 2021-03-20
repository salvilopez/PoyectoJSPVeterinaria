package DTO;

import DTO.Cestatemporal;
import DTO.Historialpedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Integer> codProducto;
    public static volatile SingularAttribute<Producto, String> tipoProducto;
    public static volatile SingularAttribute<Producto, Integer> existenciasProducto;
    public static volatile SingularAttribute<Producto, String> descripcionProducto;
    public static volatile ListAttribute<Producto, Cestatemporal> cestatemporalList;
    public static volatile SingularAttribute<Producto, Double> precioProducto;
    public static volatile ListAttribute<Producto, Historialpedido> historialpedidoList;
    public static volatile SingularAttribute<Producto, String> nombreProducto;
    public static volatile SingularAttribute<Producto, String> imagenProducto;

}