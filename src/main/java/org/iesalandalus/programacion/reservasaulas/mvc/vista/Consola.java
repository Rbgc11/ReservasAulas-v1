	package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import  org.iesalandalus.programacion.utilidades.Entrada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;


public class Consola {

	  private static final DateTimeFormatter FORMATO_DIA= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    

	    private Consola() {   
	    }
	    
	    //Método mostrarMenu
	    public static void mostrarMenu() {
	            mostrarCabecera("Reservas de aulas");
	            for (Opcion opcion: Opcion.values()) {
	                    System.out.println(opcion);
	            }
	    }

	    //Método mostrarCabecera
	    public static void mostrarCabecera(String mensaje) {
	            System.out.printf("%n%s%n", mensaje);
	            String cadena = "%0" + mensaje.length() + "d%n";
	            System.out.println(String.format(cadena, 0).replace("0", "-"));
	    }

	    //Método elegirOpcion
	    public static int elegirOpcion() {
	            int ordinalOpcion;
	                
	            do {
	                    System.out.print("Escoge una opción: ");
	                    ordinalOpcion = Entrada.entero() - 1;
	            } while (!Opcion.esOrdinalValido(ordinalOpcion));
	         return ordinalOpcion;
	    }

	    //Método leerAula
	    public static Aula leerAula(){
	    
	        Aula aula=new Aula(leerNombreAula());
	        return aula;
	    }
	    
	    //Método leerNombreAula
	    public static String leerNombreAula(){
	    	String nombreAula = null;
	        do{
	            System.out.println("Introduce nombre del aula");
	            nombreAula=Entrada.cadena();
	        }while(nombreAula==null||nombreAula.equals(""));
	        return nombreAula;
	    }
	    
	    //Método leerProfesor
	    public static Profesor leerProfesor(){
			String nombreProfesor = leerNombreProfesor();

	        System.out.println("Introduce el correo: ");
	        String correo = Entrada.cadena();
	        
	        System.out.println("Introduce el teléfono: ");
	        String telefono = Entrada.cadena();
	        
	        
	        if(telefono==null || telefono.equals("")){
	        	return new Profesor(leerNombreProfesor(), correo);
	        }else{
	        	return new Profesor(leerNombreProfesor(), correo, telefono);
	        }
	     
	     
	    }
	    

	    
	    //Método leerNombreProfesor
	    public static String leerNombreProfesor(){
	            System.out.println("Introduce nombre del profesor: ");
	            String nombre=Entrada.cadena();       
	        return nombre;
	    }
	    
	    //Método leerTramo
	    public static Tramo leerTramo(){
	        int numero= 0;
	     do{
	        System.out.println("Elige tramo horario: 1=Por la mañana y 2 = Por la tarde");
	        numero=Entrada.entero();
	     }while(numero!=1&&numero!=2);
	     
	        if(numero==1){
	            return Tramo.MANANA;
	        }
	        if(numero==2){
	        
	            return Tramo.TARDE;
	        }
	        return null;
	    }
	   
	    //Método leerDia
	    public static LocalDate leerDia(){
	     LocalDate fecha=null;
	        do{
	        System.out.println("Introduce una fecha con formato dd/mm/aaaa: ");
	        fecha=LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
	       }while(fecha==null);
	        return fecha;
	    }
	}

