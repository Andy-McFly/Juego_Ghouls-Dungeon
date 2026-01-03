package es.studium;

import java.awt.Image;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Modelo
{
	public List<Jugador> datosJugador() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String consulta = "FROM " + Jugador.class.getName(); 
		List<Jugador> resultado = session.createQuery(consulta, Jugador.class).list(); 
		
		session.close(); 
		return resultado;
	}
	
	public List<Armadura> datosArmaduras() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String consulta = "FROM " + Armadura.class.getName(); 
		List<Armadura> listaResultado = session.createQuery(consulta, Armadura.class).list(); 
		
		session.close(); 
		return listaResultado;
	}
	
	public List<Arma> datosArmas() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String consulta = "FROM " + Arma.class.getName(); 
		List<Arma> listaResultado = session.createQuery(consulta, Arma.class).list(); 
		
		session.close(); 
		return listaResultado;
	}
	
	public List<Enemigo> datosEnemigos() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String consulta = "FROM " + Enemigo.class.getName(); 
		List<Enemigo> listaResultado = session.createQuery(consulta, Enemigo.class).list(); 
		
		session.close(); 
		return listaResultado;
	}
	
	public void guardarPartida(HistorialPartidas partida) 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(partida);
		tx.commit();
		session.close();
	}
	
	public List<HistorialPartidas> datosPartidas() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String consulta = "FROM " + HistorialPartidas.class.getName(); 
		List<HistorialPartidas> listaResultado = session.createQuery(consulta, HistorialPartidas.class).list(); 
		
		session.close(); 
		return listaResultado;
	}
	
	public Image[] actualizarImgJugador(Armadura armadura) 
	{
		Image[] imagenesJugador = new Image[5];
		
		switch(armadura.getNombreArmadura()) 
    	{
    		case "Calzones cómodos":
    			imagenesJugador[0] = new ImageIcon(getClass().getResource("img/jugador.png")).getImage();
    			imagenesJugador[1] = new ImageIcon(getClass().getResource("img/jugadorAtaca.png")).getImage();
    			imagenesJugador[2] = new ImageIcon(getClass().getResource("img/jugadorHerido.png")).getImage();
    			imagenesJugador[3] = new ImageIcon(getClass().getResource("img/jugadorConcentrar.png")).getImage();
    			imagenesJugador[4] = new ImageIcon(getClass().getResource("img/jugadorGanar.png")).getImage();
    			break;
    		case "Armadura de hierro":
    			imagenesJugador[0] = new ImageIcon(getClass().getResource("img/jugadorHierro.png")).getImage();
    			imagenesJugador[1] = new ImageIcon(getClass().getResource("img/jugadorHierroAtaca.png")).getImage();
    			imagenesJugador[2] = new ImageIcon(getClass().getResource("img/jugadorHierroHerido.png")).getImage();
    			imagenesJugador[3] = new ImageIcon(getClass().getResource("img/jugadorHierroConcentrar.png")).getImage();
    			imagenesJugador[4] = new ImageIcon(getClass().getResource("img/jugadorHierroGanar.png")).getImage();
    			break;
    		case "Armadura de plata":
    			imagenesJugador[0] = new ImageIcon(getClass().getResource("img/jugadorPlata.png")).getImage();
    			imagenesJugador[1] = new ImageIcon(getClass().getResource("img/jugadorPlataAtaca.png")).getImage();
    			imagenesJugador[2] = new ImageIcon(getClass().getResource("img/jugadorPlataHerido.png")).getImage();
    			imagenesJugador[3] = new ImageIcon(getClass().getResource("img/jugadorPlataConcentrar.png")).getImage();
    			imagenesJugador[4] = new ImageIcon(getClass().getResource("img/jugadorPlataGanar.png")).getImage();
    			break;
    		case "Armadura de oro":
    			imagenesJugador[0] = new ImageIcon(getClass().getResource("img/jugadorOro.png")).getImage();
    			imagenesJugador[1] = new ImageIcon(getClass().getResource("img/jugadorOroAtaca.png")).getImage();
    			imagenesJugador[2] = new ImageIcon(getClass().getResource("img/jugadorOroHerido.png")).getImage();
    			imagenesJugador[3] = new ImageIcon(getClass().getResource("img/jugadorOroConcentrar.png")).getImage();
    			imagenesJugador[4] = new ImageIcon(getClass().getResource("img/jugadorOroGanar.png")).getImage();
    			break;
    	}
		return imagenesJugador;
	}
	
	public int ataqueJugador(Jugador jugador, Arma arma, Enemigo enemigo) 
	{
		Random random = new Random();
		int ataqueArma = random.nextInt(arma.getMaxArma() - arma.getMinArma() + 1) + arma.getMinArma();
		int ataqueJugador = jugador.getAtaque() + ataqueArma;
		int defensaEnemigo = enemigo.getDefensaEnemigo();
		
		if(ataqueJugador > defensaEnemigo) 
		{
			ataqueJugador = ataqueJugador - defensaEnemigo;
		}
		else if(defensaEnemigo >= ataqueJugador) 
		{
			ataqueJugador = 0;
		}
		
		return ataqueJugador;
	}
	
	public int ataqueEnemigo(Jugador jugador, Armadura armadura, Enemigo enemigo) 
	{
		Random random = new Random();
		int ataqueEnemigo = random.nextInt(enemigo.getAtaqueMax() - enemigo.getAtaqueMin() + 1) + enemigo.getAtaqueMin();
		int defensaJugador = jugador.getDefensa() + armadura.getDefensaArmadura();
		System.out.println("Defensa" + defensaJugador);
		if(ataqueEnemigo > defensaJugador) 
		{
			ataqueEnemigo = ataqueEnemigo - defensaJugador;
		}
		else if(defensaJugador >= ataqueEnemigo) 
		{
			ataqueEnemigo = 0;
		}
		
		return ataqueEnemigo;
	}
	
	public Image actualizarHUDArmadura(Armadura armadura) 
	{
		Image img = new ImageIcon(getClass().getResource("img/ArmaduraInicial.png")).getImage();;
		
		switch(armadura.getNombreArmadura()) 
    	{
    		case "Calzones cómodos":
    			img = new ImageIcon(getClass().getResource("img/ArmaduraInicial.png")).getImage();
    			break;
    			
    		case "Armadura de hierro":
    			img = new ImageIcon(getClass().getResource("img/ArmaduraHierro.png")).getImage();
    			break;
    		case "Armadura de plata":
    			img = new ImageIcon(getClass().getResource("img/ArmaduraPlata.png")).getImage();
    			break;
    		case "Armadura de oro":
    			img = new ImageIcon(getClass().getResource("img/ArmaduraOro.png")).getImage();
    			break;
    	}
		return img;
	}
	
	public Image actualizarHUDArma(Arma arma) 
	{
		Image img = new ImageIcon(getClass().getResource("img/espadaOxidada.png")).getImage();;
		
		switch(arma.getNombreArma()) 
    	{
    		case "Espada Oxidada":
    			img = new ImageIcon(getClass().getResource("img/espadaOxidada.png")).getImage();
    			break;
    			
    		case "Espada de Hierro":
    			img = new ImageIcon(getClass().getResource("img/espadaHierro.png")).getImage();
    			break;
    		case "Espada de Plata":
    			img = new ImageIcon(getClass().getResource("img/espadaPlata.png")).getImage();
    			break;
    		case "Espada de Oro":
    			img = new ImageIcon(getClass().getResource("img/espadaOro.png")).getImage();
    			break;
    	}
		return img;
	}
	
	public Image comprobarMuerte(int vitalidad, Image imgEnemigoActual, Image imgMuerteEnemigo) 
	{
		Image imgEnemigo = imgEnemigoActual;
		if(vitalidad <= 0) 
		{
			imgEnemigo = imgMuerteEnemigo;
		}
		
		return imgEnemigo;
	}

	public void turnoEnemigosFase1(Jugador jugador, Armadura armadura, Enemigo enemigo1, Enemigo enemigo2, boolean defiende, JuegoFase1 fase,
			Runnable actualizarVista, Runnable devolverTurno)
	{
		Thread hilo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					if(enemigo1.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo1();
						int ataque1 = ataqueEnemigo(jugador, armadura, enemigo1);
						jugador.setVitalidad(jugador.getVitalidad() - ataque1);
						String log = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log = jugador.getNombre() + " muere por ataque de " + enemigo1.getNombreEnemigo();
						}
						else 
						{
							log = enemigo1.getNombreEnemigo() + " inflige " + ataque1 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log);

						actualizarVista.run();
					}

					if(enemigo2.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo2();
						int ataque2 = ataqueEnemigo(jugador, armadura, enemigo2);
						jugador.setVitalidad(jugador.getVitalidad() - ataque2);
						String log2 = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log2 = jugador.getNombre() + " muere por ataque de " + enemigo2.getNombreEnemigo();
						}
						else 
						{
							log2 = enemigo2.getNombreEnemigo() + " inflige " + ataque2 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log2);

						actualizarVista.run();
					}
					

					if (defiende)
					{
						jugador.setDefensa(jugador.getDefensa() - 10);
					}

					devolverTurno.run();
				} 
				catch (InterruptedException e){}
			}
		});

		hilo.start();
	}
	
	public void turnoEnemigosFase2(Jugador jugador, Armadura armadura, Enemigo enemigo1, Enemigo enemigo2, boolean defiende, JuegoFase2 fase,
			Runnable actualizarVista, Runnable devolverTurno)
	{
		Thread hilo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					if(enemigo1.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo1();
						int ataque1 = ataqueEnemigo(jugador, armadura, enemigo1);
						jugador.setVitalidad(jugador.getVitalidad() - ataque1);
						String log = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log = jugador.getNombre() + " muere por ataque de " + enemigo1.getNombreEnemigo();
						}
						else 
						{
							log = enemigo1.getNombreEnemigo() + " inflige " + ataque1 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log);

						actualizarVista.run();
					}

					if(enemigo2.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo2();
						int ataque2 = ataqueEnemigo(jugador, armadura, enemigo2);
						jugador.setVitalidad(jugador.getVitalidad() - ataque2);
						String log2 = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log2 = jugador.getNombre() + " muere por ataque de " + enemigo2.getNombreEnemigo();
						}
						else 
						{
							log2 = enemigo2.getNombreEnemigo() + " inflige " + ataque2 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log2);

						actualizarVista.run();
					}
					

					if (defiende)
					{
						jugador.setDefensa(jugador.getDefensa() - 10);
					}

					devolverTurno.run();
				} 
				catch (InterruptedException e){}
			}
		});

		hilo.start();
	}
	
	public void turnoEnemigosFase3(Jugador jugador, Armadura armadura, Enemigo enemigo1, Enemigo enemigo2, boolean defiende, JuegoFase3 fase,
			Runnable actualizarVista, Runnable devolverTurno)
	{
		Thread hilo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					if(enemigo1.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo1();
						int ataque1 = ataqueEnemigo(jugador, armadura, enemigo1);
						jugador.setVitalidad(jugador.getVitalidad() - ataque1);
						String log = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log = jugador.getNombre() + " muere por ataque de " + enemigo1.getNombreEnemigo();
						}
						else 
						{
							log = enemigo1.getNombreEnemigo() + " inflige " + ataque1 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log);

						actualizarVista.run();
					}

					if(enemigo2.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo2();
						int ataque2 = ataqueEnemigo(jugador, armadura, enemigo2);
						jugador.setVitalidad(jugador.getVitalidad() - ataque2);
						String log2 = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log2 = jugador.getNombre() + " muere por ataque de " + enemigo2.getNombreEnemigo();
						}
						else 
						{
							log2 = enemigo2.getNombreEnemigo() + " inflige " + ataque2 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log2);

						actualizarVista.run();
					}
					

					if (defiende)
					{
						jugador.setDefensa(jugador.getDefensa() - 10);
					}

					devolverTurno.run();
				} 
				catch (InterruptedException e){}
			}
		});

		hilo.start();
	}
	
	public void turnoEnemigosFase4(Jugador jugador, Armadura armadura, Enemigo enemigo1, Enemigo enemigo2, boolean defiende, JuegoFase4 fase,
			Runnable actualizarVista, Runnable devolverTurno)
	{
		Thread hilo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					if(enemigo1.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo1();
						int ataque1 = ataqueEnemigo(jugador, armadura, enemigo1);
						jugador.setVitalidad(jugador.getVitalidad() - ataque1);
						String log = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log = jugador.getNombre() + " muere por ataque de " + enemigo1.getNombreEnemigo();
						}
						else 
						{
							log = enemigo1.getNombreEnemigo() + " inflige " + ataque1 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log);

						actualizarVista.run();
					}

					if(enemigo2.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo2();
						int ataque2 = ataqueEnemigo(jugador, armadura, enemigo2);
						jugador.setVitalidad(jugador.getVitalidad() - ataque2);
						String log2 = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log2 = jugador.getNombre() + " muere por ataque de " + enemigo2.getNombreEnemigo();
						}
						else 
						{
							log2 = enemigo2.getNombreEnemigo() + " inflige " + ataque2 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log2);

						actualizarVista.run();
					}
					

					if (defiende)
					{
						jugador.setDefensa(jugador.getDefensa() - 10);
					}

					devolverTurno.run();
				} 
				catch (InterruptedException e){}
			}
		});

		hilo.start();
	}
	
	public void turnoEnemigosFase5(Jugador jugador, Armadura armadura, Enemigo enemigo1, Enemigo enemigo2, boolean defiende, JuegoFase5 fase,
			Runnable actualizarVista, Runnable devolverTurno)
	{
		Thread hilo = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					if(enemigo1.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo1();
						int ataque1 = ataqueEnemigo(jugador, armadura, enemigo1);
						jugador.setVitalidad(jugador.getVitalidad() - ataque1);
						String log = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log = jugador.getNombre() + " muere por ataque de " + enemigo1.getNombreEnemigo();
						}
						else 
						{
							log = enemigo1.getNombreEnemigo() + " inflige " + ataque1 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log);

						actualizarVista.run();
					}

					if(enemigo2.getVitalidadEnemigo() > 0 && jugador.getVitalidad() > 0) 
					{
						Thread.sleep(3000);
						fase.ataqueEnemigo2();
						int ataque2 = ataqueEnemigo(jugador, armadura, enemigo2);
						jugador.setVitalidad(jugador.getVitalidad() - ataque2);
						String log2 = "";
						if(jugador.getVitalidad() <= 0) 
						{
							jugador.setVitalidad(0);
							log2 = jugador.getNombre() + " muere por ataque de " + enemigo2.getNombreEnemigo();
						}
						else 
						{
							log2 = enemigo2.getNombreEnemigo() + " inflige " + ataque2 + " puntos de daño a " + jugador.getNombre();
						}
						
						fase.actualizarLog(log2);

						actualizarVista.run();
					}
					

					if (defiende)
					{
						jugador.setDefensa(jugador.getDefensa() - 10);
					}

					devolverTurno.run();
				} 
				catch (InterruptedException e){}
			}
		});

		hilo.start();
	}
	
}
