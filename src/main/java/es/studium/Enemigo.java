package es.studium;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="enemigos")
public class Enemigo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnemigo;
	private String nombreEnemigo;
	private int vitalidadEnemigo;
	private int defensaEnemigo;
	private int ataqueMax;
	private int ataqueMin;
	
	public Enemigo() 
	{
		idEnemigo = 0;
		nombreEnemigo = "";
		vitalidadEnemigo = 0;
		defensaEnemigo = 0;
		ataqueMax = 0;
		ataqueMin = 0;
	}
	
	public Enemigo(int id, String nombre, int vit, int def, int atqMax, int atqMin) 
	{
		this.idEnemigo = id;
		this.nombreEnemigo = nombre;
		this.vitalidadEnemigo = vit;
		this.defensaEnemigo = def;
		this.ataqueMax = atqMax;
		this.ataqueMin = atqMin;
	}

	public int getIdEnemigo()
	{
		return idEnemigo;
	}

	public void setIdEnemigo(int idEnemigo)
	{
		this.idEnemigo = idEnemigo;
	}

	public String getNombreEnemigo()
	{
		return nombreEnemigo;
	}

	public void setNombreEnemigo(String nombreEnemigo)
	{
		this.nombreEnemigo = nombreEnemigo;
	}

	public int getVitalidadEnemigo()
	{
		return vitalidadEnemigo;
	}

	public void setVitalidadEnemigo(int vitalidadEnemigo)
	{
		this.vitalidadEnemigo = vitalidadEnemigo;
	}

	public int getDefensaEnemigo()
	{
		return defensaEnemigo;
	}

	public void setDefensaEnemigo(int defensaEnemigo)
	{
		this.defensaEnemigo = defensaEnemigo;
	}

	public int getAtaqueMax()
	{
		return ataqueMax;
	}

	public void setAtaqueMax(int ataqueMax)
	{
		this.ataqueMax = ataqueMax;
	}

	public int getAtaqueMin()
	{
		return ataqueMin;
	}

	public void setAtaqueMin(int ataqueMin)
	{
		this.ataqueMin = ataqueMin;
	}

}
