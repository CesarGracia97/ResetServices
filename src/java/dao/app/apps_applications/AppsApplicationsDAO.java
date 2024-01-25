package dao.app.apps_applications;

import dao.app.apps_scheme.*;
import dao.app.apps_user.*;
import ent.querys.EntityManagerHelper;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppsApplications entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 *
 * @see com.app.tv.log_GponTv.LogGpon
 * @author MyEclipse Persistence Tools
 */
public class AppsApplicationsDAO implements IAppsApplicationsDAO {
    // property constants

    public static final String NAME_APPLICATION = "name_application";
    public static final String DESCRIPTION_APPLICATION = "description_application";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved AppsApplications entity.
     * All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * AppsApplicationsDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity AppsApplications entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsApplications entity) {
        EntityManagerHelper
                .log("saving AppsApplications instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent AppsApplications entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * AppsApplicationsDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity AppsApplications entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsApplications entity) {
        EntityManagerHelper.log("deleting AppsApplications instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(AppsApplications.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved AppsApplications entity and return it or a
     * copy of it to the sender. A copy of the AppsApplications entity parameter
     * is returned when the JPA persistence mechanism has not previously been
     * tracking the updated entity. This operation must be performed within the
     * a database transaction context for the entity's data to be permanently
     * saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = AppsApplicationsDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity AppsApplications entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsApplications update(AppsApplications entity) {
        EntityManagerHelper.log("updating AppsApplications instance", Level.INFO,
                null);
        try {
            AppsApplications result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public AppsApplications findById(Integer id) {
        EntityManagerHelper.log("finding AppsApplications instance with id: " + id, Level.INFO, null);
        try {
            AppsApplications instance = getEntityManager().find(AppsApplications.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all AppsApplications entities with a specific property value.
     *
     * @param propertyName the name of the AppsApplications property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    @SuppressWarnings("unchecked")
    public List<AppsApplications> findByProperty(String propertyName,
            final Object value) {
        EntityManagerHelper.log("finding AppsApplications instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from AppsApplications model where model."
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

    public List<AppsApplications> findByAppsNameApplication(Object name_application) {
        return findByProperty(NAME_APPLICATION, name_application);
    }

    public List<AppsApplications> findByAppsDescriptionApplication(Object description_application) {
        return findByProperty(DESCRIPTION_APPLICATION, description_application);
    }

    /**
     * Find all AppsApplications entities.
     *
     * @return List<LogGpon> all AppsApplications entities
     */
    @SuppressWarnings("unchecked")
    public List<AppsApplications> findAll() {
        EntityManagerHelper.log("finding all AppsApplications instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsApplications model";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

}
