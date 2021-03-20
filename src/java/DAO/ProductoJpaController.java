/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Historialpedido;
import java.util.ArrayList;
import java.util.List;
import DTO.Cestatemporal;
import DTO.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getHistorialpedidoList() == null) {
            producto.setHistorialpedidoList(new ArrayList<Historialpedido>());
        }
        if (producto.getCestatemporalList() == null) {
            producto.setCestatemporalList(new ArrayList<Cestatemporal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historialpedido> attachedHistorialpedidoList = new ArrayList<Historialpedido>();
            for (Historialpedido historialpedidoListHistorialpedidoToAttach : producto.getHistorialpedidoList()) {
                historialpedidoListHistorialpedidoToAttach = em.getReference(historialpedidoListHistorialpedidoToAttach.getClass(), historialpedidoListHistorialpedidoToAttach.getHistorialpedidoPK());
                attachedHistorialpedidoList.add(historialpedidoListHistorialpedidoToAttach);
            }
            producto.setHistorialpedidoList(attachedHistorialpedidoList);
            List<Cestatemporal> attachedCestatemporalList = new ArrayList<Cestatemporal>();
            for (Cestatemporal cestatemporalListCestatemporalToAttach : producto.getCestatemporalList()) {
                cestatemporalListCestatemporalToAttach = em.getReference(cestatemporalListCestatemporalToAttach.getClass(), cestatemporalListCestatemporalToAttach.getCestatemporalPK());
                attachedCestatemporalList.add(cestatemporalListCestatemporalToAttach);
            }
            producto.setCestatemporalList(attachedCestatemporalList);
            em.persist(producto);
            for (Historialpedido historialpedidoListHistorialpedido : producto.getHistorialpedidoList()) {
                Producto oldProductoOfHistorialpedidoListHistorialpedido = historialpedidoListHistorialpedido.getProducto();
                historialpedidoListHistorialpedido.setProducto(producto);
                historialpedidoListHistorialpedido = em.merge(historialpedidoListHistorialpedido);
                if (oldProductoOfHistorialpedidoListHistorialpedido != null) {
                    oldProductoOfHistorialpedidoListHistorialpedido.getHistorialpedidoList().remove(historialpedidoListHistorialpedido);
                    oldProductoOfHistorialpedidoListHistorialpedido = em.merge(oldProductoOfHistorialpedidoListHistorialpedido);
                }
            }
            for (Cestatemporal cestatemporalListCestatemporal : producto.getCestatemporalList()) {
                Producto oldProductoOfCestatemporalListCestatemporal = cestatemporalListCestatemporal.getProducto();
                cestatemporalListCestatemporal.setProducto(producto);
                cestatemporalListCestatemporal = em.merge(cestatemporalListCestatemporal);
                if (oldProductoOfCestatemporalListCestatemporal != null) {
                    oldProductoOfCestatemporalListCestatemporal.getCestatemporalList().remove(cestatemporalListCestatemporal);
                    oldProductoOfCestatemporalListCestatemporal = em.merge(oldProductoOfCestatemporalListCestatemporal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getCodProducto());
            List<Historialpedido> historialpedidoListOld = persistentProducto.getHistorialpedidoList();
            List<Historialpedido> historialpedidoListNew = producto.getHistorialpedidoList();
            List<Cestatemporal> cestatemporalListOld = persistentProducto.getCestatemporalList();
            List<Cestatemporal> cestatemporalListNew = producto.getCestatemporalList();
            List<String> illegalOrphanMessages = null;
            for (Historialpedido historialpedidoListOldHistorialpedido : historialpedidoListOld) {
                if (!historialpedidoListNew.contains(historialpedidoListOldHistorialpedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialpedido " + historialpedidoListOldHistorialpedido + " since its producto field is not nullable.");
                }
            }
            for (Cestatemporal cestatemporalListOldCestatemporal : cestatemporalListOld) {
                if (!cestatemporalListNew.contains(cestatemporalListOldCestatemporal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cestatemporal " + cestatemporalListOldCestatemporal + " since its producto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historialpedido> attachedHistorialpedidoListNew = new ArrayList<Historialpedido>();
            for (Historialpedido historialpedidoListNewHistorialpedidoToAttach : historialpedidoListNew) {
                historialpedidoListNewHistorialpedidoToAttach = em.getReference(historialpedidoListNewHistorialpedidoToAttach.getClass(), historialpedidoListNewHistorialpedidoToAttach.getHistorialpedidoPK());
                attachedHistorialpedidoListNew.add(historialpedidoListNewHistorialpedidoToAttach);
            }
            historialpedidoListNew = attachedHistorialpedidoListNew;
            producto.setHistorialpedidoList(historialpedidoListNew);
            List<Cestatemporal> attachedCestatemporalListNew = new ArrayList<Cestatemporal>();
            for (Cestatemporal cestatemporalListNewCestatemporalToAttach : cestatemporalListNew) {
                cestatemporalListNewCestatemporalToAttach = em.getReference(cestatemporalListNewCestatemporalToAttach.getClass(), cestatemporalListNewCestatemporalToAttach.getCestatemporalPK());
                attachedCestatemporalListNew.add(cestatemporalListNewCestatemporalToAttach);
            }
            cestatemporalListNew = attachedCestatemporalListNew;
            producto.setCestatemporalList(cestatemporalListNew);
            producto = em.merge(producto);
            for (Historialpedido historialpedidoListNewHistorialpedido : historialpedidoListNew) {
                if (!historialpedidoListOld.contains(historialpedidoListNewHistorialpedido)) {
                    Producto oldProductoOfHistorialpedidoListNewHistorialpedido = historialpedidoListNewHistorialpedido.getProducto();
                    historialpedidoListNewHistorialpedido.setProducto(producto);
                    historialpedidoListNewHistorialpedido = em.merge(historialpedidoListNewHistorialpedido);
                    if (oldProductoOfHistorialpedidoListNewHistorialpedido != null && !oldProductoOfHistorialpedidoListNewHistorialpedido.equals(producto)) {
                        oldProductoOfHistorialpedidoListNewHistorialpedido.getHistorialpedidoList().remove(historialpedidoListNewHistorialpedido);
                        oldProductoOfHistorialpedidoListNewHistorialpedido = em.merge(oldProductoOfHistorialpedidoListNewHistorialpedido);
                    }
                }
            }
            for (Cestatemporal cestatemporalListNewCestatemporal : cestatemporalListNew) {
                if (!cestatemporalListOld.contains(cestatemporalListNewCestatemporal)) {
                    Producto oldProductoOfCestatemporalListNewCestatemporal = cestatemporalListNewCestatemporal.getProducto();
                    cestatemporalListNewCestatemporal.setProducto(producto);
                    cestatemporalListNewCestatemporal = em.merge(cestatemporalListNewCestatemporal);
                    if (oldProductoOfCestatemporalListNewCestatemporal != null && !oldProductoOfCestatemporalListNewCestatemporal.equals(producto)) {
                        oldProductoOfCestatemporalListNewCestatemporal.getCestatemporalList().remove(cestatemporalListNewCestatemporal);
                        oldProductoOfCestatemporalListNewCestatemporal = em.merge(oldProductoOfCestatemporalListNewCestatemporal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getCodProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getCodProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historialpedido> historialpedidoListOrphanCheck = producto.getHistorialpedidoList();
            for (Historialpedido historialpedidoListOrphanCheckHistorialpedido : historialpedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Historialpedido " + historialpedidoListOrphanCheckHistorialpedido + " in its historialpedidoList field has a non-nullable producto field.");
            }
            List<Cestatemporal> cestatemporalListOrphanCheck = producto.getCestatemporalList();
            for (Cestatemporal cestatemporalListOrphanCheckCestatemporal : cestatemporalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Cestatemporal " + cestatemporalListOrphanCheckCestatemporal + " in its cestatemporalList field has a non-nullable producto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
