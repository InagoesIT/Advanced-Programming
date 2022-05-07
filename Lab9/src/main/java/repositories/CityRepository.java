package repositories;

import entities.City;
import entities.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CityRepository
{
	private final EntityManager entityManager;

	public CityRepository(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public List<City> findByCountry(Country country)
	{
		return entityManager.createNamedQuery("City.findByCountry")
				.setParameter("1", country.getId())
				.getResultList();
	}

	public City findById(int id)
	{
		return (City) entityManager.createQuery(
					"select city from City city where city.id=?1")
				.setParameter(1, id).getSingleResult();
	}

	public City findByName(String name)
	{
		return (City) entityManager.createNamedQuery("City.findByName")
				.setParameter("1", name)
				.getResultList();
	}
}
