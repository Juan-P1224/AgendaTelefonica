package agenda.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TablaHash {

	public Casilla [] arreglo = new Casilla [13];

	public TablaHash() {
		for (int i = 0; i < arreglo.length; i++) {

			arreglo [i] = new Casilla();
		}
	}

	public Contacto insertar(String nombre, String email, String telefono){

		Contacto contacto = new Contacto<String, String, String>(nombre, email, telefono);

		int pos = contacto.getNombre().hashCode()%arreglo.length;

		if( arreglo[pos].insertar(contacto)){

			return contacto;

		}else{

			JOptionPane.showMessageDialog(null,"La llave ya existe");

		}
		return null;
	}

	public Casilla[] getArreglo() {
		return arreglo;
	}

	public void setArreglo(Casilla[] arreglo) {
		this.arreglo = arreglo;
	}

	/**
	 * Metodo que obtiene los contactos de la agenda
	 * @return La lista de contactos
	 */
	public ArrayList<Contacto> obtenerContacto() {
		ArrayList<Contacto> listaContacto = new ArrayList<>();
		for (Contacto contactoAux: listaContacto) {
			if(contactoAux != null){
				listaContacto.add(contactoAux);
			}
		}
		return listaContacto;
	}

	/**
	 * Metodo que elimina un contacto
	 * @param contacto
	 */

	public void eliminarContacto(Contacto contactoAux) {

		int posicion ;
		posicion = contactoAux.getNombre().hashCode()%arreglo.length;
		arreglo[posicion].eliminarDato(contactoAux);


	}

	public boolean validarNombre(String nombre) {
		int posicion ;
		posicion = nombre.hashCode()%arreglo.length;
		return arreglo[posicion].validarNombre(nombre);
	}

	public void buscar(String nombre) {

		int posicion;

		posicion = nombre.hashCode()%arreglo.length;

		arreglo[posicion].buscar(nombre,0);

	}


//	public void buscar(String key){
//		Dato datoAux;
//		int posicion ;
//		posicion = key.hashCode()%arreglo.length;
//		datoAux = arreglo[posicion].buscarDato(key);
//		if(datoAux != null){
//	    System.out.println(datoAux);
//		}else{
//			JOptionPane.showMessageDialog(null,"El dato no existe");
//		}
//	}

//	public void eliminar(String key){
//		int posicion ;
//		boolean datoAux;
//		posicion = key.hashCode()%arreglo.length;
//		datoAux =arreglo[posicion].eliminarDato(key);
//		if(datoAux==true){
//
//			JOptionPane.showMessageDialog(null,"El dato con la llave "+key+" fue eliminado");
//		}else{
//
//			JOptionPane.showMessageDialog(null,"El dato con la "+key+" no fue eliminado");
//
//		}
//
//
//	}

//	public void mostrar(){
//
//		String mensaje = "";
//
//		for (Casillas casillas : arreglo) {
//
//			mensaje += casillas.mostarDatos()+"\n";
//
//		}
//
//		System.out.println(mensaje);
//
//	}



}
