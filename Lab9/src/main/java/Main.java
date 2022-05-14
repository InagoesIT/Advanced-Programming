import entities.Continent;
import org.eclipse.persistence.exceptions.DatabaseException;
import utils.ChocoSolverDemo;
import utils.DataImporter;
import utils.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class Main
{
	private static final EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
	private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

	public static void main(String[] args)
	{
//		DataImporter dataImporter = new DataImporter(entityManager);
//		dataImporter.importCSV("/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab9/src/main/resources/world_capitals.csv");
		ChocoSolverDemo chocoSolverDemo = new ChocoSolverDemo(entityManager, 10, 15);
		System.out.println(chocoSolverDemo.getResult());
	}

	private static void compulsoryTest()
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