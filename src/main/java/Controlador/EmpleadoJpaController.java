/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidades.Empleado;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PABLO
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    private static EmpleadoJpaController instancia;
    
    public  EmpleadoJpaController (){
        emf = Persistence.createEntityManagerFactory("TpFinalPU");
    }
    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

  
    public long calcularEdad(String fecha) {
		//int edad = 0;
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaActual = LocalDate.now();
		LocalDate nacimiento = LocalDate.parse(fecha,fmt);
		long edad= ChronoUnit.YEARS.between(nacimiento, fechaActual); 
		return edad;
	}
    
    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empleado = em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleado.getNdeLegajo();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getNdeLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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
    
    public List<Empleado> findEmpleadoEntitiesFilter(String valor) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

            cq.select(cq.from(Empleado.class));
            //cq.where(em.getCriteriaBuilder().like(c.get("apellidos"),"%"+ valor +"%"));
            String consulta = "SELECT empleado FROM empleado e WHERE e.apellidos like '%"+valor+"%' or e.cuit like '%"+valor+"%' ";
            Query q = em.createQuery(consulta);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }

//////////////////////
    public List<Empleado> listarEmpleadosSueldo(Double sueld) {
        EntityManager em = getEntityManager();
        try {
            
            String jpql = "SELECT e FROM Empleado e WHERE e.sueldo >:varSueldo";
            Query q = em.createQuery(jpql);
            q.setParameter("varSueldo", sueld);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Empleado> listarEmpleados(String val) {
        EntityManager em = getEntityManager();
        try {
            
            String jpql = "SELECT e FROM Empleado e WHERE e.apellidos LIKE '%"+val+"%' OR e.cuit LIKE '%"+val+"%'";
            Query q = em.createQuery(jpql);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
public List<Empleado> actualizarEmpleados(String cuit,int codPro) {
        EntityManager em = getEntityManager();
        try {
            
            String jpql = "UPDATE Empleado SET id_proyecto =:varCod WHERE cuit = '"+cuit+"'";
            Query q = em.createQuery(jpql);
            q.setParameter("varCod", codPro);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    
    
    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
