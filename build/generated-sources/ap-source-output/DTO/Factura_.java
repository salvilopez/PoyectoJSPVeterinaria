package DTO;

import DTO.Historialpedido;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Integer> codUsuario;
    public static volatile SingularAttribute<Factura, Double> importeTotal;
    public static volatile SingularAttribute<Factura, Integer> codPedido;
    public static volatile ListAttribute<Factura, Historialpedido> historialpedidoList;

}