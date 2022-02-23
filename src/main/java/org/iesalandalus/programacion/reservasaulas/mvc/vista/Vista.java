package org.iesalandalus.programacion.reservasaulas.mvc.vista;

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
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
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
		String[]aulas = controlador.representarAulas();
		if (aulas.length != 0) {
			for (String aula : aulas) {
				System.out.println(aula);
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
                            System.out.println(CORREO_VALIDO);}
                        
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	//Método borrarProfesor
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
                String nombreProfesor=Consola.leerNombreProfesor();
                Profesor profesor=null;
		try {
             profesor = new Profesor(nombreProfesor,CORREO_VALIDO);
            controlador.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	//Método buscarProfesor
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		String nombreProfesor = Consola.leerNombreProfesor();
                
		try {
			Profesor profesor= new Profesor(nombreProfesor,CORREO_VALIDO);
                        profesor =controlador.buscarProfesor(profesor);
			if ( profesor== null) {
                            System.out.println("No existe ninguna profesor con ese nombre.");
                        } else {
                            System.out.println("El profesor buscado es: " + profesor);
                        }
		} catch  (IllegalArgumentException | NullPointerException e) {
			System.out.println(ERROR + e.getMessage());
		
                
                }
	}
	
	//Método listarProfesores
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		String[]listaprofesores = controlador.representarProfesores();
		if (listaprofesores.length != 0) {
			for (String profesor : listaprofesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores para listar.");
		}
	}

		//Método realizarReserva
        public void realizarReserva()  {
        	Consola.mostrarCabecera("Realizar reserva");

        	try{
        		controlador.realizarReserva(leerReserva(Consola.leerProfesor()));
        		System.out.println("Reserva realizada correctamente.");
        	} catch (OperationNotSupportedException|IllegalArgumentException e) {
        		System.out.println(ERROR + e.getMessage());
        	}         
        }
        
       //Método leerReserva
        private Reserva leerReserva(Profesor profesor){
            
            Aula aula = Consola.leerAula();
            Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
            return new Reserva(profesor, aula, permanencia);
          
        }
	
    	//Método anularReserva
	public void anularReserva() {
            
		Consola.mostrarCabecera("Borrar cliente");
                Profesor profesor = new Profesor(NOMBRE_VALIDO, CORREO_VALIDO);
		try {
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("Reserva anulada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	
	//Método listarReservas
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		String[]reservas = controlador.representarReservas();
		if (reservas.length != 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas para listar.");
		}
	}
        
		//Método listarReservasAula
        public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas aula");
		
		Reserva[]listaReservasAula = controlador.getReservasAula(Consola.leerAula());
		if (listaReservasAula.length != 0) {
			for (Reserva reserva : listaReservasAula) {
                            if(reserva!=null){
                            System.out.println(reserva);
			}
                        }
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
        
    	//Método listarReservasProfesor
        public void listarReservasProfesor() {
            Consola.mostrarCabecera("Listar reservas profesor");
            Profesor profesor = new Profesor(Consola.leerNombreProfesor(),CORREO_VALIDO);
               
            Reserva[]reservasProfesor = controlador.getReservasProfesor(profesor);
            if (reservasProfesor.length != 0) {
                    for (Reserva reserva : reservasProfesor) {
                        if(reserva!=null){
                            System.out.println(reserva);
                        }
                    }    
            } else {
                    System.out.println("No hay reservas a ese profesor.");
            }
                    
             
         }
                            
                            
    	//Método listarReservasPermanencia
        public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas profesor");
        Permanencia permanenciaReserva=new Permanencia(Consola.leerDia(),Consola.leerTramo());
		Reserva[] reservasPermanencia = controlador.getReservasPermanencia(permanenciaReserva);
                
		if (reservasPermanencia.length != 0) {
			for (Reserva reserva : reservasPermanencia) {
                            if(reserva!=null){
     
				System.out.println(reservasPermanencia);
                            }
                        }
		}else{
			System.out.println("No hay reservas.");
		}
	}
               
                    
    	//Método consultarDisponibilidad
        public void consultarDisponibilidad(){
    		Consola.mostrarCabecera("Consultar disponibilidad");
            Permanencia permanencia=new Permanencia(Consola.leerDia(),Consola.leerTramo());
            boolean aulaDisponible= controlador.consultarDisponibilidad(Consola.leerAula(),permanencia);
            
            if(aulaDisponible==true){
                
                System.out.println("Aula disponible para el tramo y día indicado");
            }else{
                System.out.println("Aula no disponible para el tramo y día indicados");
            }
        
        }

 
}

