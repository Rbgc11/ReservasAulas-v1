package org.iesalandalus.programacion.reservasaulas.mvc.modelo;


	import javax.naming.OperationNotSupportedException; 
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
	import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
	
	public class Modelo {
		private static int CAPACIDAD = 10;
		 private Profesores profesores;
		    private Aulas aulas;
		    private Reservas reservas;
		    
		    public Modelo(){   
		    	aulas = new Aulas(CAPACIDAD);
				profesores = new Profesores(CAPACIDAD);
				reservas = new Reservas(CAPACIDAD);
		    }
		        
		    public Aula[] getAulas(){
		        return aulas.get();
		    }
		    
		    public int getNumAulas(){
		        return getAulas().length;
		    }
		    
		    public String[] representarAulas(){
		        return aulas.representar();
		    }
		    
		    public Aula buscarAula(Aula aula){
		        return aulas.buscar(aula);
		    }
		    
		    public void insertarAula(Aula aula) throws OperationNotSupportedException{
		        aulas.insertar(aula);
		    }
		    
		    public void borrarAula(Aula aula) throws OperationNotSupportedException{
		        aulas.borrar(aula);
		    }
		    
		    public Profesor[] getProfesores(){
		        return profesores.get();
		    }
		    
		    public int getNumProfesores(){
		        return  getProfesores().length;
		    }
		    
		    public String[] representarProfesores(){
		        return profesores.representar();
		    }
		    
		    public Profesor buscarProfesor(Profesor profesor){
		        return profesores.buscar(profesor);
		    }
		    
		    public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException{
		        profesores.insertar(profesor);
		    }
		    
		    public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException{
		        profesores.borrar(profesor);
		    }
		    
		    public Reserva[] getReservas(){
		        return reservas.get();
		    }
		    
		    public int getNumReservas(){
		        return getReservas().length;
		    }
		    
		    public String[] representarReservas(){
		        return reservas.representar();
		    }
		    
		    public Reserva buscarReserva(Reserva reserva){
		        return reservas.buscar(reserva);
		    }
		    
		    public void realizarReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException{
		        reservas.insertar(reserva);
		    }
		    
		    public void anularReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException{
		        reservas.borrar(reserva);
		    }
		    
		    public Reserva[] getReservasAula(Aula aula){
		        return reservas.getReservasAula(aula);
		    }
		    
		    public Reserva[] getReservasProfesor(Profesor profesor){
		        return reservas.getReservasProfesor(profesor);
		    }
		    
		    public Reserva[] getReservasPermanencia(Permanencia permanencia){
		        return reservas.getReservasPermanencia(permanencia);
		    }
		    
		    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia){
		        return reservas.consultarDisponibilidad(aula, permanencia);
		    
		    }
		    
		    
		}

