package es.studium;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sonido extends Thread
{
	private String rutaFichero;
	private Player player;
//	private boolean reproducir = true;
	
	public Sonido(String rutaFichero) 
	{
		this.rutaFichero = rutaFichero;
	}
	
	public void pararMusica()
    {
		try
        {
//            reproducir = false;
            if(player != null)
            {
                player.close();
            }
        }
        catch(Exception e){}
    }
	
	@Override
	public void run() 
	{
		try 
		{
			player = new Player(new FileInputStream(rutaFichero));
			player.play();
		}
		catch(Exception e)
		{
			System.out.println("Error al reproducir: " + rutaFichero);
		}
	}

}
