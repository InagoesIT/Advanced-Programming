import java.sql.SQLException;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			var continents = new ContinentDAO();
			continents.create("Europe");
			Database.getConnection().commit();

			int europe1Id = continents.findByName("Europe1");
			System.out.println(europe1Id);
			int europeId = continents.findByName("Europe");
			System.out.println(europeId);

			var countries = new CountryDAO();
			countries.create("Romania", europeId);
			countries.create("Republic of Moldova", europeId);
			Database.getConnection().commit();

			System.out.println("All the countries in Europe are: " + countries.findByContinent(europeId));
			Database.getConnection().close();
		} catch (SQLException sqlException)
		{
			System.err.println(sqlException);
			try
			{
				Database.getConnection().rollback();
			} catch (SQLException exception)
			{
				exception.printStackTrace();
			}
		}
	}
}
