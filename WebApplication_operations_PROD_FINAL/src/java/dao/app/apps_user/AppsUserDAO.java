package dao.app.apps_user;

import ent.querys.EntityManagerHelper;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 AppsUser entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.app.tv.log_GponTv.LogGpon
 * @author MyEclipse Persistence Tools
 */
public class AppsUserDAO implements IAppsUserDAO {
    // property constants

    public static final String APPS_USER = "apps_user";
    public static final String APPS_FULL_NAMES = "apps_full_names";
    public static final String APPS_DESCRITION_USER = "apps_descrition_user";


    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved AppsUser entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsUserDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsUser entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsUser entity) {
        EntityManagerHelper
                .log("saving AppsUser instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent AppsUser entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsUserDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsUser entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsUser entity) {
        EntityManagerHelper.log("deleting AppsUser instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(AppsUser.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved AppsUser entity and return it or a copy of it
 to the sender. A copy of the AppsUser entity parameter is returned when
 the JPA persistence mechanism has not previously been tracking the
 updated entity. This operation must be performed within the a database
 transaction context for the entity's data to be permanently saved to the
 persistence store, i.e., database. This method uses the
 {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 entity = AppsUserDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsUser entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsUser update(AppsUser entity) {
        EntityManagerHelper.log("updating AppsUser instance", Level.INFO,
                null);
        try {
            AppsUser result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public AppsUser findById(Integer id) {
        EntityManagerHelper.log("finding AppsUser instance with id: " + id,
                Level.INFO, null);
        try {
            AppsUser instance = getEntityManager().find(AppsUser.class,
                    id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all AppsUser entities with a specific property value.
     *
     * @param propertyName the name of the AppsUser property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    @SuppressWarnings("unchecked")
    public List<AppsUser> findByProperty(String propertyName,
            final Object value) {
        EntityManagerHelper.log("finding AppsUser instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from AppsUser model where model."
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

    public List<AppsUser> findByAppsUser(Object apps_user) {
        return findByProperty(APPS_USER, apps_user);
    }

    public List<AppsUser> findByAppsFullNames(Object apps_full_names) {
        return findByProperty(APPS_FULL_NAMES, apps_full_names);
    }

    public List<AppsUser> findByAppsDescritionUser(Object apps_descrition_user) {
        return findByProperty(APPS_DESCRITION_USER, apps_descrition_user);
    }
  
    /**
     * Find all AppsUser entities.
     *
     * @return List<LogGpon> all AppsUser entities
     */
    @SuppressWarnings("unchecked")
    public List<AppsUser> findAll() {
        EntityManagerHelper.log("finding all AppsUser instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsUser model";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

}
