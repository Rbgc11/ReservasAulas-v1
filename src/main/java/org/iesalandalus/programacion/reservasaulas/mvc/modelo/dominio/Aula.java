package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
	//Atributos
    private String nombre;

    public Aula(String nombre) {
        setNombre(nombre);
    }
    
    //Constructor Copia
    public Aula(Aula aula) {
        {
            if (aula == null) {
    			throw new NullPointerException("No se puede copiar un aula nula.");
    		}
    		setNombre(aula.nombre);
        }
    }
    
	private void setNombre(String nombre) {
		if(nombre==null)
			throw new NullPointerException("El nombre del aula no puede ser nulo.");
		else if (nombre.trim() == ("")) {
			throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
		} else {
		this.nombre = nombre;
		}
	}
	
    public String getNombre() {
        return nombre;
    }



	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre Aula=" + this.nombre + "";
	}

   

 

}
