package repositories;

import entities.City;
import entities.Continent;
import entities.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepository
{
	private final EntityManager entityManager;

	public CountryRepository(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public List<Country> findByContinent(Continent continent)
	{
		return entityManager.createNamedQuery("Country.findByContinent")
				.setParameter("1", continent.getId())
				.getResultList();
	}

	public Country findById(int id)
	{
		return (Country) entityManager.createQuery(
						"select country from City country where country.id=?1")
				.setParameter(1, id).getSingleResult();
	}

	public Country findByName(String name)
	{
		return (Country) entityManager.createNamedQuery("Country.findByName")
				.setParameter("1", name)
				.getResultList();
	}
}
