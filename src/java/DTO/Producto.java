/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByCodProducto", query = "SELECT p FROM Producto p WHERE p.codProducto = :codProducto")
    , @NamedQuery(name = "Producto.findByNombreProducto", query = "SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "Producto.findByPrecioProducto", query = "SELECT p FROM Producto p WHERE p.precioProducto = :precioProducto")
    , @NamedQuery(name = "Producto.findByExistenciasProducto", query = "SELECT p FROM Producto p WHERE p.existenciasProducto = :existenciasProducto")
    , @NamedQuery(name = "Producto.findByTipoProducto", query = "SELECT p FROM Producto p WHERE p.tipoProducto = :tipoProducto")
    , @NamedQuery(name = "Producto.findByDescripcionProducto", query = "SELECT p FROM Producto p WHERE p.descripcionProducto = :descripcionProducto")
    , @NamedQuery(name = "Producto.findByImagenProducto", query = "SELECT p FROM Producto p WHERE p.imagenProducto = :imagenProducto")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codProducto")
    private Integer codProducto;
    @Basic(optional = false)
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Basic(optional = false)
    @Column(name = "precioProducto")
    private double precioProducto;
    @Basic(optional = false)
    @Column(name = "existenciasProducto")
    private int existenciasProducto;
    @Basic(optional = false)
    @Column(name = "tipoProducto")
    private String tipoProducto;
    @Basic(optional = false)
    @Column(name = "descripcionProducto")
    private String descripcionProducto;
    @Basic(optional = false)
    @Column(name = "imagenProducto")
    private String imagenProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Historialpedido> historialpedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Cestatemporal> cestatemporalList;

    public Producto() {
    }

    public Producto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public Producto(Integer codProducto, String nombreProducto, double precioProducto, int existenciasProducto, String tipoProducto, String descripcionProducto, String imagenProducto) {
        this.codProducto = codProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.existenciasProducto = existenciasProducto;
        this.tipoProducto = tipoProducto;
        this.descripcionProducto = descripcionProducto;
        this.imagenProducto = imagenProducto;
    }

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getExistenciasProducto() {
        return existenciasProducto;
    }

    public void setExistenciasProducto(int existenciasProducto) {
        this.existenciasProducto = existenciasProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    @XmlTransient
    public List<Historialpedido> getHistorialpedidoList() {
        return historialpedidoList;
    }

    public void setHistorialpedidoList(List<Historialpedido> historialpedidoList) {
        this.historialpedidoList = historialpedidoList;
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
        hash += (codProducto != null ? codProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codProducto == null && other.codProducto != null) || (this.codProducto != null && !this.codProducto.equals(other.codProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Producto[ codProducto=" + codProducto + " ]";
    }
    
}
