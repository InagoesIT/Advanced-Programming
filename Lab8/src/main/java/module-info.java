module world_map {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	requires org.controlsfx.controls;
	requires java.sql;

	exports world_map;
	opens world_map to javafx.graphics;
}