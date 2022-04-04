package com.example.lab6;

import java.io.Serializable;

public class GameLine  implements Serializable
{
	private String id; //"lineij" (i->row, j->column)

	public GameLine(String id)
	{
		this.id = id;
	}

	public GameLine(){}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
