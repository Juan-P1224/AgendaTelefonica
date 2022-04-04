package agenda.model;

public class Contacto <N, C, T> {

	N nombre;
	C correo;
	T telefono;

	public Contacto(N nombre, C correo, T telefono) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}



	public N getNombre() {
		return nombre;
	}



	public void setNombre(N nombre) {
		this.nombre = nombre;
	}



	public C getCorreo() {
		return correo;
	}



	public void setCorreo(C correo) {
		this.correo = correo;
	}



	public T getTelefono() {
		return telefono;
	}



	public void setTelefono(T telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "]";
	}

	public boolean validarKey(Object nombre2) {
		if(nombre.equals(nombre2)){
			return true;
		}

		return false;
	}

}
