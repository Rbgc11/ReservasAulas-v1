package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
	//Atributos
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	//Constructor copia
	public Reserva(Reserva reserva)  {
		if(reserva == null) {
			throw new NullPointerException("No se puede copiar una reserva nula.");
		} else {
	       setProfesor(reserva.profesor);
	       setAula(reserva.aula);
	       setPermanencia(reserva.permanencia);
		}
	}

	//Setter y Getters
	private void setProfesor(Profesor profesor)  {
		if (profesor == null) {
			throw new NullPointerException("No pueden haber valores nulos");
		} else {
			this.profesor = profesor;
		}
	}

	public Profesor getProfesor() {
		return profesor;
	}

	private void setAula(Aula aula)  {
		if (aula == null) {
			throw new NullPointerException("No pueden haber valores nulos");
		} else {
			this.aula = new Aula(aula.getNombre());;
		}
	}

	public Aula getAula() {
		return aula;
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("No pueden haber valores nulos");
		} else {
			this.permanencia = new Permanencia(permanencia.getDia(), permanencia.getTramo());
		}
	}

	public Permanencia getPermanencia() {
		return permanencia;
	}



	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia)
				&& Objects.equals(profesor, other.profesor);
	}

	@Override
	public String toString() {
		return "Profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia + "";
	}

	
}