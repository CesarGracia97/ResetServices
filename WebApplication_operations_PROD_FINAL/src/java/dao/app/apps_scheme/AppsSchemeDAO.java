package dao.app.apps_scheme;

import dao.app.apps_user.*;
import ent.querys.EntityManagerHelper;


import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 AppsScheme entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.app.tv.log_GponTv.LogGpon
 * @author MyEclipse Persistence Tools
 */
public class AppsSchemeDAO implements IAppsSchemeDAO {
    // property constants

    public static final String DESCRIPTION_SCHEME = "description_scheme";
    public static final String NAME_SCHEME = "name_scheme";


    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved AppsScheme entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsSchemeDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsScheme entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsScheme entity) {
        EntityManagerHelper
                .log("saving AppsScheme instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent AppsScheme entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsSchemeDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsScheme entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsScheme entity) {
        EntityManagerHelper.log("deleting AppsScheme instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(AppsScheme.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved AppsScheme entity and return it or a copy of it
 to the sender. A copy of the AppsScheme entity parameter is returned when
 the JPA persistence mechanism has not previously been tracking the
 updated entity. This operation must be performed within the a database
 transaction context for the entity's data to be permanently saved to the
 persistence store, i.e., database. This method uses the
 {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 entity = AppsSchemeDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsScheme entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsScheme update(AppsScheme entity) {
        EntityManagerHelper.log("updating AppsScheme instance", Level.INFO,
                null);
        try {
            AppsScheme result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public AppsScheme findById(Integer id) {
        EntityManagerHelper.log("finding AppsScheme instance with id: " + id,
                Level.INFO, null);
        try {
            AppsScheme instance = getEntityManager().find(AppsScheme.class,
                    id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all AppsScheme entities with a specific property value.
     *
     * @param propertyName the name of the AppsScheme property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    @SuppressWarnings("unchecked")
    public List<AppsScheme> findByProperty(String propertyName,
            final Object value) {
        EntityManagerHelper.log("finding AppsScheme instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from AppsScheme model where model."
                    + propertyName + "= :propertyValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("propertyValue", value);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property name failed",
                    Level.SEVERE, re);
            throw re;
        }
    }

    public List<AppsScheme> findByAppsDescriptionScheme(Object description_scheme) {
        return findByProperty(DESCRIPTION_SCHEME, description_scheme);
    }

    public List<AppsScheme> findByAppsSchemeName(Object name_scheme) {
        return findByProperty(NAME_SCHEME, name_scheme);
    }
  
    /**
     * Find all AppsScheme entities.
     *
     * @return List<LogGpon> all AppsScheme entities
     */
    @SuppressWarnings("unchecked")
    public List<AppsScheme> findAll() {
        EntityManagerHelper.log("finding all AppsScheme instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsScheme model";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }
    
        public List<AppsScheme> findId( String id) {
        EntityManagerHelper.log("finding all AppsScheme instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsScheme model where id=?";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }
    
        public List<AppsScheme> findDescriptionScheme(int id) {
        EntityManagerHelper.log("finding all AppsScheme instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsScheme model where id="+id;
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

}
