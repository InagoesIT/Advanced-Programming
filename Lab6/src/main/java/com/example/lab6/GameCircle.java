package com.example.lab6;

import java.io.Serializable;

public class GameCircle implements Serializable
{
	private int state; // 0->free; 1->red; 2->blue
	private String id; //"circleij" (i->row, j->column)

	public GameCircle(String id)
	{
		this.id = id;
		this.state = 0;
	}

	public GameCircle(){}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "GameCircle{" +
				"state=" + state +
				", id='" + id + '\'' +
				'}';
	}
}
