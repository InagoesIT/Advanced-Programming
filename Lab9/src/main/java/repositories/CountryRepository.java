package repositories;

import entities.City;
import entities.Continent;
import entities.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepository extends DataRepository<Country>
{
	public CountryRepository(EntityManager entityManager)
	{
		super(entityManager);
	}

	@Override
	public Country findById(Integer id)
	{
		try
		{
			return (Country) entityManager.createNamedQuery("Country.findById")
					.setParameter("1", id)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	@Override
	public Country findByName(String name)
	{
		try
		{
			return (Country) entityManager.createNamedQuery("Country.findByName")
					.setParameter("1", name)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}

	@Override
	public List<Country> findAll()
	{
		return entityManager.createNamedQuery("Country.findAll")
				.getResultList();
	}

	@Override
	public long count()
	{
		return entityManager.createNamedQuery("Country.findAll")
				.getResultList().size();
	}

	public List<Country> findByContinent(Continent continent)
	{
		return entityManager.createNamedQuery("Country.findByContinent")
				.setParameter("1", continent.getId())
				.getResultList();
	}

	public Country findByCode(String code)
	{
		try
		{
			return (Country) entityManager.createNamedQuery("Country.findByCode")
					.setParameter("1", code)
					.getResultList().get(0);
		}
		catch (ArrayIndexOutOfBoundsException exception)
		{
			return null;
		}
	}
}
