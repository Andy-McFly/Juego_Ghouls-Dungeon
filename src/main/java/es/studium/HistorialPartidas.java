package es.studium;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="historial")
public class HistorialPartidas
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPartida;
	private String nombreJugador;
	private int nivel;
	private int combateFinal;
	private String fecha;
	private int oroEquipo;
	private int oroVida;
	
	public HistorialPartidas() 
	{
		idPartida = 0;
		nombreJugador = "";
		nivel = 0;
		combateFinal = 0;
		fecha = "";
		oroEquipo = 0;
		oroVida = 0;
	}
	
	public HistorialPartidas(int id, String nombreJugador, int nivel, int combate, String fecha, int oroEquipo, int oroVida) 
	{
		this.idPartida = id;
		this.nombreJugador = nombreJugador;
		this.nivel = nivel;
		this.combateFinal = combate;
		this.fecha = fecha;
		this.oroEquipo = oroEquipo;
		this.oroVida = oroVida;
	}

	public int getIdPartida()
	{
		return idPartida;
	}

	public void setIdPartida(int idPartida)
	{
		this.idPartida = idPartida;
	}

	public String getNombreJugador()
	{
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador)
	{
		this.nombreJugador = nombreJugador;
	}

	public int getNivel()
	{
		return nivel;
	}

	public void setNivel(int nivel)
	{
		this.nivel = nivel;
	}

	public int getCombateFinal()
	{
		return combateFinal;
	}

	public void setCombateFinal(int combateFinal)
	{
		this.combateFinal = combateFinal;
	}

	public String getFecha()
	{
		return fecha;
	}

	public void setFecha(String fecha)
	{
		this.fecha = fecha;
	}

	public int getOroEquipo()
	{
		return oroEquipo;
	}

	public void setOroEquipo(int oroEquipo)
	{
		this.oroEquipo = oroEquipo;
	}

	public int getOroVida()
	{
		return oroVida;
	}

	public void setOroVida(int oroVida)
	{
		this.oroVida = oroVida;
	}

}
