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
import DTO.Mascota;
import DTO.Cita;
import DTO.Cestatemporal;
import DTO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author usuario
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getHistorialpedidoList() == null) {
            usuario.setHistorialpedidoList(new ArrayList<Historialpedido>());
        }
        if (usuario.getMascotaList() == null) {
            usuario.setMascotaList(new ArrayList<Mascota>());
        }
        if (usuario.getCitaList() == null) {
            usuario.setCitaList(new ArrayList<Cita>());
        }
        if (usuario.getCestatemporalList() == null) {
            usuario.setCestatemporalList(new ArrayList<Cestatemporal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historialpedido> attachedHistorialpedidoList = new ArrayList<Historialpedido>();
            for (Historialpedido historialpedidoListHistorialpedidoToAttach : usuario.getHistorialpedidoList()) {
                historialpedidoListHistorialpedidoToAttach = em.getReference(historialpedidoListHistorialpedidoToAttach.getClass(), historialpedidoListHistorialpedidoToAttach.getHistorialpedidoPK());
                attachedHistorialpedidoList.add(historialpedidoListHistorialpedidoToAttach);
            }
            usuario.setHistorialpedidoList(attachedHistorialpedidoList);
            List<Mascota> attachedMascotaList = new ArrayList<Mascota>();
            for (Mascota mascotaListMascotaToAttach : usuario.getMascotaList()) {
                mascotaListMascotaToAttach = em.getReference(mascotaListMascotaToAttach.getClass(), mascotaListMascotaToAttach.getCodMascota());
                attachedMascotaList.add(mascotaListMascotaToAttach);
            }
            usuario.setMascotaList(attachedMascotaList);
            List<Cita> attachedCitaList = new ArrayList<Cita>();
            for (Cita citaListCitaToAttach : usuario.getCitaList()) {
                citaListCitaToAttach = em.getReference(citaListCitaToAttach.getClass(), citaListCitaToAttach.getCodCita());
                attachedCitaList.add(citaListCitaToAttach);
            }
            usuario.setCitaList(attachedCitaList);
            List<Cestatemporal> attachedCestatemporalList = new ArrayList<Cestatemporal>();
            for (Cestatemporal cestatemporalListCestatemporalToAttach : usuario.getCestatemporalList()) {
                cestatemporalListCestatemporalToAttach = em.getReference(cestatemporalListCestatemporalToAttach.getClass(), cestatemporalListCestatemporalToAttach.getCestatemporalPK());
                attachedCestatemporalList.add(cestatemporalListCestatemporalToAttach);
            }
            usuario.setCestatemporalList(attachedCestatemporalList);
            em.persist(usuario);
            for (Historialpedido historialpedidoListHistorialpedido : usuario.getHistorialpedidoList()) {
                Usuario oldUsuarioOfHistorialpedidoListHistorialpedido = historialpedidoListHistorialpedido.getUsuario();
                historialpedidoListHistorialpedido.setUsuario(usuario);
                historialpedidoListHistorialpedido = em.merge(historialpedidoListHistorialpedido);
                if (oldUsuarioOfHistorialpedidoListHistorialpedido != null) {
                    oldUsuarioOfHistorialpedidoListHistorialpedido.getHistorialpedidoList().remove(historialpedidoListHistorialpedido);
                    oldUsuarioOfHistorialpedidoListHistorialpedido = em.merge(oldUsuarioOfHistorialpedidoListHistorialpedido);
                }
            }
            for (Mascota mascotaListMascota : usuario.getMascotaList()) {
                Usuario oldCodUsuarioOfMascotaListMascota = mascotaListMascota.getCodUsuario();
                mascotaListMascota.setCodUsuario(usuario);
                mascotaListMascota = em.merge(mascotaListMascota);
                if (oldCodUsuarioOfMascotaListMascota != null) {
                    oldCodUsuarioOfMascotaListMascota.getMascotaList().remove(mascotaListMascota);
                    oldCodUsuarioOfMascotaListMascota = em.merge(oldCodUsuarioOfMascotaListMascota);
                }
            }
            for (Cita citaListCita : usuario.getCitaList()) {
                Usuario oldCodUsuarioOfCitaListCita = citaListCita.getCodUsuario();
                citaListCita.setCodUsuario(usuario);
                citaListCita = em.merge(citaListCita);
                if (oldCodUsuarioOfCitaListCita != null) {
                    oldCodUsuarioOfCitaListCita.getCitaList().remove(citaListCita);
                    oldCodUsuarioOfCitaListCita = em.merge(oldCodUsuarioOfCitaListCita);
                }
            }
            for (Cestatemporal cestatemporalListCestatemporal : usuario.getCestatemporalList()) {
                Usuario oldUsuarioOfCestatemporalListCestatemporal = cestatemporalListCestatemporal.getUsuario();
                cestatemporalListCestatemporal.setUsuario(usuario);
                cestatemporalListCestatemporal = em.merge(cestatemporalListCestatemporal);
                if (oldUsuarioOfCestatemporalListCestatemporal != null) {
                    oldUsuarioOfCestatemporalListCestatemporal.getCestatemporalList().remove(cestatemporalListCestatemporal);
                    oldUsuarioOfCestatemporalListCestatemporal = em.merge(oldUsuarioOfCestatemporalListCestatemporal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCodUsuario());
            List<Historialpedido> historialpedidoListOld = persistentUsuario.getHistorialpedidoList();
            List<Historialpedido> historialpedidoListNew = usuario.getHistorialpedidoList();
            List<Mascota> mascotaListOld = persistentUsuario.getMascotaList();
            List<Mascota> mascotaListNew = usuario.getMascotaList();
            List<Cita> citaListOld = persistentUsuario.getCitaList();
            List<Cita> citaListNew = usuario.getCitaList();
            List<Cestatemporal> cestatemporalListOld = persistentUsuario.getCestatemporalList();
            List<Cestatemporal> cestatemporalListNew = usuario.getCestatemporalList();
            List<String> illegalOrphanMessages = null;
            for (Historialpedido historialpedidoListOldHistorialpedido : historialpedidoListOld) {
                if (!historialpedidoListNew.contains(historialpedidoListOldHistorialpedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialpedido " + historialpedidoListOldHistorialpedido + " since its usuario field is not nullable.");
                }
            }
            for (Mascota mascotaListOldMascota : mascotaListOld) {
                if (!mascotaListNew.contains(mascotaListOldMascota)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mascota " + mascotaListOldMascota + " since its codUsuario field is not nullable.");
                }
            }
            for (Cita citaListOldCita : citaListOld) {
                if (!citaListNew.contains(citaListOldCita)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cita " + citaListOldCita + " since its codUsuario field is not nullable.");
                }
            }
            for (Cestatemporal cestatemporalListOldCestatemporal : cestatemporalListOld) {
                if (!cestatemporalListNew.contains(cestatemporalListOldCestatemporal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cestatemporal " + cestatemporalListOldCestatemporal + " since its usuario field is not nullable.");
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
            usuario.setHistorialpedidoList(historialpedidoListNew);
            List<Mascota> attachedMascotaListNew = new ArrayList<Mascota>();
            for (Mascota mascotaListNewMascotaToAttach : mascotaListNew) {
                mascotaListNewMascotaToAttach = em.getReference(mascotaListNewMascotaToAttach.getClass(), mascotaListNewMascotaToAttach.getCodMascota());
                attachedMascotaListNew.add(mascotaListNewMascotaToAttach);
            }
            mascotaListNew = attachedMascotaListNew;
            usuario.setMascotaList(mascotaListNew);
            List<Cita> attachedCitaListNew = new ArrayList<Cita>();
            for (Cita citaListNewCitaToAttach : citaListNew) {
                citaListNewCitaToAttach = em.getReference(citaListNewCitaToAttach.getClass(), citaListNewCitaToAttach.getCodCita());
                attachedCitaListNew.add(citaListNewCitaToAttach);
            }
            citaListNew = attachedCitaListNew;
            usuario.setCitaList(citaListNew);
            List<Cestatemporal> attachedCestatemporalListNew = new ArrayList<Cestatemporal>();
            for (Cestatemporal cestatemporalListNewCestatemporalToAttach : cestatemporalListNew) {
                cestatemporalListNewCestatemporalToAttach = em.getReference(cestatemporalListNewCestatemporalToAttach.getClass(), cestatemporalListNewCestatemporalToAttach.getCestatemporalPK());
                attachedCestatemporalListNew.add(cestatemporalListNewCestatemporalToAttach);
            }
            cestatemporalListNew = attachedCestatemporalListNew;
            usuario.setCestatemporalList(cestatemporalListNew);
            usuario = em.merge(usuario);
            for (Historialpedido historialpedidoListNewHistorialpedido : historialpedidoListNew) {
                if (!historialpedidoListOld.contains(historialpedidoListNewHistorialpedido)) {
                    Usuario oldUsuarioOfHistorialpedidoListNewHistorialpedido = historialpedidoListNewHistorialpedido.getUsuario();
                    historialpedidoListNewHistorialpedido.setUsuario(usuario);
                    historialpedidoListNewHistorialpedido = em.merge(historialpedidoListNewHistorialpedido);
                    if (oldUsuarioOfHistorialpedidoListNewHistorialpedido != null && !oldUsuarioOfHistorialpedidoListNewHistorialpedido.equals(usuario)) {
                        oldUsuarioOfHistorialpedidoListNewHistorialpedido.getHistorialpedidoList().remove(historialpedidoListNewHistorialpedido);
                        oldUsuarioOfHistorialpedidoListNewHistorialpedido = em.merge(oldUsuarioOfHistorialpedidoListNewHistorialpedido);
                    }
                }
            }
            for (Mascota mascotaListNewMascota : mascotaListNew) {
                if (!mascotaListOld.contains(mascotaListNewMascota)) {
                    Usuario oldCodUsuarioOfMascotaListNewMascota = mascotaListNewMascota.getCodUsuario();
                    mascotaListNewMascota.setCodUsuario(usuario);
                    mascotaListNewMascota = em.merge(mascotaListNewMascota);
                    if (oldCodUsuarioOfMascotaListNewMascota != null && !oldCodUsuarioOfMascotaListNewMascota.equals(usuario)) {
                        oldCodUsuarioOfMascotaListNewMascota.getMascotaList().remove(mascotaListNewMascota);
                        oldCodUsuarioOfMascotaListNewMascota = em.merge(oldCodUsuarioOfMascotaListNewMascota);
                    }
                }
            }
            for (Cita citaListNewCita : citaListNew) {
                if (!citaListOld.contains(citaListNewCita)) {
                    Usuario oldCodUsuarioOfCitaListNewCita = citaListNewCita.getCodUsuario();
                    citaListNewCita.setCodUsuario(usuario);
                    citaListNewCita = em.merge(citaListNewCita);
                    if (oldCodUsuarioOfCitaListNewCita != null && !oldCodUsuarioOfCitaListNewCita.equals(usuario)) {
                        oldCodUsuarioOfCitaListNewCita.getCitaList().remove(citaListNewCita);
                        oldCodUsuarioOfCitaListNewCita = em.merge(oldCodUsuarioOfCitaListNewCita);
                    }
                }
            }
            for (Cestatemporal cestatemporalListNewCestatemporal : cestatemporalListNew) {
                if (!cestatemporalListOld.contains(cestatemporalListNewCestatemporal)) {
                    Usuario oldUsuarioOfCestatemporalListNewCestatemporal = cestatemporalListNewCestatemporal.getUsuario();
                    cestatemporalListNewCestatemporal.setUsuario(usuario);
                    cestatemporalListNewCestatemporal = em.merge(cestatemporalListNewCestatemporal);
                    if (oldUsuarioOfCestatemporalListNewCestatemporal != null && !oldUsuarioOfCestatemporalListNewCestatemporal.equals(usuario)) {
                        oldUsuarioOfCestatemporalListNewCestatemporal.getCestatemporalList().remove(cestatemporalListNewCestatemporal);
                        oldUsuarioOfCestatemporalListNewCestatemporal = em.merge(oldUsuarioOfCestatemporalListNewCestatemporal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getCodUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCodUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historialpedido> historialpedidoListOrphanCheck = usuario.getHistorialpedidoList();
            for (Historialpedido historialpedidoListOrphanCheckHistorialpedido : historialpedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Historialpedido " + historialpedidoListOrphanCheckHistorialpedido + " in its historialpedidoList field has a non-nullable usuario field.");
            }
            List<Mascota> mascotaListOrphanCheck = usuario.getMascotaList();
            for (Mascota mascotaListOrphanCheckMascota : mascotaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mascota " + mascotaListOrphanCheckMascota + " in its mascotaList field has a non-nullable codUsuario field.");
            }
            List<Cita> citaListOrphanCheck = usuario.getCitaList();
            for (Cita citaListOrphanCheckCita : citaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cita " + citaListOrphanCheckCita + " in its citaList field has a non-nullable codUsuario field.");
            }
            List<Cestatemporal> cestatemporalListOrphanCheck = usuario.getCestatemporalList();
            for (Cestatemporal cestatemporalListOrphanCheckCestatemporal : cestatemporalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Cestatemporal " + cestatemporalListOrphanCheckCestatemporal + " in its cestatemporalList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
