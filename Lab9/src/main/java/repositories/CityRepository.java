package repositories;

import entities.City;
import entities.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CityRepository extends DataRepository<City>
{
	public CityRepository(EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public City findById(Integer id)
	{
		try
		{
			return (City) entityManager.createNamedQuery("City.findById")
					.setParameter("1", id)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	@Override
	public City findByName(String name)
	{
		try
		{
			return (City) entityManager.createNamedQuery("City.findByName")
					.setParameter("1", name)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	@Override
	public List<City> findAll()
	{
		return entityManager.createNamedQuery("City.findAll")
				.getResultList();
	}

	@Override
	public long count()
	{
		return entityManager.createNamedQuery("City.findAll")
				.getResultList().size();
	}

	public List<City> findByCountry(Country country)
	{
		return entityManager.createNamedQuery("City.findByCountry")
				.setParameter("1", country.getId())
				.getResultList();
	}

	public List<City> findListByName(String name)
	{
		return entityManager.createNamedQuery("City.findByName")
				.setParameter("1", name)
				.getResultList();
	}
}
