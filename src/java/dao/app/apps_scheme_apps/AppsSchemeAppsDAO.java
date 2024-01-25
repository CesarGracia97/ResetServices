package dao.app.apps_scheme_apps;




import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ent.querys.EntityManagerHelper;


public class AppsSchemeAppsDAO implements IAppsSchemeAppsDAO {
    // property constants

    public static final String ID_APPS_SCHEME = "id_apps_scheme";
    public static final String ID_APPS_USER = "id_apps_apps";


    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved AppsSchemeApps entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsSchemeAppsDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsSchemeApps entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsSchemeApps entity) {
        EntityManagerHelper
                .log("saving AppsSchemeApps instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent AppsSchemeApps entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 AppsSchemeAppsDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsSchemeApps entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsSchemeApps entity) {
        EntityManagerHelper.log("deleting AppsSchemeApps instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(AppsSchemeApps.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved AppsSchemeApps entity and return it or a copy of it
 to the sender. A copy of the AppsSchemeApps entity parameter is returned when
 the JPA persistence mechanism has not previously been tracking the
 updated entity. This operation must be performed within the a database
 transaction context for the entity's data to be permanently saved to the
 persistence store, i.e., database. This method uses the
 {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 entity = AppsSchemeAppsDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsSchemeApps entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsSchemeApps update(AppsSchemeApps entity) {
        EntityManagerHelper.log("updating AppsSchemeApps instance", Level.INFO,
                null);
        try {
            AppsSchemeApps result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public AppsSchemeApps findById(Integer id) {
        EntityManagerHelper.log("finding AppsSchemeApps instance with id: " + id,
                Level.INFO, null);
        try {
            AppsSchemeApps instance = getEntityManager().find(AppsSchemeApps.class,
                    id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all AppsSchemeApps entities with a specific property value.
     *
     * @param propertyName the name of the AppsSchemeApps property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    @SuppressWarnings("unchecked")
    public List<AppsSchemeApps> findByProperty(String propertyName,
            final Object value) {
        EntityManagerHelper.log("finding AppsSchemeApps instance with property: "
                + propertyName + ", idAppsScheme: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from AppsSchemeApps model where model."
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

    public List<AppsSchemeApps> findByIdAppsScheme(Object id_apps_scheme) {
        return findByProperty(ID_APPS_SCHEME, id_apps_scheme);
    }

    public List<AppsSchemeApps> findByAppsIdAppsUser(Object id_apps_user) {
        return findByProperty(ID_APPS_USER, id_apps_user);
    }
  
    /**
     * Find all AppsSchemeApps entities.
     *
     * @return List<LogGpon> all AppsSchemeApps entities
     */
    @SuppressWarnings("unchecked")
    public List<AppsSchemeApps> findAll() {
        EntityManagerHelper.log("finding all AppsApplicationUser instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from AppsApplicationUser model";
            Query query = getEntityManager().createQuery(queryString);
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

}
