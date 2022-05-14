package repositories;

import entities.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class DataRepository<T extends AbstractEntity>
{
	protected EntityManager entityManager;

	protected DataRepository(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public void persist(T entity)
	{
		try
		{
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (Exception exception)
		{
			System.out.println("The entity with the name: " + entity.getName() + " wasn't added to the database.");
			entityManager.getTransaction().rollback();
		}
	}

	public abstract T findById(Integer id);

	public abstract T findByName(String name);

	public abstract List<T> findAll();

	public abstract long count();
}
