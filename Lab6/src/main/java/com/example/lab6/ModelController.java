package com.example.lab6;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class ModelController
{
	private Graph<GameCircle, GameLine> graph;
	private int turn; // 1->red, 2->blue;
	private boolean isFirst;

	public ModelController()
	{
		this.graph = new SimpleGraph<>(GameLine.class);
		this.turn = 1;
		this.isFirst = true;
	}

	public boolean addNode(String id)
	{
		if (!hasNode(id))
		{
			GameCircle gameCircle = new GameCircle(id);
			return this.graph.addVertex(gameCircle);
		}
		return false;
	}

	public GameCircle getNode(String id)
	{
		return this.graph.vertexSet().stream().filter(gameCircle -> gameCircle.getId().equals(id)).toList().get(0);
	}

	public boolean hasNode(String id)
	{
		return this.graph.vertexSet().stream().anyMatch(gameCircle -> gameCircle.getId().equals(id));
	}

	public boolean addEdge(String circleId1, String circleId2, String lineId)
	{
		return this.graph.addEdge(getNode(circleId1), getNode(circleId2), new GameLine(lineId));
	}

	public boolean hasAdjacentNode(String id, int opponent)
	{
		return Graphs.neighborSetOf(graph, getNode(id)).stream().
				anyMatch(gameCircle -> gameCircle.getState() == opponent);
	}

	public int getMoveColor(String id)
	{
		// the node isn't in the graph
		if (!hasNode(id))
			return 0;
		// this is the first stone placed on the table
		if (this.isFirst)
		{
			this.isFirst = false;
			getNode(id).setState(turn);
			turn = 3 - turn;
			return 3 - turn;
		}
		// the place is occupied
		if (getNode(id).getState() != 0)
			return 0;

		if (hasAdjacentNode(id, 3 - turn))
		{
			getNode(id).setState(turn);
			turn = 3 - turn;
			return 3 - turn;
		}
		return 0;
	}

	public Graph<GameCircle, GameLine> getGraph()
	{
		return graph;
	}

	public boolean canMove(int color)
	{
		// check if I can place a color stone on an empty place (i.e. color can move)
		return graph.vertexSet().stream().filter(gameCircle -> gameCircle.getState() == 0).
				anyMatch(gameCircle -> hasAdjacentNode(gameCircle.getId(), 3 - color));
	}

	public int whoWon()
	{
		if (turn == 1 && !canMove(1))
			return 2;
		else if (turn == 2 && !canMove(2))
			return 1;
		return 0;
	}

	public void setGraph(Graph<GameCircle, GameLine> graph)
	{
		this.graph = graph;
	}
}
