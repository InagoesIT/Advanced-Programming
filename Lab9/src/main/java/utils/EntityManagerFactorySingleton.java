package utils;

import javax.persistence.*;

public class EntityManagerFactorySingleton
{
	private static EntityManagerFactory entityManagerFactory = null;

	private EntityManagerFactorySingleton(){}

	public static EntityManagerFactory getInstance()
	{
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("default");
		return entityManagerFactory;
	}
}