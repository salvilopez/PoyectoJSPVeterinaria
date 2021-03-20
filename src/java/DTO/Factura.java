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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodPedido", query = "SELECT f FROM Factura f WHERE f.codPedido = :codPedido")
    , @NamedQuery(name = "Factura.findByCodUsuario", query = "SELECT f FROM Factura f WHERE f.codUsuario = :codUsuario")
    , @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha")
    , @NamedQuery(name = "Factura.findByImporteTotal", query = "SELECT f FROM Factura f WHERE f.importeTotal = :importeTotal")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codPedido")
    private Integer codPedido;
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private int codUsuario;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "importeTotal")
    private double importeTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<Historialpedido> historialpedidoList;

    public Factura() {
    }

    public Factura(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Factura(Integer codPedido, int codUsuario, Date fecha, double importeTotal) {
        this.codPedido = codPedido;
        this.codUsuario = codUsuario;
        this.fecha = fecha;
        this.importeTotal = importeTotal;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @XmlTransient
    public List<Historialpedido> getHistorialpedidoList() {
        return historialpedidoList;
    }

    public void setHistorialpedidoList(List<Historialpedido> historialpedidoList) {
        this.historialpedidoList = historialpedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPedido != null ? codPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codPedido == null && other.codPedido != null) || (this.codPedido != null && !this.codPedido.equals(other.codPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Factura[ codPedido=" + codPedido + " ]";
    }
    
}
