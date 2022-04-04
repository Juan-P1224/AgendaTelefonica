package agenda;

import java.io.IOException;
import java.util.ArrayList;

import agenda.controller.AgendaController;
import agenda.model.Contacto;
import agenda.model.TablaHash;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {


	private Stage primaryStage;

	TablaHash table = new TablaHash();

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Agenda Uniquindio");
		mostrarVentanaPrincipal();

	}

//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		mostrarVentanaPrincipal();
//	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metodo que muestra la ventana pricipal
	 */
	private void mostrarVentanaPrincipal() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AgendaView.fxml"));

			BorderPane rootLayout = (BorderPane)loader.load();

			AgendaController agendaController = loader.getController();
			agendaController.setAplicacion(this);


			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para crear un contacto en la agenda
	 * @param nombre
	 * @param email
	 * @param telefono
	 * @return el contacto creado
	 */
	public Contacto crearContacto(String nombre, String email, String telefono) {

		return table.insertar(nombre, email, telefono);

	}

	/**
	 * Metodo que elimina un contacto
	 * @param contactoSeleccionado
	 */
	public void eliminarContacto(Contacto contactoSeleccionado) {

		table.eliminarContacto(contactoSeleccionado);

	}

	public ArrayList<Contacto> obtenerContacto() {
		return table.obtenerContacto();
	}

	public boolean validarNombre(String nombre) {
		return table.validarNombre(nombre);
	}

	public void buscarContacto(String nombre) {

		table.buscar(nombre);

	}

}
