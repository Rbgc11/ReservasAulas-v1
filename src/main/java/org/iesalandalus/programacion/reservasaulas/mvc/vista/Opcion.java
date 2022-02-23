package org.iesalandalus.programacion.reservasaulas.mvc.vista;


public enum Opcion {
	//Opciones
	    INSERTAR_AULA("Insertar aula") {
	        public void ejecutar() {
	            Vista.insertarAula();
	        }
	    },
	    BORRAR_AULA("Borrar aula") {
	        public void ejecutar() {
	            Vista.borrarAula();
	        }
	    },
	    BUSCAR_AULA("Buscar aula") {
	        public void ejecutar() {
	            Vista.buscarAula();
	        }
	    },
	    LISTAR_AULAS("Listar aulas") {
	        public void ejecutar() {
	            Vista.listarAulas();
	        }
	    },   
	    INSERTAR_PROFESOR("Insertar profesor") {
	        public void ejecutar() {
	            Vista.insertarProfesor();
	        }
	    },
	    BORRAR_PROFESOR("Borrar profesor") {
	        public void ejecutar() {
	            Vista.borrarProfesor();
	        }
	    },
	    BUSCAR_PROFESOR("Buscar profesor") {
	        public void ejecutar() {
	            Vista.buscarProfesor();
	        }
	    },
	    LISTAR_PROFESORES("Listar profesores") {
	        public void ejecutar() {
	            Vista.listarProfesores();
	        }
	    },
	    INSERTAR_RESERVA("Insertar reserva") {
	        public void ejecutar() {
	            Vista.realizarReserva();
	        }
	    },
	    BORRAR_RESERVA("Borrar reserva") {
	        public void ejecutar() {
	            Vista.anularReserva();
	        }
	    },
	   
	    LISTAR_RESERVAS("Listar reservas") {
	        public void ejecutar() {
	            Vista.listarReservas();
	        }
	    }, 
	    LISTAR_RESERVAS_AULAS("Listar reservas aulas") {
	        public void ejecutar() {
	            Vista.listarReservasAula();
	        }
	    }, 
	    LISTAR_RESERVAS_PROFESOR("Listar reservas profesor") {
	        public void ejecutar() {
	           Vista.listarReservasProfesor();
	        }
	    }, 
	    LISTAR_RESERVAS_PERMANENCIA("Listar reservas permanencia") {
	        public void ejecutar() {
	            Vista.listarReservasPermanencia();
	        }
	    },
	    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad"){
	        public void ejecutar(){
	            Vista.consultarDisponibilidad();
	        }
	    },
		 SALIR("Salir") {
		        public void ejecutar() {
		            Vista.salir();
		        }
		    }; 
	
	//Atributos
	private static Vista Vista;
	private String mensajeAmostrar;

	//Método opcion
	private Opcion(String mensajeAmostrar){
	    this.mensajeAmostrar = mensajeAmostrar;
	}
	
	//GetMensaje
	public String getMensaje(){
	    return this.mensajeAmostrar;

	}

	//Método ejecutar
	public abstract void ejecutar();

	//SetVista
	protected static void setVista(Vista vista) {
		if (vista == null) {
			throw new NullPointerException("La vista no pueda ser nula.");
		}
		Opcion.Vista = vista;
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal() + 1, mensajeAmostrar);
	}

	//Método getOpcionSegunOrdinal
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
	    if (!esOrdinalValido(ordinal)) {
	        throw new IllegalArgumentException("El ordinal de la opción no es válido");
	    }
	    return values()[ordinal];
}
	
	//Método OrdinalValido
	public static boolean esOrdinalValido(int ordinal) {
	    return (ordinal >= 0 && ordinal <= values().length - 1);
	    }

	}

