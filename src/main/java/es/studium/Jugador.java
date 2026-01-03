package es.studium;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="jugadores")
public class Jugador
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJugador;
	private String nombre;
	private int vitalidad;
	private int defensa;
	private int ataque;
	private int nivel;
	private int oro;
	
	public Jugador() 
	{
		idJugador = 0;
		nombre = "Arthur";
		vitalidad = 12;
		defensa = 0;
		ataque = 0;
		nivel = 0;
		oro = 50;
	}
	
	public Jugador(int id,String nombre, int vitalidad, int defensa, int ataque, int nivel, int oro) 
	{
		this.idJugador = id;
		this.nombre = nombre;
		this.vitalidad = vitalidad;
		this.defensa = defensa;
		this.ataque = ataque;
		this.nivel = nivel;
		this.oro = oro;
	}

	public int getIdJugador()
	{
		return idJugador;
	}

	public void setIdJugador(int idJugador)
	{
		this.idJugador = idJugador;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getVitalidad()
	{
		return vitalidad;
	}

	public void setVitalidad(int vitalidad)
	{
		this.vitalidad = vitalidad;
	}

	public int getDefensa()
	{
		return defensa;
	}

	public void setDefensa(int defensa)
	{
		this.defensa = defensa;
	}

	public int getAtaque()
	{
		return ataque;
	}

	public void setAtaque(int ataque)
	{
		this.ataque = ataque;
	}

	public int getNivel()
	{
		return nivel;
	}

	public void setNivel(int nivel)
	{
		this.nivel = nivel;
	}

	public int getOro()
	{
		return oro;
	}

	public void setOro(int oro)
	{
		this.oro = oro;
	}

}
