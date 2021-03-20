package DTO;

import DTO.Factura;
import DTO.HistorialpedidoPK;
import DTO.Producto;
import DTO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Historialpedido.class)
public class Historialpedido_ { 

    public static volatile SingularAttribute<Historialpedido, Factura> factura;
    public static volatile SingularAttribute<Historialpedido, HistorialpedidoPK> historialpedidoPK;
    public static volatile SingularAttribute<Historialpedido, Usuario> usuario;
    public static volatile SingularAttribute<Historialpedido, Integer> cantidad;
    public static volatile SingularAttribute<Historialpedido, Producto> producto;

}