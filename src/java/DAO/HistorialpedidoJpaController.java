/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuario;
import DTO.Producto;
import DTO.Factura;
import DTO.Historialpedido;
import DTO.HistorialpedidoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class HistorialpedidoJpaController implements Serializable {

    public HistorialpedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialpedido historialpedido) throws PreexistingEntityException, Exception {
        if (historialpedido.getHistorialpedidoPK() == null) {
            historialpedido.setHistorialpedidoPK(new HistorialpedidoPK());
        }
        historialpedido.getHistorialpedidoPK().setCodPedido(historialpedido.getFactura().getCodPedido());
        historialpedido.getHistorialpedidoPK().setCodUsuario(historialpedido.getUsuario().getCodUsuario());
        historialpedido.getHistorialpedidoPK().setCodProducto(historialpedido.getProducto().getCodProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = historialpedido.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCodUsuario());
                historialpedido.setUsuario(usuario);
            }
            Producto producto = historialpedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getCodProducto());
                historialpedido.setProducto(producto);
            }
            Factura factura = historialpedido.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getCodPedido());
                historialpedido.setFactura(factura);
            }
            em.persist(historialpedido);
            if (usuario != null) {
                usuario.getHistorialpedidoList().add(historialpedido);
                usuario = em.merge(usuario);
            }
            if (producto != null) {
                producto.getHistorialpedidoList().add(historialpedido);
                producto = em.merge(producto);
            }
            if (factura != null) {
                factura.getHistorialpedidoList().add(historialpedido);
                factura = em.merge(factura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialpedido(historialpedido.getHistorialpedidoPK()) != null) {
                throw new PreexistingEntityException("Historialpedido " + historialpedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialpedido historialpedido) throws NonexistentEntityException, Exception {
        historialpedido.getHistorialpedidoPK().setCodPedido(historialpedido.getFactura().getCodPedido());
        historialpedido.getHistorialpedidoPK().setCodUsuario(historialpedido.getUsuario().getCodUsuario());
        historialpedido.getHistorialpedidoPK().setCodProducto(historialpedido.getProducto().getCodProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpedido persistentHistorialpedido = em.find(Historialpedido.class, historialpedido.getHistorialpedidoPK());
            Usuario usuarioOld = persistentHistorialpedido.getUsuario();
            Usuario usuarioNew = historialpedido.getUsuario();
            Producto productoOld = persistentHistorialpedido.getProducto();
            Producto productoNew = historialpedido.getProducto();
            Factura facturaOld = persistentHistorialpedido.getFactura();
            Factura facturaNew = historialpedido.getFactura();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCodUsuario());
                historialpedido.setUsuario(usuarioNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getCodProducto());
                historialpedido.setProducto(productoNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getCodPedido());
                historialpedido.setFactura(facturaNew);
            }
            historialpedido = em.merge(historialpedido);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getHistorialpedidoList().remove(historialpedido);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getHistorialpedidoList().add(historialpedido);
                usuarioNew = em.merge(usuarioNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getHistorialpedidoList().remove(historialpedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getHistorialpedidoList().add(historialpedido);
                productoNew = em.merge(productoNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getHistorialpedidoList().remove(historialpedido);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getHistorialpedidoList().add(historialpedido);
                facturaNew = em.merge(facturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HistorialpedidoPK id = historialpedido.getHistorialpedidoPK();
                if (findHistorialpedido(id) == null) {
                    throw new NonexistentEntityException("The historialpedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HistorialpedidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpedido historialpedido;
            try {
                historialpedido = em.getReference(Historialpedido.class, id);
                historialpedido.getHistorialpedidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialpedido with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = historialpedido.getUsuario();
            if (usuario != null) {
                usuario.getHistorialpedidoList().remove(historialpedido);
                usuario = em.merge(usuario);
            }
            Producto producto = historialpedido.getProducto();
            if (producto != null) {
                producto.getHistorialpedidoList().remove(historialpedido);
                producto = em.merge(producto);
            }
            Factura factura = historialpedido.getFactura();
            if (factura != null) {
                factura.getHistorialpedidoList().remove(historialpedido);
                factura = em.merge(factura);
            }
            em.remove(historialpedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialpedido> findHistorialpedidoEntities() {
        return findHistorialpedidoEntities(true, -1, -1);
    }

    public List<Historialpedido> findHistorialpedidoEntities(int maxResults, int firstResult) {
        return findHistorialpedidoEntities(false, maxResults, firstResult);
    }

    private List<Historialpedido> findHistorialpedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialpedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historialpedido findHistorialpedido(HistorialpedidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialpedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialpedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialpedido> rt = cq.from(Historialpedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
