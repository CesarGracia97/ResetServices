/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_user_scheme_apps;

import java.util.logging.Level;
import javax.persistence.EntityManager;
import ent.querys.EntityManagerHelper;
import java.util.List;
import javax.persistence.Query;
import ent.querys.EntityManagerHelper;


/**
 *
 * @author jyacelga
 */
public class AppsUserSchemeAppsDAO implements IAppsUserSchemeAppsDAO {
    // property constants

    public static final String ID_APPS_SCHEME_APPS = "id_apps_scheme_apps";
    public static final String ID_APPS_USER = "id_apps_user";


    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved AppsUserSchemeApps entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsUserSchemeAppsDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsUserSchemeApps entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsUserSchemeApps entity) {
        EntityManagerHelper
                .log("saving AppsUserSchemeApps instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent AppsUserSchemeApps entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsUserSchemeAppsDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsUserSchemeApps entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsUserSchemeApps entity) {
        EntityManagerHelper.log("deleting AppsUserSchemeApps instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(AppsUserSchemeApps.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved AppsUserSchemeApps entity and return it or a copy of it
 to the sender. A copy of the AppsUserSchemeApps entity parameter is returned when
 the JPA persistence mechanism has not previously been tracking the
 updated entity. This operation must be performed within the a database
 transaction context for the entity's data to be permanently saved to the
 persistence store, i.e., database. This method uses the
 {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 entity = AppsUserSchemeAppsDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsUserSchemeApps entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsUserSchemeApps update(AppsUserSchemeApps entity) {
        EntityManagerHelper.log("updating AppsUserSchemeApps instance", Level.INFO,
                null);
        try {
            AppsUserSchemeApps result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public AppsUserSchemeApps findById(Integer id) {
        EntityManagerHelper.log("finding AppsUserSchemeApps instance with id: " + id,
                Level.INFO, null);
        try {
            AppsUserSchemeApps instance = getEntityManager().find(AppsUserSchemeApps.class,
                    id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all AppsUserSchemeApps entities with a specific property value.
     *
     * @param propertyName the name of the AppsUserSchemeApps property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    @SuppressWarnings("unchecked")
    public List<AppsUserSchemeApps> findByProperty(String propertyName,
            final Object value) {
        System.out.println("datos de busqueda: "+propertyName);
        EntityManagerHelper.log("finding AppsUserSchemeApps instance with property: "
                + propertyName + ", idAppsScheme: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from AppsUserSchemeApps model where model."
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

    public List<AppsUserSchemeApps> findByIdAppsScheme(Object id_apps_scheme_apps) {
        return findByProperty(ID_APPS_SCHEME_APPS, id_apps_scheme_apps);
    }

    public List<AppsUserSchemeApps> findByAppsIdAppsUser(Object id_apps_user) {
        return findByProperty(ID_APPS_USER, id_apps_user);
    }
  
    /**
     * Find all AppsUserSchemeApps entities.
     *
     * @return List<LogGpon> all AppsUserSchemeApps entities
     */
    @SuppressWarnings("unchecked")
    public List<AppsUserSchemeApps> findAll() {
        EntityManagerHelper.log("finding all AppsUserSchemeApps instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsUserSchemeApps model";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

}

