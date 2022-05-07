import entities.Continent;
import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class Main
{
	private static final EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
	private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

	public static void main(String[] args)
	{
		entityManager.getTransaction().begin();
		Continent continentRes;
		Continent continent;

		try
		{
			continent = new Continent("Europe");
			entityManager.persist(continent);
		}
		catch (DatabaseException exceptionEurope)
		{
			System.out.println("There is already a continent with this name");
		}
		try
		{
			continentRes = (Continent)entityManager.createQuery(
							"select continent from Continent continent where continent.name='Europe'")
					.getSingleResult();
			continentRes.setName("Africa");
			System.out.println("The changed name of the continent:" + continentRes.getName());
			entityManager.getTransaction().commit();
		}
		catch (DatabaseException exceptionAfrica)
		{
			System.out.println("There is already a continent with this name");
		}
		catch (RollbackException rollbackException)
		{
			System.out.println(rollbackException);
		}
	}
}
