package DTO;

import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Mascota.class)
public class Mascota_ { 

    public static volatile SingularAttribute<Mascota, String> especie;
    public static volatile SingularAttribute<Mascota, Integer> codMascota;
    public static volatile SingularAttribute<Mascota, String> raza;
    public static volatile SingularAttribute<Mascota, Usuario> codUsuario;
    public static volatile SingularAttribute<Mascota, String> nombreMascota;
    public static volatile SingularAttribute<Mascota, String> genero;
    public static volatile SingularAttribute<Mascota, Date> fechaNacimientoMascota;
    public static volatile SingularAttribute<Mascota, String> descripcionMascota;
    public static volatile SingularAttribute<Mascota, String> imagenMascota;

}