package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores{
	private int capacidad;
	private int tamano;
	 private Profesor[] coleccionProfesores;

	// Constructor con parámetros
		public Profesores(int capacidadProfesores) {
			if (capacidadProfesores < 1) {
				throw new IllegalArgumentException( "La capacidad debe ser mayor que cero.");
			} 
				coleccionProfesores = new Profesor[capacidadProfesores];
				this.capacidad = capacidadProfesores;
				this.tamano = 0;
			
		}
		
		// Método Profesor[] get()
	    public Profesor[] get() {
		return copiaProfundaProfesores();
	    }
	    
		// Método copiaProfundaProfesores
		private Profesor[] copiaProfundaProfesores() throws IllegalArgumentException, NullPointerException {
			Profesor[] copiaProfesores = new Profesor[capacidad];
			for(int i = 0;!tamanoSuperado(i); i++) {
				copiaProfesores[i] = new Profesor(coleccionProfesores[i]);
			}
			return copiaProfesores;
		}
	    

		public int getTamano() {
			return tamano;
		}
		  
	    public int getCapacidad() {
		return capacidad;
	    }


	    //Método insertar
		public void insertar(Profesor profesor) throws OperationNotSupportedException{
	     
		if (profesor == null) {
			throw new NullPointerException("No se puede insertar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
	        if (tamanoSuperado(indice)) {
	            coleccionProfesores[indice] = profesor;
	            tamano++;
		} else {
	            if (capacidadSuperada(indice)) {
	            throw new OperationNotSupportedException("El Profesor ya existe.");
		} else {
			throw new OperationNotSupportedException("No se aceptan más Profesores.");
		}		}
	        
	    }
	    
	    //Método buscarIndice
	    private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (!tamanoSuperado(indice) && !profesorEncontrado) {
	            if (coleccionProfesores[indice].equals(profesor)) {
	            profesorEncontrado = true;
		} else {
			indice++;
	            }
		}
	                return indice;
		}
	    
	    private boolean tamanoSuperado (int indice) {
			return indice >= tamano;
		}
	    private boolean capacidadSuperada(int indice) {
			return indice >= capacidad;
		}
		
	    //Método buscar
	    public Profesor buscar(Profesor profesor) throws IllegalArgumentException, NullPointerException {
	    	if (profesor == null) {
	            throw new NullPointerException("No se puede buscar un profesor nula.");
	            }
	    	int indice = buscarIndice(profesor);
	    	if (tamanoSuperado(indice)) {
	    			return null;
	    	} else {
				Profesor profesorEncontrado = new Profesor(coleccionProfesores[indice]);
				return profesorEncontrado;
	    	}
		}
	    
	    //Método borrar
	    public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
	            throw new NullPointerException("No se puede borrar un profesor nula.");
	            }
	            int indice = buscarIndice(profesor);
	            if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			}
	            else {
			throw new OperationNotSupportedException("El Profesor a borrar no existe.");
	            }
		}
	    
	    //Método desplazarunaposicionhaciaizquierda
	    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; !tamanoSuperado(i); i++) {
	            coleccionProfesores[i] = coleccionProfesores[i+1];
	            }
	            coleccionProfesores[tamano] = null;
	            tamano--;
		}
		
	    //Método representar
		public String[] representar() {
			String[] representacion = new String[tamano];
			for (int i = 0; !tamanoSuperado(i); i++) {
				representacion[i] = coleccionProfesores[i].toString();
			}
			return representacion;
		}
		
	
}