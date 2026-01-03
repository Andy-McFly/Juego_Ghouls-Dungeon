package es.studium;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JuegoFase5
{
	Modelo modelo = new Modelo();
	JuegoHUD hud;
	Juego juego;
	
	String sndAtacar = "sound/atacar.mp3";
	String sndDefensa = "sound/defensa.mp3";
	String sndMuerteEnemigo = "sound/muerteEnemigo.mp3";
	String sndGanar = "sound/ganar.mp3";
	String sndPerder = "sound/perder.mp3";
	Sonido sound;
	Sonido soundPartida;
	
	JPanel panelFondo;
	JLabel lblInfo = new JLabel("");
	JTextArea txaLog = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(txaLog);
	
	Image imagenFondo = new ImageIcon(getClass().getResource("img/secreto.png")).getImage();
	Image imgTurnoJugador = new ImageIcon(getClass().getResource("img/turnoJugador.png")).getImage();
	Image imgTurnoEnemigo = new ImageIcon(getClass().getResource("img/turnoEnemigo.png")).getImage();
	Image imgTurnoActual = imgTurnoJugador;
	Image imgJugador;
	Image imgJugadorAtaca;
	Image imgJugadorHerido;
	Image imgJugadorConcentrar;
	Image imgJugadorGanar;
	Image imgJugadorMuerte = new ImageIcon(getClass().getResource("img/jugadorMuerte.png")).getImage();
	Image imgJugadorActual;
	
	Image imgDefenderNo = new ImageIcon(getClass().getResource("img/defenderNo.png")).getImage();
	Image imgDefender = new ImageIcon(getClass().getResource("img/defender.png")).getImage();
	Image imgDefenderActual = imgDefenderNo;
	
	Image imgEnemigo1 = new ImageIcon(getClass().getResource("img/craneo.png")).getImage();
	Image imgEnemigo1Ataca = new ImageIcon(getClass().getResource("img/craneoAtaque.png")).getImage();
	Image imgEnemigo1Herido = new ImageIcon(getClass().getResource("img/craneoHerido.png")).getImage();
	Image imgEnemigo1Actual = imgEnemigo1;
	
	Image imgEnemigo2 = new ImageIcon(getClass().getResource("img/trueBoss.png")).getImage();
	Image imgEnemigo2Ataca = new ImageIcon(getClass().getResource("img/trueBossAtaque.png")).getImage();
	Image imgEnemigo2Herido = new ImageIcon(getClass().getResource("img/trueBossHerido.png")).getImage();
	Image imgEnemigo2Actual = imgEnemigo2;
	
	Image imgMuerteEnemigo = new ImageIcon(getClass().getResource("img/muerteEnemigo.png")).getImage();
	Image imgVida = new ImageIcon(getClass().getResource("img/heart.png")).getImage();
	Image imgPerder = new ImageIcon(getClass().getResource("img/muerte.png")).getImage();
	Image imgAvisoPerder = new ImageIcon(getClass().getResource("img/perder.png")).getImage();
	Image imgAvisoGanar = new ImageIcon(getClass().getResource("img/ganar.png")).getImage();
	Image imgPortal = new ImageIcon(getClass().getResource("img/portal.png")).getImage();
	
	int vidaJugador = 0;
	int vidaEnemigo1 = 0;
	int vidaEnemigo2 = 0;
	int partida = 0;
	boolean turno;
	
	public JuegoFase5(Juego juegoEnv, Jugador jugador, Armadura armadura, Arma arma) 
	{
		Image[] imagenesJugador = modelo.actualizarImgJugador(armadura);
		imgJugador = imagenesJugador[0];
		imgJugadorAtaca = imagenesJugador[1];
		imgJugadorHerido = imagenesJugador[2];
		imgJugadorConcentrar = imagenesJugador[3];
		imgJugadorGanar = imagenesJugador[4];
		imgJugadorActual = imgJugador;
		
		juego = juegoEnv;
		hud = new JuegoHUD(jugador, arma, armadura);
		
		panelFondo = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 50, 900, 428, null);
                hud.dibujar(g);
                g.setColor(Color.white);
                g.setFont(new Font("Calibri", Font.BOLD, 18));
                g.drawString("Fase 5: ????", 395, 30);
                g.drawImage(imgTurnoActual, 345, 60, 207, 50, null);
                g.setColor(Color.black);
                g.drawRect(345, 60, 207, 50);
                
                if(partida == 1) 
                {
                	imgJugadorActual = imgJugadorGanar;
                    g.drawImage(imgAvisoGanar, 0, 50, getWidth(), 227, null);
                    g.setColor(Color.white);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString("El mal ha caído!", 340, 255);
                    g.drawString("Click aquí para continuar...", 550, 525);
                }
                
                g.drawImage(imgJugadorActual, 180, 360, 21+40, 35+40, null);
                g.drawImage(imgDefenderActual, 220, 360, 14+40, 20+40, null);
                g.drawImage(imgEnemigo1Actual, 550, 385, 54, 54, null);
                g.drawImage(imgEnemigo2Actual, 430, 330, 74, 80, null);
                g.drawImage(imgVida, 195, 310, 20+20, 20+20, null);
                g.drawImage(imgVida, 555, 330, 20+20, 20+20, null);
                g.drawImage(imgVida, 435, 275, 20+20, 20+20, null);
                g.setColor(Color.white);
                g.setFont(new Font("Calibri", Font.BOLD, 18));
                g.drawString(jugador.getVitalidad()+"", 207, 336);
                g.drawString(vidaEnemigo1+"", 567, 356);
                g.drawString(vidaEnemigo2+"", 447, 301);
                
                if(partida == 2) 
                {
                	g.setColor(new Color(0, 0, 0, 210));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.drawImage(imgAvisoPerder, 0, 50, getWidth(), 227, null);
                    g.drawImage(imgPerder, getWidth()/2 - 35, 350, 42+30, 15+30, null);
                    g.setColor(Color.white);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString("Click aquí para continuar...", 550, 525);
                }
            }
        };
        
        lblInfo.setBounds(550, 505, 160, 30);
        lblInfo.setForeground(Color.white);
        panelFondo.add(lblInfo);
        scrollPane.setBounds(550, 550, 300, 100);
        txaLog.setFocusable(false);
        txaLog.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panelFondo.add(scrollPane);
		panelFondo.setLayout(null);
		panelFondo.setBackground(Color.black);
		
		panelFondo.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        int mouseX = e.getX();
		        int mouseY = e.getY();

		        if (mouseX >= 180 && mouseX <= 180 + 61 && mouseY >= 360 && mouseY <= 360 + 75 && vidaJugador > 0) 
		        {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		            lblInfo.setText("Defender");
		        }
		        else if (mouseX >= 550 && mouseX <= 550 + 54 && mouseY >= 385 && mouseY <= 385 + 54 && vidaEnemigo1 > 0 && vidaJugador > 0) 
		        {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		            lblInfo.setText("Atacar a Cráneo");
		        } 
		        else if (mouseX >= 430 && mouseX <= 430 + 74 && mouseY >= 330 && mouseY <= 330 + 80 && vidaEnemigo2 > 0 && vidaJugador > 0) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			        lblInfo.setText("Atacar a Maldad");
			    }
		        else 
		        {
		        	panelFondo.setCursor(Cursor.getDefaultCursor());
		            lblInfo.setText("");
		        }
		    }
		});
		
		panelFondo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	System.out.println(e.getX() + " - " + e.getY());
            	int mouseX = e.getX();
		        int mouseY = e.getY();
		        
		        if (mouseX >= 0 && mouseX <= 0 + 885 && mouseY >= 478 && mouseY <= 478 + 184) 
		        {
					switch (partida)
					{
						case 1:
							soundPartida.pararMusica();
							juego.ganarFase5();
							break;
						case 2:
							soundPartida.pararMusica();
							juego.perder();
							break;
					}
		        }
            	
            	
            	if (mouseX >= 550 && mouseX <= 550 + 54 && mouseY >= 385 && mouseY <= 385 + 54 && vidaEnemigo1 > 0 && vidaJugador > 0) 
        		{
            		if(turno) 
            		{
            			juego.clickFase5(e.getX(), e.getY());
            			imgJugadorActual = imgJugadorAtaca;
            			imgEnemigo1Actual = imgEnemigo1Herido;
            			panelFondo.repaint();
            			sound = new Sonido(sndAtacar);
        				sound.start();
        			
            			Thread hilo = new Thread(new Runnable()
            			{
            				@Override
            				public void run()
            				{
            					try
            					{
            						Thread.sleep(600);
            					}
            					catch (Exception e){}

            					imgJugadorActual = imgJugador;
            					imgEnemigo1Actual = imgEnemigo1;
            					imgEnemigo1Actual = modelo.comprobarMuerte(vidaEnemigo1, imgEnemigo1Actual, imgMuerteEnemigo);
            					panelFondo.repaint();
            					if(imgEnemigo1Actual == imgMuerteEnemigo) 
            					{
            						sound = new Sonido(sndMuerteEnemigo);
                    				sound.start();
            					}
            				}
            			});
            			hilo.start();
            		}
        			
        		}
            	
            	else if (mouseX >= 430 && mouseX <= 430 + 74 && mouseY >= 330 && mouseY <= 330 + 80 && vidaEnemigo2 > 0 && vidaJugador > 0) 
        		{
            		if(turno) 
            		{
            			juego.clickFase5(e.getX(), e.getY());
						imgJugadorActual = imgJugadorAtaca;
						imgEnemigo2Actual = imgEnemigo2Herido;
						panelFondo.repaint();
						sound = new Sonido(sndAtacar);
        				sound.start();

						Thread hilo = new Thread(new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									Thread.sleep(600);
								} 
								catch (Exception e){}

								imgJugadorActual = imgJugador;
								imgEnemigo2Actual = imgEnemigo2;
								imgEnemigo2Actual = modelo.comprobarMuerte(vidaEnemigo2, imgEnemigo2Actual, imgPortal);
								panelFondo.repaint();
								if(imgEnemigo2Actual == imgMuerteEnemigo) 
            					{
            						sound = new Sonido(sndMuerteEnemigo);
                    				sound.start();
            					}
							}
						});
						hilo.start();
            		}
        			
        		}
            	
            	else if (mouseX >= 180 && mouseX <= 180 + 61 && mouseY >= 360 && mouseY <= 360 + 75 && vidaJugador > 0) 
        		{
            		if(turno)
            		{
            			juego.clickFase5(e.getX(), e.getY());
            			imgDefenderActual = imgDefender;
            			panelFondo.repaint();
            			sound = new Sonido(sndDefensa);
        				sound.start();
            		}
        		}
            }
        });
	}
	
	public void actualizarFase5(Jugador jugador, Enemigo enemigo1, Enemigo enemigo2, Arma arma, Armadura armadura, 
			int partida, boolean defiende, boolean turno) 
	{
		vidaEnemigo1 = enemigo1.getVitalidadEnemigo();
		vidaEnemigo2 = enemigo2.getVitalidadEnemigo();
		vidaJugador = jugador.getVitalidad();
		this.turno = turno;
		this.partida = partida;
		
		if(turno) 
		{
			imgDefenderActual = imgDefenderNo;
			imgTurnoActual = imgTurnoJugador;
			switch(partida) 
        	{
        		case 1:
        			soundPartida = new Sonido(sndGanar);
        			soundPartida.start();
        			break;
        		case 2:
        			soundPartida = new Sonido(sndPerder);
        			soundPartida.start();
        			break;
        	}
		}
		else 
		{
			imgTurnoActual = imgTurnoEnemigo;
		}
		
		panelFondo.repaint();
	}
	
	public void ataqueEnemigo1()
    {
		imgEnemigo1Actual = imgEnemigo1Ataca;
		imgJugadorActual = imgJugadorHerido;
		sound = new Sonido(sndAtacar);
		sound.start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try { 
                	Thread.sleep(700); 
                	} catch (Exception e) {}
                imgEnemigo1Actual = imgEnemigo1;
                imgJugadorActual = imgJugador;
                imgJugadorActual = modelo.comprobarMuerte(vidaJugador, imgJugadorActual, imgJugadorMuerte);
                panelFondo.repaint();
            }
        }).start();
    }
	
	public void ataqueEnemigo2()
    {
		imgEnemigo2Actual = imgEnemigo2Ataca;
		imgJugadorActual = imgJugadorHerido;
		sound = new Sonido(sndAtacar);
		sound.start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try { 
                	Thread.sleep(600); 
                	} catch (Exception e) {}
                imgEnemigo2Actual = imgEnemigo2;
                imgJugadorActual = imgJugador;
                imgJugadorActual = modelo.comprobarMuerte(vidaJugador, imgJugadorActual, imgJugadorMuerte);
                panelFondo.repaint();
            }
        }).start();
    }
	
	public void actualizarLog(String log)
	{
		txaLog.append(log + "\n");
	}

}
