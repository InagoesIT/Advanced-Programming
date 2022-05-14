package repositories;

import entities.City;
import entities.Continent;

import javax.persistence.EntityManager;
import java.util.List;

public class ContinentRepository extends DataRepository<Continent>
{
	public ContinentRepository(EntityManager entityManager)
	{
		super(entityManager);
	}

	public Continent findById(Integer id)
	{
//		return (Continent) entityManager.createQuery(
//						"select continent from Continent continent where continent.id=?1")
//				.setParameter(1, id).getSingleResult();
		try
		{
			return (Continent) entityManager.createNamedQuery("Continent.findById")
					.setParameter("1", id)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	public Continent findByName(String name)
	{
		try
		{
			return (Continent) entityManager.createNamedQuery("Continent.findByName")
					.setParameter("1", name)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	@Override
	public List<Continent> findAll()
	{
		return entityManager.createNamedQuery("Continent.findAll")
				.getResultList();
	}

	@Override
	public long count()
	{
		return entityManager.createNamedQuery("Continent.findAll")
				.getResultList().size();
	}
}
