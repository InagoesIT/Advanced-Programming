module com.example.lab6 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;
	requires org.jgrapht.core;
	requires java.desktop;
	requires com.fasterxml.jackson.databind;
	requires org.jgrapht.io;

	opens com.example.lab6 to javafx.fxml;
	exports com.example.lab6;
}