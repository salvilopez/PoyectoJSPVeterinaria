/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodUsuario", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByPasswordUsuario", query = "SELECT u FROM Usuario u WHERE u.passwordUsuario = :passwordUsuario")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByCorreoUsuario", query = "SELECT u FROM Usuario u WHERE u.correoUsuario = :correoUsuario")
    , @NamedQuery(name = "Usuario.findByFechaRegistroUsuario", query = "SELECT u FROM Usuario u WHERE u.fechaRegistroUsuario = :fechaRegistroUsuario")
    , @NamedQuery(name = "Usuario.findByTelefonoUsuario", query = "SELECT u FROM Usuario u WHERE u.telefonoUsuario = :telefonoUsuario")
    , @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private Integer codUsuario;
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "passwordUsuario")
    private String passwordUsuario;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "correoUsuario")
    private String correoUsuario;
    @Basic(optional = false)
    @Column(name = "fechaRegistroUsuario")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistroUsuario;
    @Basic(optional = false)
    @Column(name = "telefonoUsuario")
    private int telefonoUsuario;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Historialpedido> historialpedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private List<Mascota> mascotaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUsuario")
    private List<Cita> citaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Cestatemporal> cestatemporalList;

    public Usuario() {
    }

    public Usuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(Integer codUsuario, String nombreUsuario, String passwordUsuario, String nombre, String apellidos, String correoUsuario, Date fechaRegistroUsuario, int telefonoUsuario, String direccion) {
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.passwordUsuario = passwordUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoUsuario = correoUsuario;
        this.fechaRegistroUsuario = fechaRegistroUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccion = direccion;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Date getFechaRegistroUsuario() {
        return fechaRegistroUsuario;
    }

    public void setFechaRegistroUsuario(Date fechaRegistroUsuario) {
        this.fechaRegistroUsuario = fechaRegistroUsuario;
    }

    public int getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(int telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Historialpedido> getHistorialpedidoList() {
        return historialpedidoList;
    }

    public void setHistorialpedidoList(List<Historialpedido> historialpedidoList) {
        this.historialpedidoList = historialpedidoList;
    }

    @XmlTransient
    public List<Mascota> getMascotaList() {
        return mascotaList;
    }

    public void setMascotaList(List<Mascota> mascotaList) {
        this.mascotaList = mascotaList;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @XmlTransient
    public List<Cestatemporal> getCestatemporalList() {
        return cestatemporalList;
    }

    public void setCestatemporalList(List<Cestatemporal> cestatemporalList) {
        this.cestatemporalList = cestatemporalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Usuario[ codUsuario=" + codUsuario + " ]";
    }
    
}
