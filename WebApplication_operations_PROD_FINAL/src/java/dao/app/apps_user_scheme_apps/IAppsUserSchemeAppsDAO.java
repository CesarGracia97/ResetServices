package dao.app.apps_user_scheme_apps;


import java.util.Date;
import java.util.List;

public interface IAppsUserSchemeAppsDAO {

    /**
     * Perform an initial save of a previously unsaved LogGponTv entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 IAppsUserSchemeAppsDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity LogGponTv entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsUserSchemeApps entity);

    /**
     * Delete a persistent  entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 IAppsUserSchemeAppsDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsUserSchemeApps entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsUserSchemeApps entity);

    /**
     * Persist a previously saved  entity and return it or a copy of
     * it to the sender. A copy of the LogGponTv entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 entity = IAppsUserSchemeAppsDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsUserSchemeApps entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsUserSchemeApps update(AppsUserSchemeApps entity);

    public AppsUserSchemeApps findById(Integer id);

    /**
     * Find all LogGponTv entities with a specific property value.
     *
     *
     * @param propertyName the name of the AppsUserSchemeApps property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    public List<AppsUserSchemeApps> findByProperty(String propertyName, Object value
    );

    public List<AppsUserSchemeApps> findByIdAppsScheme(Object Id_Apps_Scheme
    );

    public List<AppsUserSchemeApps> findByAppsIdAppsUser(Object OperationName
    );

      /**
     * Find all AppsUserSchemeApps entities.
     *
     * @return List<LogGpon> all AppsUserSchemeApps entities
     */
    public List<AppsUserSchemeApps> findAll();
}
