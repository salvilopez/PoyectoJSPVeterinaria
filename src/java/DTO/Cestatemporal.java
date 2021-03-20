/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "cestatemporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cestatemporal.findAll", query = "SELECT c FROM Cestatemporal c")
    , @NamedQuery(name = "Cestatemporal.findByCodUsuario", query = "SELECT c FROM Cestatemporal c WHERE c.cestatemporalPK.codUsuario = :codUsuario")
    , @NamedQuery(name = "Cestatemporal.findByCodProducto", query = "SELECT c FROM Cestatemporal c WHERE c.cestatemporalPK.codProducto = :codProducto")
    , @NamedQuery(name = "Cestatemporal.findByCantidad", query = "SELECT c FROM Cestatemporal c WHERE c.cantidad = :cantidad")})
public class Cestatemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CestatemporalPK cestatemporalPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "codProducto", referencedColumnName = "codProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public Cestatemporal() {
    }

    public Cestatemporal(CestatemporalPK cestatemporalPK) {
        this.cestatemporalPK = cestatemporalPK;
    }

    public Cestatemporal(CestatemporalPK cestatemporalPK, int cantidad) {
        this.cestatemporalPK = cestatemporalPK;
        this.cantidad = cantidad;
    }

    public Cestatemporal(int codUsuario, int codProducto) {
        this.cestatemporalPK = new CestatemporalPK(codUsuario, codProducto);
    }

    public CestatemporalPK getCestatemporalPK() {
        return cestatemporalPK;
    }

    public void setCestatemporalPK(CestatemporalPK cestatemporalPK) {
        this.cestatemporalPK = cestatemporalPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cestatemporalPK != null ? cestatemporalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cestatemporal)) {
            return false;
        }
        Cestatemporal other = (Cestatemporal) object;
        if ((this.cestatemporalPK == null && other.cestatemporalPK != null) || (this.cestatemporalPK != null && !this.cestatemporalPK.equals(other.cestatemporalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Cestatemporal[ cestatemporalPK=" + cestatemporalPK + " ]";
    }
    
}
