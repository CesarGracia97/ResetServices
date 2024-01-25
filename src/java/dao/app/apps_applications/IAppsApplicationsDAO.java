package dao.app.apps_applications;

import dao.app.apps_scheme.*;
import dao.app.apps_user.*;
import java.util.Date;
import java.util.List;

/**
 * Interface for LogTvPagadaDAO.
 *
 * @author MyEclipse Persistence Tools
 */
public interface IAppsApplicationsDAO {

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
 IAppsApplicationsDAO.save(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity LogGponTv entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(AppsApplications entity);

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
 IAppsApplicationsDAO.delete(entity);
 EntityManagerHelper.commit();
 entity = null;
 </pre>
     *
     * @param entity AppsApplications entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(AppsApplications entity);

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
 entity = IAppsApplicationsDAO.update(entity);
 EntityManagerHelper.commit();
 </pre>
     *
     * @param entity AppsApplications entity to update
     * @returns LogGpon the persisted LogGpon entity instance, may not be the
     * same
     * @throws RuntimeException if the operation fails
     */
    public AppsApplications update(AppsApplications entity);

    public AppsApplications findById(Integer id);

    /**
     * Find all LogGponTv entities with a specific property value.
     *
     *
     * @param propertyName the name of the AppsApplications property to query
     * @param value the property value to match
     * @return List<LogGpon> found by query
     */
    public List<AppsApplications> findByProperty(String propertyName, Object value
    );

    public List<AppsApplications> findByAppsNameApplication(Object Name_Application
    );

    public List<AppsApplications> findByAppsDescriptionApplication(Object OperationName
    );

      /**
     * Find all AppsApplications entities.
     *
     * @return List<LogGpon> all AppsApplications entities
     */
    public List<AppsApplications> findAll();
}
