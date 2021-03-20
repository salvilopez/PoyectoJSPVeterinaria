/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m")
    , @NamedQuery(name = "Mascota.findByCodMascota", query = "SELECT m FROM Mascota m WHERE m.codMascota = :codMascota")
    , @NamedQuery(name = "Mascota.findByNombreMascota", query = "SELECT m FROM Mascota m WHERE m.nombreMascota = :nombreMascota")
    , @NamedQuery(name = "Mascota.findByFechaNacimientoMascota", query = "SELECT m FROM Mascota m WHERE m.fechaNacimientoMascota = :fechaNacimientoMascota")
    , @NamedQuery(name = "Mascota.findByGenero", query = "SELECT m FROM Mascota m WHERE m.genero = :genero")
    , @NamedQuery(name = "Mascota.findByEspecie", query = "SELECT m FROM Mascota m WHERE m.especie = :especie")
    , @NamedQuery(name = "Mascota.findByRaza", query = "SELECT m FROM Mascota m WHERE m.raza = :raza")
    , @NamedQuery(name = "Mascota.findByDescripcionMascota", query = "SELECT m FROM Mascota m WHERE m.descripcionMascota = :descripcionMascota")
    , @NamedQuery(name = "Mascota.findByImagenMascota", query = "SELECT m FROM Mascota m WHERE m.imagenMascota = :imagenMascota")})
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codMascota")
    private Integer codMascota;
    @Basic(optional = false)
    @Column(name = "nombreMascota")
    private String nombreMascota;
    @Basic(optional = false)
    @Column(name = "fechaNacimientoMascota")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoMascota;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "especie")
    private String especie;
    @Basic(optional = false)
    @Column(name = "raza")
    private String raza;
    @Basic(optional = false)
    @Column(name = "descripcionMascota")
    private String descripcionMascota;
    @Basic(optional = false)
    @Column(name = "imagenMascota")
    private String imagenMascota;
    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Mascota() {
    }

    public Mascota(Integer codMascota) {
        this.codMascota = codMascota;
    }

    public Mascota(Integer codMascota, String nombreMascota, Date fechaNacimientoMascota, String genero, String especie, String raza, String descripcionMascota, String imagenMascota) {
        this.codMascota = codMascota;
        this.nombreMascota = nombreMascota;
        this.fechaNacimientoMascota = fechaNacimientoMascota;
        this.genero = genero;
        this.especie = especie;
        this.raza = raza;
        this.descripcionMascota = descripcionMascota;
        this.imagenMascota = imagenMascota;
    }

    public Integer getCodMascota() {
        return codMascota;
    }

    public void setCodMascota(Integer codMascota) {
        this.codMascota = codMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Date getFechaNacimientoMascota() {
        return fechaNacimientoMascota;
    }

    public void setFechaNacimientoMascota(Date fechaNacimientoMascota) {
        this.fechaNacimientoMascota = fechaNacimientoMascota;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public String getImagenMascota() {
        return imagenMascota;
    }

    public void setImagenMascota(String imagenMascota) {
        this.imagenMascota = imagenMascota;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMascota != null ? codMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.codMascota == null && other.codMascota != null) || (this.codMascota != null && !this.codMascota.equals(other.codMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Mascota[ codMascota=" + codMascota + " ]";
    }
    
}
