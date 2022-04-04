package com.example.lab6;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.jgrapht.nio.json.JSONExporter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class Controller
{
	@FXML
	private Spinner<Integer> heightSpinner;
	@FXML
	private Spinner<Integer> widthSpinner;
	@FXML
	private AnchorPane grid;

	private ModelController modelController;
	private Random random;

	@FXML
	public void initialize()
	{
		this.createGrid();
	}

	@FXML
	public void createGrid()
	{
		int initLayoutX;
		int initLayoutY = 16;
		int i;
		int j;

		random = new Random();

		modelController = new ModelController();

		grid.getChildren().clear();

		// create the lines and circles
		// add the nodes and edges to the graph
		for (i = 0; i < heightSpinner.getValueFactory().getValue(); i++)
		{
			initLayoutX = 38;

			for (j = 0; j < widthSpinner.getValueFactory().getValue(); j++)
			{
				createCircle("circle" + i + j, initLayoutX, initLayoutY);

				if (j != widthSpinner.getValueFactory().getValue() - 1)
					createLine(i, j, "R", initLayoutX, initLayoutY);

				if (i != heightSpinner.getValueFactory().getValue() - 1)
					createLine(i, j, "C", initLayoutX, initLayoutY);

				initLayoutX += 50;
			}
			initLayoutY += 50;
		}
	}

	@FXML
	public void displayWinner(int winner)
	{
		Alert alert = new Alert(Alert.AlertType.NONE,
				"The game has ended. The winner is the " + (winner == 1 ? "red player." : "blue player."),
				ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isEmpty() || result.get() == ButtonType.OK)
			createGrid();
	}

	@FXML
	public void save()
	{
		ButtonType jsonButton = new ButtonType("JSON");
		ButtonType pngButton = new ButtonType("PNG");

		Alert alert = new Alert(Alert.AlertType.NONE,
				"Do you want the current state of the board as a png or JSON file?",
				jsonButton, pngButton);
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent())
		{
			if (result.get() == pngButton)
				saveAsPng();
			else if (result.get() == jsonButton)
				saveAsJSON();
		}
	}

	@FXML
	public void saveAsJSON()
	{
		JSONExporter<GameCircle, GameLine> jsonExporter = new JSONExporter<>(GameCircle::getId);
		jsonExporter.setEdgeIdProvider(GameLine::getId);
		jsonExporter.exportGraph(modelController.getGraph(), new File("gameBoard.json"));
	}

	@FXML
	public void load()
	{
//		JSONImporter<GameCircle, GameLine> jsonImporter= new JSONImporter<>();
//		Graph<GameCircle, GameLine> newGraph = new SimpleGraph<>(GameLine.class);
//		jsonImporter.importGraph(newGraph, new File("gameBoard.json"));
//		modelController.setGraph(newGraph);
	}

	@FXML
	public void saveAsPng()
	{
		WritableImage image = grid.snapshot(new SnapshotParameters(), null);
		File file = new File("gameBoard.png");

		try
		{
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}

	private void createCircle(String id, int initLayoutX, int initLayoutY)
	{
		Circle circle = new Circle();
		Color circleColor = Color.web("#1f93ff00", 0.0);
		circle.setId(id);
		circle.setRadius(14);
		circle.setStrokeWidth(0.2);
		circle.setStroke(Color.BLACK);
		circle.setFill(circleColor);
		circle.setLayoutX(initLayoutX);
		circle.setLayoutY(initLayoutY);
		circle.toFront();
		circle.setViewOrder(1.0);
		grid.getChildren().add(circle);

		circle.setOnMouseClicked(mouseEvent ->
		{
			int color = modelController.getMoveColor(circle.getId());
			int winner;
			if (color == 1)
				circle.setFill(Color.RED);
			else if (color == 2)
				circle.setFill(Color.BLUE);
			winner = modelController.whoWon();
			if (winner != 0)
				displayWinner(winner);
		});
	}

	private void addEdgeAndVertices(String place, int i, int j)
	{
		modelController.addNode("circle" + i + j);
		if (place.equals("R"))
		{
			modelController.addNode("circle" + i + (j + 1));
			modelController.addEdge("circle" + i + j, "circle" + i + (j + 1), "line" + i + j + place);
		} else if (place.equals("C"))
		{
			modelController.addNode("circle" + (i + 1) + j);
			modelController.addEdge("circle" + i + j, "circle" + (i + 1) + j, "line" + i + j + place);
		}
	}

	private void createLine(int i, int j, String place, double initLayoutX, double initLayoutY)
	{
		Line line = new Line();
		boolean isThick = random.nextBoolean();
		line.setStrokeWidth(isThick ? 5.0 : 0.5);
		line.setId("line" + i + j + place);
		line.setStroke(Color.BLACK);
		line.setLayoutX(initLayoutX);
		line.setLayoutY(initLayoutY);
		double END_LINE = 50;
		if (place.equals("R"))
			line.setEndX(END_LINE);
		else
			line.setEndY(END_LINE);
		line.setMouseTransparent(true);
		line.setViewOrder(2.0);
		grid.getChildren().add(line);

		if (isThick)
			addEdgeAndVertices(place, i, j);
	}
}