package DTO;

import DTO.Cestatemporal;
import DTO.Cita;
import DTO.Historialpedido;
import DTO.Mascota;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, Integer> telefonoUsuario;
    public static volatile SingularAttribute<Usuario, Integer> codUsuario;
    public static volatile SingularAttribute<Usuario, String> passwordUsuario;
    public static volatile SingularAttribute<Usuario, String> direccion;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistroUsuario;
    public static volatile ListAttribute<Usuario, Mascota> mascotaList;
    public static volatile SingularAttribute<Usuario, String> correoUsuario;
    public static volatile ListAttribute<Usuario, Cestatemporal> cestatemporalList;
    public static volatile ListAttribute<Usuario, Historialpedido> historialpedidoList;
    public static volatile ListAttribute<Usuario, Cita> citaList;

}