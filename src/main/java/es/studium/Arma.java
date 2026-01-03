package es.studium;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="armas")
public class Arma
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArma;
	private String nombreArma;
	private int minArma;
	private int maxArma;
	private int precioArma;
	
	public Arma() 
	{
		idArma = 0;
		nombreArma = "";
		minArma = 0;
		maxArma = 0;
		precioArma = 0;
	}
	
	public Arma(int id, String nombre, int min, int max, int precio) 
	{
		this.idArma = id;
		this.nombreArma = nombre;
		this.minArma = min;
		this.maxArma = max;
		this.precioArma = precio;
	}

	public int getIdArma()
	{
		return idArma;
	}

	public void setIdArma(int idArma)
	{
		this.idArma = idArma;
	}

	public String getNombreArma()
	{
		return nombreArma;
	}

	public void setNombreArma(String nombreArma)
	{
		this.nombreArma = nombreArma;
	}

	public int getMinArma()
	{
		return minArma;
	}

	public void setMinArma(int minArma)
	{
		this.minArma = minArma;
	}

	public int getMaxArma()
	{
		return maxArma;
	}

	public void setMaxArma(int maxArma)
	{
		this.maxArma = maxArma;
	}

	public int getPrecioArma()
	{
		return precioArma;
	}

	public void setPrecioArma(int precioArma)
	{
		this.precioArma = precioArma;
	}

}
