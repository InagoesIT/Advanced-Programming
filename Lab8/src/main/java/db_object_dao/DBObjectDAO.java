package db_object_dao;

import db_object.DBObject;

import java.sql.SQLException;

public interface DBObjectDAO<T extends DBObject>
{
	void create(T object) throws SQLException;
	T findByName(String name) throws SQLException;
	T findById(int id) throws SQLException;
}
