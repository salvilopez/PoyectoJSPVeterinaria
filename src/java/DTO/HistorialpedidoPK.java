/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author usuario
 */
@Embeddable
public class HistorialpedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codPedido")
    private int codPedido;
    @Basic(optional = false)
    @Column(name = "codUsuario")
    private int codUsuario;
    @Basic(optional = false)
    @Column(name = "codProducto")
    private int codProducto;

    public HistorialpedidoPK() {
    }

    public HistorialpedidoPK(int codPedido, int codUsuario, int codProducto) {
        this.codPedido = codPedido;
        this.codUsuario = codUsuario;
        this.codProducto = codProducto;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) codUsuario;
        hash += (int) codProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialpedidoPK)) {
            return false;
        }
        HistorialpedidoPK other = (HistorialpedidoPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.codUsuario != other.codUsuario) {
            return false;
        }
        if (this.codProducto != other.codProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.HistorialpedidoPK[ codPedido=" + codPedido + ", codUsuario=" + codUsuario + ", codProducto=" + codProducto + " ]";
    }
    
}
