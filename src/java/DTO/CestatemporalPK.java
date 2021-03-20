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
public class CestatemporalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codUsuario")
    private int codUsuario;
    @Basic(optional = false)
    @Column(name = "codProducto")
    private int codProducto;

    public CestatemporalPK() {
    }

    public CestatemporalPK(int codUsuario, int codProducto) {
        this.codUsuario = codUsuario;
        this.codProducto = codProducto;
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
        hash += (int) codUsuario;
        hash += (int) codProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CestatemporalPK)) {
            return false;
        }
        CestatemporalPK other = (CestatemporalPK) object;
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
        return "DTO.CestatemporalPK[ codUsuario=" + codUsuario + ", codProducto=" + codProducto + " ]";
    }
    
}
