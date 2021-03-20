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
@Table(name = "historialpedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialpedido.findAll", query = "SELECT h FROM Historialpedido h")
    , @NamedQuery(name = "Historialpedido.findByCodPedido", query = "SELECT h FROM Historialpedido h WHERE h.historialpedidoPK.codPedido = :codPedido")
    , @NamedQuery(name = "Historialpedido.findByCodUsuario", query = "SELECT h FROM Historialpedido h WHERE h.historialpedidoPK.codUsuario = :codUsuario")
    , @NamedQuery(name = "Historialpedido.findByCodProducto", query = "SELECT h FROM Historialpedido h WHERE h.historialpedidoPK.codProducto = :codProducto")
    , @NamedQuery(name = "Historialpedido.findByCantidad", query = "SELECT h FROM Historialpedido h WHERE h.cantidad = :cantidad")})
public class Historialpedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistorialpedidoPK historialpedidoPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "codProducto", referencedColumnName = "codProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "codPedido", referencedColumnName = "codPedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public Historialpedido() {
    }

    public Historialpedido(HistorialpedidoPK historialpedidoPK) {
        this.historialpedidoPK = historialpedidoPK;
    }

    public Historialpedido(HistorialpedidoPK historialpedidoPK, int cantidad) {
        this.historialpedidoPK = historialpedidoPK;
        this.cantidad = cantidad;
    }

    public Historialpedido(int codPedido, int codUsuario, int codProducto) {
        this.historialpedidoPK = new HistorialpedidoPK(codPedido, codUsuario, codProducto);
    }

    public HistorialpedidoPK getHistorialpedidoPK() {
        return historialpedidoPK;
    }

    public void setHistorialpedidoPK(HistorialpedidoPK historialpedidoPK) {
        this.historialpedidoPK = historialpedidoPK;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialpedidoPK != null ? historialpedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialpedido)) {
            return false;
        }
        Historialpedido other = (Historialpedido) object;
        if ((this.historialpedidoPK == null && other.historialpedidoPK != null) || (this.historialpedidoPK != null && !this.historialpedidoPK.equals(other.historialpedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Historialpedido[ historialpedidoPK=" + historialpedidoPK + " ]";
    }
    
}
