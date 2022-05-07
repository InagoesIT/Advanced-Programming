package repositories;

import entities.Continent;

import javax.persistence.EntityManager;

public class ContinentRepository
{
	private final EntityManager entityManager;

	public ContinentRepository(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public Continent findById(int id)
	{
		return (Continent) entityManager.createQuery(
						"select continent from Continent continent where continent.id=?1")
				.setParameter(1, id).getSingleResult();
	}

	public Continent findByName(String name)
	{
		return (Continent) entityManager.createNamedQuery("City.findByName")
				.setParameter("1", name)
				.getResultList();
	}
}
