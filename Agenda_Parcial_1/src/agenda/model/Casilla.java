package agenda.model;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class Casilla {

	//Atributos de la clase casilla
	private ArrayList<Contacto> listaContactos = new ArrayList<>();

	public Casilla( ) {

	}

	public ArrayList<Contacto> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ArrayList<Contacto> listaContactos) {
		this.listaContactos = listaContactos;
	}

	@Override
	public String toString() {
		return "Casilla [listaContactos=" + listaContactos + "]";
	}

	/**
	 * Metodo que inserta una contacto a la agenda
	 * @param contacto
	 * @return true(si se agrego) de lo contrario false
	 */
	public boolean insertar(Contacto contacto) {
		for (Contacto contacto2 : listaContactos) {
			if(contacto2.validarKey(contacto.getNombre())){
				return false;
			}
		}
		listaContactos.add(contacto);
		return true;
	}

	/**
	 * Metodo que elimina un contacto
	 * @param nombre
	 * @return true si es eliminado y de los contrario false
	 */
	public void eliminarDato(Contacto contactoAux) {

		listaContactos.remove(contactoAux);

	}

	/**
	 * Metodo que valida si existe el nombre
	 * @param nombre
	 * @return
	 */
	public boolean validarNombre(String nombre) {

		for (Contacto contacto : listaContactos) {
			if (contacto.getNombre().equals(nombre)){
				return true;
			}
		}
		return false;
	}

	public  void buscar(String nombre, int posicion) {

		if(listaContactos.size() < posicion){
			mostrarMensaje("Contacto", "Info Contacto", "no encontrado", AlertType.INFORMATION);
		}else{

			if(listaContactos.get(posicion).getNombre().equals(nombre))  {
				mostrarMensaje("Contacto", "Info Contacto", " "+listaContactos.get(posicion), AlertType.INFORMATION);
				posicion = listaContactos.size();
			}else{

				buscar(nombre, posicion++);

			}
		}
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
}




