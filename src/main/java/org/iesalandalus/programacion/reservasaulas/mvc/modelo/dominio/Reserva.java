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
			this.profesor = new Profesor(profesor);
		}
	}

	public Profesor getProfesor() {
		return new Profesor(profesor);
	}

	private void setAula(Aula aula)  {
		if (aula == null) {
			throw new NullPointerException("No pueden haber valores nulos");
		} else {
			this.aula = new Aula(aula.getNombre());;
		}
	}

	public Aula getAula() {
		return new Aula(aula);
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("No pueden haber valores nulos");
		} else {
			this.permanencia = new Permanencia(permanencia.getDia(), permanencia.getTramo());
		}
	}

	public Permanencia getPermanencia() {
		return new Permanencia(permanencia);
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
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (permanencia == null) {
			if (other.permanencia != null)
				return false;
		} else if (!permanencia.equals(other.permanencia))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia + "";
	}

	
}