package DTO;

import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:33:01")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Date> fechaCita;
    public static volatile SingularAttribute<Cita, Usuario> codUsuario;
    public static volatile SingularAttribute<Cita, Integer> horaCita;
    public static volatile SingularAttribute<Cita, String> tipoCita;
    public static volatile SingularAttribute<Cita, Integer> codCita;

}