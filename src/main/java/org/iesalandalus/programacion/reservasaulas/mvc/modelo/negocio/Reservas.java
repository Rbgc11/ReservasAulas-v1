package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	private int capacidad;
	private int tamano;
	Reserva[] coleccionReservas;

 	//Constructor con parámetros
	public Reservas(int capacidadReservas) {
		if (capacidadReservas < 1) {
			throw new IllegalArgumentException(" La capacidad debe ser mayor que cero.");
		} else {
			coleccionReservas = new Reserva[capacidadReservas];
			this.capacidad = capacidadReservas;
			this.tamano = 0;
		}
	}
	    
	    public Reserva[] get() {
		return copiaProfundaReservas();
	    }
	    
		// Método copiaProfundaReservas
		private Reserva[] copiaProfundaReservas()  {
			Reserva[] copiaReservas = new Reserva[capacidad];
			for(int i = 0; !tamanoSuperado(i); i++) {
				copiaReservas[i] = new Reserva(coleccionReservas[i]);
			}
			return copiaReservas;
		}
	    
	    
		public int getTamano() {
			return tamano;
		}
		  
	    public int getCapacidad() {
		return capacidad;
	    }


	    //Método insertar
		public void insertar(Reserva reserva) throws OperationNotSupportedException{
	     
			if (reserva == null) {
				throw new NullPointerException("No se puede insertar una reserva nula.");
			}
			int indice = buscarIndice(reserva);
	        if (tamanoSuperado(indice)) {
	            coleccionReservas[indice] = reserva;
	            tamano++;
	        } else {
	        		if (capacidadSuperada(indice)) {
	        		throw new OperationNotSupportedException("La reserva ya existe.");
	        } else {
	        	throw new OperationNotSupportedException("No se aceptan más reservas.");
	        }			}
	        
	    }
	    
	    //Método buscarIndiceReserva
	    private int buscarIndice(Reserva reserva) {
	    	int indice = 0;
	    	boolean reservaEncontrada = false;
	    	while (!tamanoSuperado(indice) && !reservaEncontrada) {
	    			if (coleccionReservas[indice].equals(reserva)) {
	    				reservaEncontrada = true;
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
	    public Reserva buscar(Reserva reserva) throws IllegalArgumentException, NullPointerException  {
	    	if (reserva == null) {
	            throw new NullPointerException("No se puede buscar una reserva nula.");
	            }
	    	int indice = buscarIndice(reserva);
	    	if (tamanoSuperado(indice)) {
				return null;
	    	} else {
            	return new Reserva(coleccionReservas[indice]);
	            }
		}
	    
	    //Método borrar
	    public void borrar(Reserva reserva) throws OperationNotSupportedException {
	    	if (reserva == null) {
	    		throw new NullPointerException ("No se puede borrar un Reserva nula.");
	        }
	        int indice = buscarIndice(reserva);
	        if (!tamanoSuperado(indice)) {
	        	desplazarUnaPosicionHaciaIzquierda(indice);
			}
	            else {
	            	throw new OperationNotSupportedException("La reserva a borrar no existe.");
	            }
		}
	      
	    //Método desplazarunaposicionhaciaizquierda
	    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
	    	for (int i = indice; i < tamano - 1; i++) {
	            coleccionReservas[i] = coleccionReservas[i+1];
	            }
	            coleccionReservas[tamano] = null;
	            tamano--;
		}
		
	    //Método representar
		public String[] representar() {
			String[] representacion = new String[tamano];
			for (int i = 0; !tamanoSuperado(i); i++) {
				representacion[i] = coleccionReservas[i].toString();
			}
			return representacion;
		}
		
		//GetReservasProfesor
	    public Reserva[] getReservasProfesor(Profesor profesor){

	        if(profesor==null){

	                throw new NullPointerException("No se pueden comprobar las reservas de un profesor nulo.");
	        }

	        Reserva[] reservaProfesor = new Reserva[capacidad];
	        int indice = 0;
	        for(int i = 0; !tamanoSuperado(i); i++) {
	            if(coleccionReservas[i]!=null && coleccionReservas[i].getProfesor().equals(profesor)){
	                reservaProfesor[indice] = new Reserva(coleccionReservas[i]);
	                 indice++;
	            }
	        }

	        return reservaProfesor;
	        }
	    
	    //Get ReservasAula
	    public Reserva[] getReservasAula(Aula aula){
	        if(aula==null){
	                throw new NullPointerException("No se pueden comprobar las reservas de un profesor nulo.");
	        }
	        Reserva[] reservaAula = new Reserva[capacidad];
	        int indice = 0;
	        for(int i = 0;!tamanoSuperado(i); i++) {
	            if(coleccionReservas[i]!=null && coleccionReservas[i].getAula().equals(aula)){
	                reservaAula[indice] = new Reserva(coleccionReservas[i]);
	                 indice++;
	            }
	        }

	        return reservaAula;
	        }
	    
	    //Método getReservasPemanencia
	    public Reserva[] getReservasPermanencia(Permanencia permanencia){
	        if(permanencia==null){
	                throw new NullPointerException("No se pueden comprobar las reservas de un profesor nulo.");
	        }
	        Reserva[] reservaPermanencia = new Reserva[capacidad];
	        int indice = 0;
	        for(int i = 0; !tamanoSuperado(i); i++) {
	            if(coleccionReservas[i]!=null && coleccionReservas[i].getPermanencia().equals(permanencia)){
	                reservaPermanencia[indice] = new Reserva(coleccionReservas[i]);
	                 indice++;
	            }
	        }

	        return reservaPermanencia;
	    }
	    
	    
	 // Método consultarDisponibilidad(Aula,Permanencia)
	    
	     public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia){
	     
	     	if(aula==null)
	            throw new NullPointerException("No se puede consultar la disponibilidad de un aula nula.");
	     	if(permanencia==null)
	            throw new NullPointerException("No se puede consultar la disponibilidad de una permanencia nula.");
		
	     	Reserva[] reservas = getReservasAula(aula);
			for (Reserva reserva : reservas) {
				if (reserva != null) {
					if (reserva.getPermanencia().equals(permanencia)) {
						return false;
					}
				}
			}
			return true;
		
	     }
	     

	     
	     
}    
	    
	    
	    
	    
	   
	 