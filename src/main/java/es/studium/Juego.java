package es.studium;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Juego
{
	Modelo modelo = new Modelo();
//	String sndGanar = "sound/ganar.mp3";
//	String sndPerder = "sound/perder.mp3";
//	String sndFinalA = "sound/finalPortal.mp3";
//	String sndFinalB = "sound/finalAldea.mp3";
//	Sonido soundEfectos;
	
	Jugador jugador;
	JuegoIntro intro;
	JuegoTienda1 tienda1;
	JuegoTienda2 tienda2;
	JuegoFase1 fase1;
	JuegoFase2 fase2;
	JuegoFase3 fase3;
	JuegoFase4 fase4;
	JuegoFase5 fase5;
	JuegoFinal juegoFinal;
	
	CardLayout layout;
	JPanel panelCambio;
	JFrame vJuego;
	JFrame vPrincipal;
	
	boolean concentrado = false;
	boolean defiende = false;
	boolean turno = true;
	int resultadoPartida = 0;
	int fase = 0;
	int comprasEquipo = 0;
	int comprasVida = 0;
	
	List<Arma> armas = modelo.datosArmas();
	List<Armadura> armaduras = modelo.datosArmaduras();
	List<Enemigo> enemigos = modelo.datosEnemigos();
	Enemigo enemigo1 = enemigos.get(0);
	Enemigo enemigo2 = enemigos.get(1);
	Arma arma = armas.get(0);
	Armadura armadura = armaduras.get(0);
	
	String log = "";
	
	public Juego(JFrame vPrincipal, Jugador j) 
	{
		this.vPrincipal = vPrincipal;
		jugador = j;
		layout = new CardLayout();
		panelCambio = new JPanel(layout);
		intro = new JuegoIntro(this, jugador.getNombre());
		tienda1 = new JuegoTienda1(this, jugador, arma, armadura, armas.get(1), armaduras.get(1));
		
		vJuego = new JFrame();
		vJuego.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vJuego.dispose();
				vPrincipal.setVisible(true);
			}
		});
		vJuego.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vJuego.dispose();
				vPrincipal.setVisible(true);
			}
		});
		vJuego.setTitle("Ghouls n' Dungeon: Partida en curso");
		vJuego.setSize(900,700);
		vJuego.setLocationRelativeTo(null);
		vJuego.setResizable(false);
		vJuego.setVisible(true);
		vJuego.requestFocusInWindow();
		
		panelCambio.add(intro.panelFondo, "Intro");
		panelCambio.add(tienda1.panelFondo, "Tienda1");
		
		vJuego.add(panelCambio);
		layout.show(panelCambio, "Intro");
	}
	
	public void clickIntro() 
	{
		layout.show(panelCambio, "Tienda1");
	}
	
	public void clickTienda(Jugador j, Arma a, Armadura arm, int oroEquipo, int oroVida) 
	{
		jugador = j;
		arma = a;
		armadura = arm;
		
		comprasEquipo = comprasEquipo + oroEquipo;
		comprasVida = comprasVida + oroVida;
		
		resultadoPartida = 0;
		fase = 1;
		fase1 = new JuegoFase1(this, jugador, armadura, arma);
		panelCambio.add(fase1.panelFondo, "Fase1");
		fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
		layout.show(panelCambio, "Fase1");
	}
	
	public void clickTienda2(Jugador j, Arma a, Armadura arm, int oroEquipo, int oroVida) 
	{
		jugador = j;
		arma = a;
		armadura = arm;
		
		comprasEquipo = comprasEquipo + oroEquipo;
		comprasVida = comprasVida + oroVida;
		
		enemigo1 = enemigos.get(4);
		enemigo2 = enemigos.get(5);
		
		resultadoPartida = 0;
		fase = 3;
		fase3 = new JuegoFase3(this, jugador, armadura, arma);
		panelCambio.add(fase3.panelFondo, "Fase3");
		fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
		layout.show(panelCambio, "Fase3");
	}
	
	//FASE 1.
	public void clickFase1(int x, int y) 
	{
		if(turno) 
		{
			if (x >= 430 && x <= 430 + 71 && y >= 330 && y <= 330 + 64 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo1);
				enemigo1.setVitalidadEnemigo(enemigo1.getVitalidadEnemigo() - ataque);
				if(enemigo1.getVitalidadEnemigo() <= 0) 
				{
					enemigo1.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 15);
					log = enemigo1.getNombreEnemigo() + " ha muerto. Ganas " + 15 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo1.getNombreEnemigo();
				}
				fase1.actualizarLog(log);
				fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			} 
			else if (x >= 550 && x <= 550 + 68 && y >= 380 && y <= 380 + 71 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo2);
				enemigo2.setVitalidadEnemigo(enemigo2.getVitalidadEnemigo() - ataque);
				if(enemigo2.getVitalidadEnemigo() <= 0) 
				{
					enemigo2.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 15);
					log = enemigo2.getNombreEnemigo() + " ha muerto. Ganas " + 15 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo2.getNombreEnemigo();
				}
				fase1.actualizarLog(log);
				fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			if (x >= 180 && x <= 180 + 61 && y >= 360 && y <= 360 + 75 && jugador.getVitalidad() > 0)
			{
				jugador.setDefensa(jugador.getDefensa() + 10);
				defiende = true;
				fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			
			turno = false;
			fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			
			modelo.turnoEnemigosFase1(jugador, armadura, enemigo1, enemigo2, defiende, fase1,

					//Actualizar fase.
					new Runnable()
					{
						@Override
						public void run()
						{
							fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
						}
					},

					//Devolver turno.
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								Thread.sleep(2000);
								turno = true;
								defiende = false;
								resultadoPartida = comprobarPartida();
								fase1.actualizarFase1(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
							} catch (InterruptedException e){}
						}
					});
		}
		
    }
	
	public void ganarFase1() 
	{
		fase = 2;
		resultadoPartida = 0;
		turno = true;
		jugador.setVitalidad(jugador.getVitalidad() + 5);
		jugador.setNivel(jugador.getNivel() + 1);
		jugador.setAtaque(jugador.getAtaque() + 1);
		jugador.setDefensa(jugador.getDefensa() + 1);
		
		enemigo1 = enemigos.get(2);
		enemigo2 = enemigos.get(3);
		
		fase2 = new JuegoFase2(this, jugador, armadura, arma);
		panelCambio.add(fase2.panelFondo, "Fase2");
		fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
		layout.show(panelCambio, "Fase2");
	}
	
	//FASE 2.
	public void clickFase2(int x, int y) 
	{
		if(turno) 
		{
			if (x >= 430 && x <= 430 + 65 && y >= 310 && y <= 310 + 65 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo1);
				enemigo1.setVitalidadEnemigo(enemigo1.getVitalidadEnemigo() - ataque);
				if(enemigo1.getVitalidadEnemigo() <= 0) 
				{
					enemigo1.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 15);
					log = enemigo1.getNombreEnemigo() + " ha muerto. Ganas " + 10 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo1.getNombreEnemigo();
				}
				fase2.actualizarLog(log);
				fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			} 
			else if (x >= 550 && x <= 550 + 102 && y >= 340 && y <= 340 + 104 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo2);
				enemigo2.setVitalidadEnemigo(enemigo2.getVitalidadEnemigo() - ataque);
				if(enemigo2.getVitalidadEnemigo() <= 0) 
				{
					enemigo2.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 20);
					log = enemigo2.getNombreEnemigo() + " ha muerto. Ganas " + 10 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo2.getNombreEnemigo();
				}
				fase2.actualizarLog(log);
				fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			if (x >= 180 && x <= 180 + 61 && y >= 360 && y <= 360 + 75 && jugador.getVitalidad() > 0)
			{
				jugador.setDefensa(jugador.getDefensa() + 10);
				defiende = true;
				fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			
			turno = false;
			fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			
			modelo.turnoEnemigosFase2(jugador, armadura, enemigo1, enemigo2, defiende, fase2,

					//Actualizar fase.
					new Runnable()
					{
						@Override
						public void run()
						{
							fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
						}
					},

					//Devolver turno.
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								Thread.sleep(2000);
								turno = true;
								defiende = false;
								resultadoPartida = comprobarPartida();
								fase2.actualizarFase2(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
							} catch (InterruptedException e){}
						}
					});
		}
	}
	
	public void ganarFase2() 
	{
		fase = 3;
		resultadoPartida = 0;
		turno = true;
		jugador.setVitalidad(jugador.getVitalidad() + 5);
		jugador.setNivel(jugador.getNivel() + 1);
		jugador.setAtaque(jugador.getAtaque() + 1);
		jugador.setDefensa(jugador.getDefensa() + 1);
				
		tienda2 = new JuegoTienda2(this, jugador, arma, armadura);
		panelCambio.add(tienda2.panelFondo, "Tienda2");
		layout.show(panelCambio, "Tienda2");
	}
	
	//FASE 3.
	public void clickFase3(int x, int y) 
	{
		if(turno) 
		{
			if (x >= 446 && x <= 446 + 40 && y >= 340 && y <= 340 + 60 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo1);
				enemigo1.setVitalidadEnemigo(enemigo1.getVitalidadEnemigo() - ataque);
				if(enemigo1.getVitalidadEnemigo() <= 0) 
				{
					enemigo1.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 15);
					log = enemigo1.getNombreEnemigo() + " ha muerto. Ganas " + 15 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo1.getNombreEnemigo();
				}
				fase3.actualizarLog(log);
				fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			} 
			else if (x >= 566 && x <= 566 + 46 && y >= 380 && y <= 380 + 67 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo2);
				enemigo2.setVitalidadEnemigo(enemigo2.getVitalidadEnemigo() - ataque);
				if(enemigo2.getVitalidadEnemigo() <= 0) 
				{
					enemigo2.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 20);
					log = enemigo2.getNombreEnemigo() + " ha muerto. Ganas " + 20 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo2.getNombreEnemigo();
				}
				fase3.actualizarLog(log);
				fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			if (x >= 180 && x <= 180 + 61 && y >= 360 && y <= 360 + 75 && jugador.getVitalidad() > 0)
			{
				jugador.setDefensa(jugador.getDefensa() + 10);
				defiende = true;
				fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			
			turno = false;
			fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			
			modelo.turnoEnemigosFase3(jugador, armadura, enemigo1, enemigo2, defiende, fase3,

					//Actualizar fase.
					new Runnable()
					{
						@Override
						public void run()
						{
							fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
						}
					},

					//Devolver turno.
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								Thread.sleep(2000);
								turno = true;
								defiende = false;
								resultadoPartida = comprobarPartida();
								fase3.actualizarFase3(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
							} catch (InterruptedException e){}
						}
					});
		}
	}
	
	public void ganarFase3() 
	{
		fase = 4;
		resultadoPartida = 0;
		turno = true;
		jugador.setVitalidad(jugador.getVitalidad() + 5);
		jugador.setNivel(jugador.getNivel() + 1);
		jugador.setAtaque(jugador.getAtaque() + 1);
		jugador.setDefensa(jugador.getDefensa() + 1);
		
		enemigo1 = enemigos.get(7);
		enemigo2 = enemigos.get(6);
				
		fase4 = new JuegoFase4(this, jugador, armadura, arma);
		panelCambio.add(fase4.panelFondo, "Fase4");
		fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
		layout.show(panelCambio, "Fase4");
	}
	
	//FASE 4
	public void clickFase4(int x, int y) 
	{
		if(turno) 
		{
			if (x >= 446 && x <= 446 + 47 && y >= 330 && y <= 330 + 57 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo1);
				enemigo1.setVitalidadEnemigo(enemigo1.getVitalidadEnemigo() - ataque);
				if(enemigo1.getVitalidadEnemigo() <= 0) 
				{
					enemigo1.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 150);
					log = enemigo1.getNombreEnemigo() + " ha muerto. Ganas " + 150 + " monedas de oro.";
					resultadoPartida = comprobarPartidaFase4();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo1.getNombreEnemigo();
				}
				fase4.actualizarLog(log);
				fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			} 
			else if (x >= 566 && x <= 566 + 94 && y >= 330 && y <= 330 + 114 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo2);
				enemigo2.setVitalidadEnemigo(enemigo2.getVitalidadEnemigo() - ataque);
				if(enemigo2.getVitalidadEnemigo() <= 0) 
				{
					enemigo2.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 50);
					log = enemigo2.getNombreEnemigo() + " ha muerto. Ganas " + 50 + " monedas de oro.";
					resultadoPartida = comprobarPartidaFase4();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo2.getNombreEnemigo();
				}
				fase4.actualizarLog(log);
				fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			if (x >= 180 && x <= 180 + 61 && y >= 360 && y <= 360 + 75 && jugador.getVitalidad() > 0)
			{
				jugador.setDefensa(jugador.getDefensa() + 10);
				defiende = true;
				fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			
			turno = false;
			fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			
			if(enemigo2.getVitalidadEnemigo() > 0) 
			{
				modelo.turnoEnemigosFase4(jugador, armadura, enemigo1, enemigo2, defiende, fase4,

					//Actualizar fase.
					new Runnable()
					{
						@Override
						public void run()
						{
							fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
						}
					},

					//Devolver turno.
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								Thread.sleep(2000);
								turno = true;
								defiende = false;
								resultadoPartida = comprobarPartidaFase4();
								fase4.actualizarFase4(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
							} catch (InterruptedException e){}
						}
					});
			}
			
		}
	}
	
	public int comprobarPartidaFase4()
	{
		int resultado = 0;
		int vitalidad = jugador.getVitalidad();
		int vitalidadEnemigo = enemigo1.getVitalidadEnemigo();
		int vitalidadEnemigo2 = enemigo2.getVitalidadEnemigo();
		
		if(vitalidadEnemigo <= 0 && vitalidadEnemigo2 <= 0) 
		{
			resultado = 3;
		}
		else if(vitalidad <= 0) 
		{
			resultado = 2;
		}
		else if(vitalidadEnemigo > 0 && vitalidadEnemigo2 <= 0) 
		{
			resultado = 1;
		}
		else 
		{
			resultado = 0;
		}
		return resultado;
	}
	
	public void ganarFase4() 
	{
		boolean secreto = false;
		turno = true;
		jugador.setVitalidad(jugador.getVitalidad() + 5);
		jugador.setNivel(jugador.getNivel() + 1);
		jugador.setAtaque(jugador.getAtaque() + 1);
		jugador.setDefensa(jugador.getDefensa() + 1);
		
		enemigo1 = enemigos.get(8);
		enemigo2 = enemigos.get(9);
		
		switch(resultadoPartida) 
		{
			case 1:
				fase = 5;
				resultadoPartida = 0;
				
				juegoFinal = new JuegoFinal(this, jugador, secreto);
				panelCambio.add(juegoFinal.panelFondo, "Final");
				layout.show(panelCambio, "Final");
				break;
				
			case 3:
				fase = 6;
				resultadoPartida = 0;
				
				fase5 = new JuegoFase5(this, jugador, armadura, arma);
				panelCambio.add(fase5.panelFondo, "Fase5");
				fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
				layout.show(panelCambio, "Fase5");
		}
		
		
	}
	
	//FASE 5.
	public void clickFase5(int x, int y) 
	{
		if(turno) 
		{
			if (x >= 550 && x <= 550 + 54 && y >= 385 && y <= 385 + 54 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo1);
				enemigo1.setVitalidadEnemigo(enemigo1.getVitalidadEnemigo() - ataque);
				if(enemigo1.getVitalidadEnemigo() <= 0) 
				{
					enemigo1.setVitalidadEnemigo(0);
					enemigo2.setDefensaEnemigo(0);
					log = enemigo1.getNombreEnemigo() + " ha muerto.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo1.getNombreEnemigo();
				}
				fase5.actualizarLog(log);
				fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			} 
			else if (x >= 430 && x <= 430 + 74 && y >= 330 && y <= 330 + 80 && jugador.getVitalidad() > 0)
			{
				int ataque = modelo.ataqueJugador(jugador, arma, enemigo2);
				enemigo2.setVitalidadEnemigo(enemigo2.getVitalidadEnemigo() - ataque);
				if(enemigo2.getVitalidadEnemigo() <= 0) 
				{
					enemigo2.setVitalidadEnemigo(0);
					jugador.setOro(jugador.getOro() + 50);
					log = enemigo2.getNombreEnemigo() + " ha muerto. Ganas " + 50 + " monedas de oro.";
					resultadoPartida = comprobarPartida();
				}
				else 
				{
					log = jugador.getNombre() + " inflige " + ataque + " puntos de daño a " + enemigo2.getNombreEnemigo();
				}
				fase5.actualizarLog(log);
				fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			if (x >= 180 && x <= 180 + 61 && y >= 360 && y <= 360 + 75 && jugador.getVitalidad() > 0)
			{
				jugador.setDefensa(jugador.getDefensa() + 10);
				defiende = true;
				fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			}
			
			turno = false;
			fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
			
			modelo.turnoEnemigosFase5(jugador, armadura, enemigo1, enemigo2, defiende, fase5,

					//Actualizar fase.
					new Runnable()
					{
						@Override
						public void run()
						{
							fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
						}
					},

					//Devolver turno.
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								Thread.sleep(2000);
								turno = true;
								defiende = false;
								resultadoPartida = comprobarPartida();
								fase5.actualizarFase5(jugador, enemigo1, enemigo2, arma, armadura, resultadoPartida, defiende, turno);
							} catch (InterruptedException e){}
						}
					});
		}
	}
	
	public void ganarFase5() 
	{
		boolean secreto = true;
		jugador.setVitalidad(jugador.getVitalidad() + 5);
		jugador.setNivel(jugador.getNivel() + 1);
		jugador.setAtaque(jugador.getAtaque() + 1);
		jugador.setDefensa(jugador.getDefensa() + 1);
		
		fase = 7;
				
		juegoFinal = new JuegoFinal(this, jugador, secreto);
		panelCambio.add(juegoFinal.panelFondo, "Final");
		layout.show(panelCambio, "Final");
	}

	//Métodos comunes.
	public int comprobarPartida()
	{
		int resultado = 0;
		int vitalidad = jugador.getVitalidad();
		int vitalidadEnemigo = enemigo1.getVitalidadEnemigo();
		int vitalidadEnemigo2 = enemigo2.getVitalidadEnemigo();
		
		if(vitalidadEnemigo <= 0 && vitalidadEnemigo2 <= 0) 
		{
			resultado = 1;
		}
		else if(vitalidad <= 0) 
		{
			resultado = 2;
		}
		else 
		{
			resultado = 0;
		}
		return resultado;
	}
	
	public void perder() 
	{
		LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = (hoy.format(formato));
		HistorialPartidas partida = new HistorialPartidas(0, jugador.getNombre(), jugador.getNivel(), fase, fecha, comprasEquipo, comprasVida);
		modelo.guardarPartida(partida);
		
		resultadoPartida = 0;
		jugador.setVitalidad(12 + jugador.getNivel());
		jugador.setOro(jugador.getOro() + 50);
		new Juego(vPrincipal, jugador);
		vJuego.dispose();
	}
	
	public void clickFinal() 
	{
		LocalDate hoy = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fecha = (hoy.format(formato));
		HistorialPartidas partida = new HistorialPartidas(0, jugador.getNombre(), jugador.getNivel(), fase, fecha, comprasEquipo, comprasVida);
		modelo.guardarPartida(partida);
		
		resultadoPartida = 0;
		jugador.setVitalidad(12 + jugador.getNivel());
		jugador.setOro(jugador.getOro() + 50);
		new Juego(vPrincipal, jugador);
		vJuego.dispose();
	}
	
}
