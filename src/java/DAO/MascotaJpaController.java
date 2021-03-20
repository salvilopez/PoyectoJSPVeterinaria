/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Mascota;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class MascotaJpaController implements Serializable {

    public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario codUsuario = mascota.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                mascota.setCodUsuario(codUsuario);
            }
            em.persist(mascota);
            if (codUsuario != null) {
                codUsuario.getMascotaList().add(mascota);
                codUsuario = em.merge(codUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota persistentMascota = em.find(Mascota.class, mascota.getCodMascota());
            Usuario codUsuarioOld = persistentMascota.getCodUsuario();
            Usuario codUsuarioNew = mascota.getCodUsuario();
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                mascota.setCodUsuario(codUsuarioNew);
            }
            mascota = em.merge(mascota);
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getMascotaList().remove(mascota);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getMascotaList().add(mascota);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mascota.getCodMascota();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getCodMascota();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.", enfe);
            }
            Usuario codUsuario = mascota.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getMascotaList().remove(mascota);
                codUsuario = em.merge(codUsuario);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
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

    public Mascota findMascota(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
