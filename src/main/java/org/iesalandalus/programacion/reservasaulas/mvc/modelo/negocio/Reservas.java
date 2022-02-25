package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	// Atributos
	List<Reserva> coleccionReservas;

	// Constructor por defecto
	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}

	// Constructor copia
	public Reservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException(" No se pueden copiar reservas nulas.");
		} else {
			setReservas(reservas);
		}
	}

	// Método setReservas
	private void setReservas(Reservas reservas) {
		if (reservas == null) {
			throw new NullPointerException("No se puede copiar una reserva nula.");
		} else {
			this.coleccionReservas = reservas.getReservas();
		}
	}

	// Método copiaProfundaReservas
	private List<Reserva> copiaProfundaReservas(List<Reserva> listaReservas) {
		List<Reserva> copiaProReservas = new ArrayList<>();
		Iterator<Reserva> iterador = listaReservas.iterator();
		while (iterador.hasNext()) {
			copiaProReservas.add(new Reserva(iterador.next()));
		}
		return copiaProReservas;
	}

	// Método List<Reservas> getReservas()
	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	// Método getNumReservas
	public int getNumReservas() {
		return coleccionReservas.size();

	}

	// Método insertar
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("No se puede realizar una reserva nula.");
		} else if (buscar(reserva) == null) {
			coleccionReservas.add(new Reserva(reserva));
		} else {
			throw new OperationNotSupportedException("La reserva ya existe.");
		}
	}

	// Método esMesSiguienteOPosterior(Reserva)
	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: La reserva no puede ser nula");
		}
		boolean mesSiguiente = false;
		Month mes = reserva.getPermanencia().getDia().getMonth();
		Month mesActual = LocalDate.now().getMonth();
		if (mes.getValue() > mesActual.getValue()) {
			mesSiguiente = true;
		}
		return mesSiguiente;

	}

	// Método List<Reserva> getReservasProfesorMes(Profesor, LocalDate)
	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate fecha) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo");
		} else if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		List<Reserva> reservasMes = new ArrayList<>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			Month mes = reserva.getPermanencia().getDia().getMonth();
			Month mesFecha = fecha.getMonth();
			if (profesor.equals(reserva.getProfesor()) && mes.getValue() == mesFecha.getValue()) {
				reservasMes.add(new Reserva(reserva));
			}
		}
		return reservasMes;
	}

	// Método Reserva getReservaAulaDia(Aula, LocalDate)
	private Reserva getReservaAulaDia(Aula aula, LocalDate fecha) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula");
		} else if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		Reserva reservaADia = null;
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (aula.equals(reserva.getAula()) && fecha.equals(reserva.getPermanencia().getDia())) {
				reservaADia = new Reserva(reserva);
			}
		}
		return reservaADia;
	}

	// Método buscar
	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("No se puede buscar un reserva nula.");
		}
		// Se mira si hay una Reserva que exista en coleccionReservas
		Reserva reservaEncontrada = null;
		int indice = coleccionReservas.indexOf(reserva);
		if (indice == -1) {
			reservaEncontrada = null;
		} else {
			reservaEncontrada = new Reserva(coleccionReservas.get(indice));
		}
		return reservaEncontrada;
	}

	// Método borrar
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("No se puede anular una reserva nula.");
		} else if (buscar(reserva) == null) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		} else {
			coleccionReservas.remove(coleccionReservas.indexOf(reserva));
		}
	}

	// Metodo representar
	public List<String> representar() {
		List<String> representacion = new ArrayList<String>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			representacion.add(iterador.next().toString());
		}
		return representacion;
	}

	// Método List<Reserva> getReservasProfesor(Profesor)
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("No se puede reservar con un profesor nulo.");
		}
		List<Reserva> listaResProfesor = new ArrayList<>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (profesor.equals(reserva.getProfesor())) {
				listaResProfesor.add(new Reserva(reserva));
			}
		}
		return listaResProfesor;
	}

	// Método List<Reserva> getReservasAula(Aula)
	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException(" No se puede reservar un aula nula.");
		}
		List<Reserva> listaResAula = new ArrayList<>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (aula.equals(reserva.getAula())) {
				listaResAula.add(new Reserva(reserva));
			}
		}
		return listaResAula;
	}

	// Método List<Reserva> getReservasPermanencia(Permanencia)
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("No se puede reservar con una permanencia nula.");
		}
		List<Reserva> listaResPermanencia = new ArrayList<>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (permanencia.equals(reserva.getPermanencia())) {
				listaResPermanencia.add(new Reserva(reserva));
			}
		}
		return listaResPermanencia;
	}

	// Método consultarDisponibilidad(Aula,Permanencia)
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("No se puede consultar la disponibilidad de una permanencia nula.");
		}
		boolean disponibilidad = true;
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (permanencia.equals(reserva.getPermanencia()) && aula.equals(reserva.getAula())) {
				disponibilidad = false;
			}
		}
		return disponibilidad;
	}
}