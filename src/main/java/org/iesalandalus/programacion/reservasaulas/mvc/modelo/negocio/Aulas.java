package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;


public class Aulas{
	private int capacidad;
	private int tamano;
	 private Aula[] coleccionAulas;

		// Constructor
		public Aulas(int capacidadAulas) throws IllegalArgumentException, NullPointerException {
			if (capacidadAulas < 1) {
				throw new IllegalArgumentException(" La capacidad tiene que ser mayor que cero.");
			}  
					coleccionAulas = new Aula[capacidadAulas];
					this.capacidad = capacidadAulas;
					this.tamano = 0;
			
		}
	    
	    
	    public Aula[] get() {
	    	return copiaProfundaAulas();
	    }
	    
		// Método copiaProfundaAulas
		private Aula[] copiaProfundaAulas()  throws IllegalArgumentException, NullPointerException {
			Aula[] copiaProAulas = new Aula [tamano];
			for(int i = 0; !tamanoSuperado(i); i++) {
				copiaProAulas[i] = new Aula(coleccionAulas[i]);
			}
			return copiaProAulas;
		}
	    
	    
		public int getTamano() {
			return tamano;
		}
		  
	    public int getCapacidad() {
	    	return capacidad;
	    }


	    //Método insertar 
		public void insertar(Aula aula) throws OperationNotSupportedException{
	     
			if (aula == null) {
				throw new NullPointerException("No se puede insertar un aula nula.");
			}
			int indice = buscarIndice(aula);
		
			if (tamanoSuperado(indice)) {
	            coleccionAulas[indice] = new Aula(aula);
	            this.tamano++;
			} else {
	            if (capacidadSuperada(indice)) {
	            throw new OperationNotSupportedException("El aula ya existe.");
	            } else {
	            	throw new OperationNotSupportedException("No se aceptan más aulas.");
		}		}
	        
	    }
	    
	    //Método buscarIndice
	    private int buscarIndice(Aula aula) {
	    	int indice = 0;
	    	boolean aulaEncontrada = false;
	    	while (!tamanoSuperado(indice) && !aulaEncontrada) {
	            if (coleccionAulas[indice].equals(aula)) {
	            	aulaEncontrada = true;
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
	    public Aula buscar(Aula aula) {
	    	if (aula == null) {
	    		throw new NullPointerException(" No se puede buscar un aula nula.");
	    	}
	    	int indice = buscarIndice(aula);
	    	if (tamanoSuperado(indice)) {
	    		return null;
	    	} else {
				Aula aulaBuscado = new Aula(coleccionAulas[indice]);
	            return aulaBuscado;
	    	}
		}
	    
	    //Método borrar 
	    public void borrar(Aula aula) throws OperationNotSupportedException {
	    	if (aula == null) {
	            throw new NullPointerException("No se puede borrar un aula nula.");
	            }
	            int indice = buscarIndice(aula);
	            if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			}
	            else {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
	            }
		}
	     
	    //Método desplazarunaposicionhaciaizquierda
	    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
	    	for (int i = indice; !tamanoSuperado(i); i++) {
	            coleccionAulas[i] = coleccionAulas[i+1];
	            }
	            coleccionAulas[indice] = null;
	            tamano--;
		}
		
	    //Método representar
		public String[] representar() {
			String[] representacion = new String[tamano];
			for (int i = 0; !tamanoSuperado(i); i++) {
				representacion[i] = coleccionAulas[i].toString();
			}
			return representacion;
		}
	   
	    
	}