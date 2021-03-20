package DTO;

import DTO.CestatemporalPK;
import DTO.Producto;
import DTO.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Cestatemporal.class)
public class Cestatemporal_ { 

    public static volatile SingularAttribute<Cestatemporal, CestatemporalPK> cestatemporalPK;
    public static volatile SingularAttribute<Cestatemporal, Usuario> usuario;
    public static volatile SingularAttribute<Cestatemporal, Integer> cantidad;
    public static volatile SingularAttribute<Cestatemporal, Producto> producto;

}