package dao.app.apps_scheme;

import dao.app.apps_user.*;
import java.util.Date;
import java.util.List;

/**
 * Interface for LogTvPagadaDAO.
 *
 * @author MyEclipse Persistence Tools
 */
public interface IAppsSchemeDAO {

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
 IAppsSchemeDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity LogGponTv entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsScheme entity);

    /**
     * Delete a persistent LogGponTv entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
 EntityManagerHelper.beginTransaction();
 IAppsSchemeDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsScheme entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsScheme entity);

    /**
     * Persist a previously saved LogGponTv entity and return it or a copy of
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
 entity = IAppsSchemeDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsScheme entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsScheme update(AppsScheme entity);

    public AppsScheme findById(Integer id);

    /**
     * Find all LogGponTv entities with a specific property value.
     *
     *
     * @param propertyName the name of the AppsScheme property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    public List<AppsScheme> findByProperty(String propertyName, Object value
    );

    public List<AppsScheme> findByAppsSchemeName(Object Name_Scheme
    );

    public List<AppsScheme> findByAppsDescriptionScheme(Object OperationName
    );

      /**
     * Find all AppsScheme entities.
     *
     * @return List<LogGpon> all AppsScheme entities
     */
    public List<AppsScheme> findAll();
}
