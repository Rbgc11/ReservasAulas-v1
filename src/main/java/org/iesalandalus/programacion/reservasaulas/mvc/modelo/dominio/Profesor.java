package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;


public class Profesor { //Atributos 
    private static final String ER_TELEFONO = "[6-9][0-9]{8}";
    private static final String ER_CORREO = "([a-zA-z0-9.-_]{1,})(\\@[a-zA-z]{1,})(\\.[a-z]{1,3})";
    private String nombre;
    private String correo;
    private String telefono;
    
    public Profesor(String nombre, String correo) {
        setNombre (nombre);
        setCorreo (correo);
    }
    
    public Profesor(String nombre, String correo, String telefono) {
        setNombre(nombre);
        setCorreo(correo);
        setTelefono(telefono);
    }
    //Constructor Copia
    public Profesor (Profesor profesor) {
        if (profesor == null) {
			throw new NullPointerException("No se puede copiar un profesor nulo.");
		}
		setNombre(profesor.getNombre());
		setTelefono(profesor.getTelefono());
		setCorreo(profesor.getCorreo());
	}
    
    //Set y Get de los atributos. El set usa el formateaNombre
    private void setNombre(String nombre) {        
        if(nombre==null){       
            throw new NullPointerException("El nombre del profesor no puede ser nulo.");
        }
        else if(nombre.trim() .equals("")){        
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");       
        }
            this.nombre = formateaNombre(nombre);
    }
    
 
  //Método formateaNombre
  		private String formateaNombre(String nombre) {
  			nombre = nombre.replaceAll("\\s{2,}", " ").trim();
  			String [] formatea = nombre.split(" ");
  			String Nombre2 = "";
  			for (int i=0; i<=formatea.length-1; i++) {
  				formatea[i] = formatea[i].substring(0,1).toUpperCase() + formatea[i].substring(1).toLowerCase();
  				Nombre2 = Nombre2 + formatea[i] + " ";
  			}
  			nombre = Nombre2.trim();
  			return nombre;
  		}


    
    
    public void setCorreo(String correo) {       
        if(correo==null){
            throw new NullPointerException("El correo del profesor no puede ser nulo.");      
        }
        if(correo.trim().equals("") || !correo.matches(ER_CORREO)){        
            throw new IllegalArgumentException("El correo del profesor no es válido.");         
        }
            this.correo=correo;        
    }
    
          
	public void setTelefono(String telefono){
		if (telefono == null) {
			this.telefono = null;
		} 
		else if (telefono.trim().equals("") || !telefono.matches(ER_TELEFONO)) {
		      throw new IllegalArgumentException("El teléfono del profesor no es válido.");
		    }

		    this.telefono = telefono;
		  }

    public String getNombre() {
        return nombre;

    }

    
    public String getCorreo() {
        return correo;
    }



    public String getTelefono() {
        return telefono;
    }
    
	public static Profesor getProfesorFicticio(String correo) {
		
		return new Profesor("Nombre", correo);
	}
	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	@Override
	public String toString() {
		if (getTelefono() == null)
			return "nombre=" + getNombre() + ", correo=" + getCorreo();
		else
			return "nombre=" + getNombre() + ", correo=" + getCorreo() + ", telefono=" + getTelefono() ;
	}
}


