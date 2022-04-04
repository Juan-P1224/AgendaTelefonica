package agenda.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import agenda.Main;
import agenda.model.Contacto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgendaController {

	Main main;

	ObservableList<Contacto> listaContactoData = FXCollections.observableArrayList();

	Contacto contactoSeleccionado;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<Contacto, String> columnTelefono;

	@FXML
	private TextField txtNombre;

	@FXML
	private Button btnEliminar;

	@FXML
	private TextField txtTelefono;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNombreBuscar;

	@FXML
	private TableColumn<Contacto, String> columnNombre;

	@FXML
	private TableView<Contacto> tableAgenda;

	@FXML
	private Button btnAgregar;

	@FXML
	private TableColumn<Contacto, String> columnCorreo;

	@FXML
	private Button btnBuscar;

	@FXML
	void eliminarAction(ActionEvent event) {

		eliminarContacto();
	}

	@FXML
	void agregarAction(ActionEvent event) {
		agregarContacto();
	}

	@FXML
	void ce00ff(ActionEvent event) {

	}

	@FXML
	void buscarAction(ActionEvent event) {

		buscarContacto();
	}

	@FXML
	void initialize() {
		this.columnNombre.setCellValueFactory  (new PropertyValueFactory<>("nombre"));
		this.columnCorreo.setCellValueFactory  (new PropertyValueFactory<>("correo"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		tableAgenda.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{

    		contactoSeleccionado = newSelection;
    	});
	}

	public void setAplicacion(Main main) {

		this.main = main;
		tableAgenda.getItems().clear();
    	tableAgenda.setItems(getListaContactoData());
	}

	/**
	 * Metodo que elimina un contacto seleccionado
	 */
	private void eliminarContacto() {

		if(contactoSeleccionado != null){

			if(mostrarMensajeConfirmacion("¿Esta seguro de eliminar al contacto?") == true){

				main.eliminarContacto(contactoSeleccionado);
    			listaContactoData.remove(contactoSeleccionado);
				contactoSeleccionado = null;
				tableAgenda.setItems(getListaContactoData());
				tableAgenda.refresh();
				mostrarMensaje("Notificación Agenda", "Contacto eliminado", "El contacto fue eliminado con éxito", AlertType.INFORMATION);
			}
		}else{
    		mostrarMensaje("Notificación Agenda", "Contacto no seleccionado", "Debe seleccionar un contacto de la tabla", AlertType.WARNING);
		}
	}

	/**
	 * Metodo que agrega un contacto
	 */
	private void agregarContacto() {
		String nombre   = txtNombre.getText();
		String email    = txtEmail.getText();
		String telefono = txtTelefono.getText();

		if(validarContacto(nombre, email, telefono)){

			Contacto contactoAux;
			contactoAux = main.crearContacto(nombre, email, telefono);

			if(contactoAux != null)
				listaContactoData.add(contactoAux);
			mostrarMensaje("Notificación agenda", "Contacto registrado", "El contacto se ha registrado con éxtio", AlertType.INFORMATION);
		}

		limpiarTextos();
	}

	/**
	 * Metodo que busca un contacto
	 */
	private void buscarContacto() {
		String nombre = txtNombreBuscar.getText();

		main.buscarContacto(nombre);
	}

	/**
	 * Metodo que valida los datos del contacto
	 * ingresado
	 * @param nombre
	 * @param email
	 * @param telefono
	 * @return True (Si los datos estan bien) de lo contrario false
	 */
	private boolean validarContacto(String nombre, String email, String telefono) {
		String mensaje = "";

		if(nombre == null || nombre.equals(""))
    		mensaje += "El nombre es invalido \n";
		if(validarNombre(nombre)){
			mensaje += "El contacto ya se encuentra agregado \n";
		}

    	if(email == null || email.equals(""))
    		mensaje += "El email es invalido \n";

    	if(telefono == null || telefono.equals(""))
    		mensaje += "El telefono es invalido \n";

    	if(mensaje.equals(""))
    		return true;
    	else{
    		mostrarMensaje("Alerta", "Datos invalidos", mensaje, AlertType.WARNING);
    		return false;
    	}
	}

	private boolean validarNombre(String nombre) {
		return main.validarNombre(nombre);
	}

	/**
	 * Metodo para mostrar un mensaje
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertType
	 */
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

    	Alert alert = new Alert(alertType);
    	alert.setTitle         (titulo);
    	alert.setHeaderText    (header);
    	alert.setContentText   (contenido);
    	alert.showAndWait      ();
    }

	/**
	 * Metodo que obtiene la lista de contactos
	 * de la agenda
	 * @return lista de contactos
	 */
	private ObservableList<Contacto> getListaContactoData() {

		listaContactoData.addAll(main.obtenerContacto());
    	return listaContactoData;
	}

	/**
	 * Metodo que limpia todos
	 * los campos de textos
	 */
	private void limpiarTextos() {
		txtNombre.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtNombreBuscar.setText("");
	}

	/**
     * Metodo solicita al usuario confirmar una accion
     * @param mensaje
     * @return boolean
     */
    private boolean mostrarMensajeConfirmacion(String mensaje) {

    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle         ("Confirmación");
    	alert.setHeaderText    (null);
    	alert.setContentText   (mensaje);

    	Optional<ButtonType> action = alert.showAndWait();

    	if(action.get() == ButtonType.OK){
    		return true;
    	}else{
    		return false;
    	}
    }

}



