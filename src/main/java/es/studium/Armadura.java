package es.studium;

import jakarta.persistence.*;

@Entity
@Table(name="armaduras")
public class Armadura
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArmadura;
	private String nombreArmadura;
	private int defensaArmadura;
	private int precioArmadura;
	
	public Armadura() 
	{
		idArmadura = 0;
		nombreArmadura = "";
		defensaArmadura = 0;
		precioArmadura = 0;
	}
	
	public Armadura(int id, String nombre, int defensa, int precio) 
	{
		this.idArmadura = id;
		this.nombreArmadura = nombre;
		this.defensaArmadura = defensa;
		this.precioArmadura = precio;
	}

	public int getIdArmadura()
	{
		return idArmadura;
	}

	public void setIdArmadura(int idArmadura)
	{
		this.idArmadura = idArmadura;
	}

	public String getNombreArmadura()
	{
		return nombreArmadura;
	}

	public void setNombreArmadura(String nombreArmadura)
	{
		this.nombreArmadura = nombreArmadura;
	}

	public int getDefensaArmadura()
	{
		return defensaArmadura;
	}

	public void setDefensaArmadura(int defensaArmadura)
	{
		this.defensaArmadura = defensaArmadura;
	}

	public int getPrecioArmadura()
	{
		return precioArmadura;
	}

	public void setPrecioArmadura(int precioArmadura)
	{
		this.precioArmadura = precioArmadura;
	}

}
