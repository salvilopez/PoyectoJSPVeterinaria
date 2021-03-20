/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Historialpedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getHistorialpedidoList() == null) {
            factura.setHistorialpedidoList(new ArrayList<Historialpedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historialpedido> attachedHistorialpedidoList = new ArrayList<Historialpedido>();
            for (Historialpedido historialpedidoListHistorialpedidoToAttach : factura.getHistorialpedidoList()) {
                historialpedidoListHistorialpedidoToAttach = em.getReference(historialpedidoListHistorialpedidoToAttach.getClass(), historialpedidoListHistorialpedidoToAttach.getHistorialpedidoPK());
                attachedHistorialpedidoList.add(historialpedidoListHistorialpedidoToAttach);
            }
            factura.setHistorialpedidoList(attachedHistorialpedidoList);
            em.persist(factura);
            for (Historialpedido historialpedidoListHistorialpedido : factura.getHistorialpedidoList()) {
                Factura oldFacturaOfHistorialpedidoListHistorialpedido = historialpedidoListHistorialpedido.getFactura();
                historialpedidoListHistorialpedido.setFactura(factura);
                historialpedidoListHistorialpedido = em.merge(historialpedidoListHistorialpedido);
                if (oldFacturaOfHistorialpedidoListHistorialpedido != null) {
                    oldFacturaOfHistorialpedidoListHistorialpedido.getHistorialpedidoList().remove(historialpedidoListHistorialpedido);
                    oldFacturaOfHistorialpedidoListHistorialpedido = em.merge(oldFacturaOfHistorialpedidoListHistorialpedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getCodPedido());
            List<Historialpedido> historialpedidoListOld = persistentFactura.getHistorialpedidoList();
            List<Historialpedido> historialpedidoListNew = factura.getHistorialpedidoList();
            List<String> illegalOrphanMessages = null;
            for (Historialpedido historialpedidoListOldHistorialpedido : historialpedidoListOld) {
                if (!historialpedidoListNew.contains(historialpedidoListOldHistorialpedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialpedido " + historialpedidoListOldHistorialpedido + " since its factura field is not nullable.");
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
            factura.setHistorialpedidoList(historialpedidoListNew);
            factura = em.merge(factura);
            for (Historialpedido historialpedidoListNewHistorialpedido : historialpedidoListNew) {
                if (!historialpedidoListOld.contains(historialpedidoListNewHistorialpedido)) {
                    Factura oldFacturaOfHistorialpedidoListNewHistorialpedido = historialpedidoListNewHistorialpedido.getFactura();
                    historialpedidoListNewHistorialpedido.setFactura(factura);
                    historialpedidoListNewHistorialpedido = em.merge(historialpedidoListNewHistorialpedido);
                    if (oldFacturaOfHistorialpedidoListNewHistorialpedido != null && !oldFacturaOfHistorialpedidoListNewHistorialpedido.equals(factura)) {
                        oldFacturaOfHistorialpedidoListNewHistorialpedido.getHistorialpedidoList().remove(historialpedidoListNewHistorialpedido);
                        oldFacturaOfHistorialpedidoListNewHistorialpedido = em.merge(oldFacturaOfHistorialpedidoListNewHistorialpedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getCodPedido();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getCodPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historialpedido> historialpedidoListOrphanCheck = factura.getHistorialpedidoList();
            for (Historialpedido historialpedidoListOrphanCheckHistorialpedido : historialpedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Historialpedido " + historialpedidoListOrphanCheckHistorialpedido + " in its historialpedidoList field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
