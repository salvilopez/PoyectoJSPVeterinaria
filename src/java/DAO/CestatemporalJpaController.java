/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Cestatemporal;
import DTO.CestatemporalPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuario;
import DTO.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class CestatemporalJpaController implements Serializable {

    public CestatemporalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cestatemporal cestatemporal) throws PreexistingEntityException, Exception {
        if (cestatemporal.getCestatemporalPK() == null) {
            cestatemporal.setCestatemporalPK(new CestatemporalPK());
        }
        cestatemporal.getCestatemporalPK().setCodProducto(cestatemporal.getProducto().getCodProducto());
        cestatemporal.getCestatemporalPK().setCodUsuario(cestatemporal.getUsuario().getCodUsuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = cestatemporal.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCodUsuario());
                cestatemporal.setUsuario(usuario);
            }
            Producto producto = cestatemporal.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getCodProducto());
                cestatemporal.setProducto(producto);
            }
            em.persist(cestatemporal);
            if (usuario != null) {
                usuario.getCestatemporalList().add(cestatemporal);
                usuario = em.merge(usuario);
            }
            if (producto != null) {
                producto.getCestatemporalList().add(cestatemporal);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCestatemporal(cestatemporal.getCestatemporalPK()) != null) {
                throw new PreexistingEntityException("Cestatemporal " + cestatemporal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cestatemporal cestatemporal) throws NonexistentEntityException, Exception {
        cestatemporal.getCestatemporalPK().setCodProducto(cestatemporal.getProducto().getCodProducto());
        cestatemporal.getCestatemporalPK().setCodUsuario(cestatemporal.getUsuario().getCodUsuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cestatemporal persistentCestatemporal = em.find(Cestatemporal.class, cestatemporal.getCestatemporalPK());
            Usuario usuarioOld = persistentCestatemporal.getUsuario();
            Usuario usuarioNew = cestatemporal.getUsuario();
            Producto productoOld = persistentCestatemporal.getProducto();
            Producto productoNew = cestatemporal.getProducto();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCodUsuario());
                cestatemporal.setUsuario(usuarioNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getCodProducto());
                cestatemporal.setProducto(productoNew);
            }
            cestatemporal = em.merge(cestatemporal);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getCestatemporalList().remove(cestatemporal);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getCestatemporalList().add(cestatemporal);
                usuarioNew = em.merge(usuarioNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getCestatemporalList().remove(cestatemporal);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getCestatemporalList().add(cestatemporal);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CestatemporalPK id = cestatemporal.getCestatemporalPK();
                if (findCestatemporal(id) == null) {
                    throw new NonexistentEntityException("The cestatemporal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CestatemporalPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cestatemporal cestatemporal;
            try {
                cestatemporal = em.getReference(Cestatemporal.class, id);
                cestatemporal.getCestatemporalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cestatemporal with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = cestatemporal.getUsuario();
            if (usuario != null) {
                usuario.getCestatemporalList().remove(cestatemporal);
                usuario = em.merge(usuario);
            }
            Producto producto = cestatemporal.getProducto();
            if (producto != null) {
                producto.getCestatemporalList().remove(cestatemporal);
                producto = em.merge(producto);
            }
            em.remove(cestatemporal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cestatemporal> findCestatemporalEntities() {
        return findCestatemporalEntities(true, -1, -1);
    }

    public List<Cestatemporal> findCestatemporalEntities(int maxResults, int firstResult) {
        return findCestatemporalEntities(false, maxResults, firstResult);
    }

    private List<Cestatemporal> findCestatemporalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cestatemporal.class));
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

    public Cestatemporal findCestatemporal(CestatemporalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cestatemporal.class, id);
        } finally {
            em.close();
        }
    }

    public int getCestatemporalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cestatemporal> rt = cq.from(Cestatemporal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
