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
@Table(name = "cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findByCodCita", query = "SELECT c FROM Cita c WHERE c.codCita = :codCita")
    , @NamedQuery(name = "Cita.findByFechaCita", query = "SELECT c FROM Cita c WHERE c.fechaCita = :fechaCita")
    , @NamedQuery(name = "Cita.findByHoraCita", query = "SELECT c FROM Cita c WHERE c.horaCita = :horaCita")
    , @NamedQuery(name = "Cita.findByTipoCita", query = "SELECT c FROM Cita c WHERE c.tipoCita = :tipoCita")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codCita")
    private Integer codCita;
    @Basic(optional = false)
    @Column(name = "fechaCita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Basic(optional = false)
    @Column(name = "horaCita")
    private int horaCita;
    @Basic(optional = false)
    @Column(name = "tipoCita")
    private String tipoCita;
    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario")
    @ManyToOne(optional = false)
    private Usuario codUsuario;

    public Cita() {
    }

    public Cita(Integer codCita) {
        this.codCita = codCita;
    }

    public Cita(Integer codCita, Date fechaCita, int horaCita, String tipoCita) {
        this.codCita = codCita;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.tipoCita = tipoCita;
    }

    public Integer getCodCita() {
        return codCita;
    }

    public void setCodCita(Integer codCita) {
        this.codCita = codCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public int getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(int horaCita) {
        this.horaCita = horaCita;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
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
        hash += (codCita != null ? codCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.codCita == null && other.codCita != null) || (this.codCita != null && !this.codCita.equals(other.codCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Cita[ codCita=" + codCita + " ]";
    }
    
}
