package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.List;
import java.util.Iterator;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;

public class Vista {
   	private static final String ERROR = "ERROR: ---- ";
    private static final String NOMBRE_VALIDO= "Nombre válido";
	private static final String CORREO_VALIDO="Correo válido";
	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}
	
	//Método setControlador
	public void setControlador (Controlador controlador){
		this.controlador = controlador;
	}
	
	//Método comenzar
	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	//Método salir
	public void salir() {
		System.out.println("¡Adiós!");
	}
	
	//Método insertarAula	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");
		} catch (NullPointerException |OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	//Método borrarAula
	public  void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	
	//Método buscarAula
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula;
		try {
			aula = controlador.buscarAula( Consola.leerAula());
			if (aula != null) {
				System.out.println("El aula buscada es: " + aula.toString());
			} else {
				System.out.println("No existe ninguna aula con dicho nombre.");
			}
		} catch (IllegalArgumentException | NullPointerException e){
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	//Método listaraAulas
	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		List<String> aulas = controlador.representarAulas();
		if (aulas.size() != 0) {
			for (Iterator<String> iterador = aulas.iterator(); iterador.hasNext();) {
				System.out.println(iterador.next().toString());
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
	
	//Método insertarProfesor
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
                        if (profesor.getNombre()!=null){
			System.out.println(NOMBRE_VALIDO);
                        }
                        if (profesor.getCorreo()!=null){
                            System.out.println(CORREO_VALIDO);
                			System.out.println("El profesor se ha insertado correctamente");
                            }
                        
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Método borrarProfesor
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("El profesor se ha borrado correctamente.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	//Método buscarProfesor
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Método listarProfesores
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		List<String> listaprofesores = controlador.representarProfesores();
		if (listaprofesores.size() != 0) {
			for (String profesor : listaprofesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores para listar.");
		}
	}

		//Método realizarReserva
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		try {
			if (controlador.buscarProfesor(Consola.leerProfesor()) == null) {
				throw new NullPointerException("El profesor introducido no existe.");
			} else {
				Reserva reserva = leerReserva(controlador.buscarProfesor(Consola.leerProfesor()));
				if (reserva == null) {
					throw new IllegalArgumentException(
							"Algún error en la reserva.");
				}
				controlador.realizarReserva(reserva);
				System.out.println("Reserva realizada correctamente.");
			}
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
       //Método leerReserva
        private Reserva leerReserva(Profesor profesor){
    		Consola.mostrarCabecera("Leer reserva");
    		Aula aula = controlador.buscarAula(Consola.leerAula());
            Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
    		Reserva reserva = null;
    		if (aula == null) {
    			System.out.println("El aula introducida no existe.");
    		} else {
    			reserva = new Reserva(profesor, aula, permanencia);

    		} 
    		return reserva;
    	}
        
    	//Método anularReserva
    	public void anularReserva() {
    		Consola.mostrarCabecera("Anular reserva");
    		try {
    			Profesor profesor = Consola.leerProfesor();
    			Reserva reserva = leerReserva(profesor);
    			controlador.anularReserva(reserva);
    			System.out.println("La reserva se ha anulado correctamente.");
    		} catch (OperationNotSupportedException | NullPointerException e) {
    			System.out.println(e.getMessage());
    		}
    	}
	
	//Método listarReservas
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		List<String>reservas = controlador.representarReservas();
		if (reservas.size() != 0) {
			for (Iterator<String> iterador = reservas.iterator(); iterador.hasNext();) {
				System.out.println(iterador.next().toString());
			}
		} else {
			System.out.println("No hay reservas para listar.");
		}
	}
        
		//Método listarReservasAula
        public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas aula");
		
		List<Reserva>reservasAula = controlador.getReservasAula(Consola.leerAula());
		if (reservasAula.size() != 0) {
			for (Iterator<Reserva> iterador = reservasAula.iterator(); iterador.hasNext();) {
				Reserva reserva = iterador.next();

				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
        
    	//Método listarReservasProfesor
        public void listarReservasProfesor() {
            Consola.mostrarCabecera("Listar reservas profesor");
    		Profesor profesor = Consola.leerProfesor();
               
            List<Reserva> reservasProfesor = controlador.getReservasProfesor(profesor);
            if (reservasProfesor.size() != 0) {
            	for (Iterator<Reserva> iterador = reservasProfesor.iterator(); iterador.hasNext();) {
    				Reserva reserva = iterador.next();

    				System.out.println(reserva);
    			}   
            } else {
                    System.out.println("No hay reservas a ese profesor.");
            }
                    
             
         }
                            
                            
    	//Método listarReservasPermanencia
        public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas profesor");
        Permanencia permanenciaReserva=new Permanencia(Consola.leerDia(),Consola.leerTramo());
        List<Reserva>reservasPermanencia = controlador.getReservasPermanencia(permanenciaReserva);
                
		if (reservasPermanencia.size() != 0) {
			for (Iterator<Reserva> iterador = reservasPermanencia.iterator(); iterador.hasNext();) {
				Reserva reserva = iterador.next();

				System.out.println(reserva);
			}
		}else{
			System.out.println("No hay reservas.");
		}
	}
               
                    
    	//Método consultarDisponibilidad
    	public void consultarDisponibilidad() {
    		Consola.mostrarCabecera("Consultar disponibilidad");
    		try {
    			Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
    			Aula buscarAula = controlador.buscarAula(Consola.leerAula());
    			if (buscarAula == null) {
    				System.out.println("El aula introducida no existe.");
    			} else {
    				if (controlador.consultarDisponibilidad(buscarAula, permanencia)) {
    					System.out.println("El aula está disponible.");
    				} else {
    					System.out.println("El aula ya está reservada.");
    				}

    			}
    		} catch (NullPointerException | IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		}
    	}
 
}

