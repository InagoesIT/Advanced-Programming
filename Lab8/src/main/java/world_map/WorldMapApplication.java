package world_map;

import db_object.City;
import db_object_dao.CityDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.sql.SQLException;

public class WorldMapApplication extends Application
{
	private static final String IMAGE_PATH = "https://maproom.net/wp-content/uploads/Maproom-World-Map-4.png";
	private static final int IMAGE_WIDTH = 1500;
	private static final int IMAGE_HEIGHT = 800;
	private static final int BORDER_SIZE = 50;

	public static void main(String[] args)
	{
		launch();
	}

	@Override
	public void start(Stage stage) throws SQLException
	{
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(IMAGE_WIDTH);
		root.setMaxWidth(IMAGE_WIDTH);
		root.setPrefHeight(IMAGE_HEIGHT);
		root.setMaxHeight(IMAGE_HEIGHT);
		Scene scene = new Scene(root, (IMAGE_WIDTH + BORDER_SIZE), (IMAGE_HEIGHT + BORDER_SIZE));

		setBackground(root);
		drawCities(root);

		stage.setTitle("Cities' locations on the world map");
		stage.setScene(scene);
		stage.show();
	}

	private static void setBackground(AnchorPane pane)
	{
		Image worldMap = new Image(IMAGE_PATH);
		BackgroundImage backgroundImage = new BackgroundImage(worldMap,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(IMAGE_WIDTH, IMAGE_HEIGHT, false, false, false, false));
		Background background = new Background(backgroundImage);
		pane.setBackground(background);
	}

	private static void drawCities(AnchorPane pane) throws SQLException
	{
//		int x;
//		int y;

		for (City city : CityDAO.getInstance().getAllCities())
		{
			//ALTERNATIVE FORMULA
//			x =  (int) ((IMAGE_WIDTH / 360.0) * (180 + city.getLongitude()));
//			y =  (int) ((IMAGE_HEIGHT / 180.0) * (90 - city.getLatitude()));
			Coordinates coordinates = getXYCoordinates(city.getLatitude(), city.getLongitude());
			drawCircle(pane, coordinates.x, coordinates.y);
		}
	}

	private static Coordinates getXYCoordinates(double latitude, double longitude)
	{
		final int FALSE_EASTING = 180;
		final double radius = IMAGE_WIDTH / (2 * Math.PI);

		final double latitudeRad = Math.toRadians(latitude);
		final double longitudeRad = Math.toRadians(longitude + FALSE_EASTING);

		final int x = (int) (longitudeRad * radius);

		final double yFromEquator = radius * Math.log(Math.tan(Math.PI / 4 + latitudeRad / 2));
		final int y = (int) (IMAGE_HEIGHT / 2 - yFromEquator);

		return new Coordinates(x, y);
	}

	private static void drawCircle(AnchorPane pane, int x, int y)
	{
		Circle circle = new Circle();
		circle.setRadius(3);
		circle.setStrokeWidth(0.2);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.BLACK);
		circle.setLayoutX(x);
		circle.setLayoutY(y);
		pane.getChildren().add(circle);
	}
}