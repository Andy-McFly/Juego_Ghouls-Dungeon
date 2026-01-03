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

public class JuegoTienda1
{
	String sndComprar = "sound/comprar.mp3";
	String sndSeleccion = "sound/seleccion.mp3";
	Sonido sound;
	Jugador jugador;
	Arma arma;
	Arma armaTienda;
	Armadura armadura;
	Armadura armaduraTienda;
	
	Image imgFondo = new ImageIcon(getClass().getResource("img/tiendaAldea.png")).getImage();
	Image imgFase = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
	Image imgHUD = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
	Image imgDialogo = new ImageIcon(getClass().getResource("img/dialogoTienda1.png")).getImage();
	Image imgDialogoVisto;
	
	Image imgEspadaOxidada = new ImageIcon(getClass().getResource("img/espadaOxidada.png")).getImage();
	Image imgEspadaHierro = new ImageIcon(getClass().getResource("img/espadaHierro.png")).getImage();
	Image imgEspadaActual = imgEspadaOxidada;
	
	Image imgArmaduraInicial = new ImageIcon(getClass().getResource("img/ArmaduraInicial.png")).getImage();
	Image imgArmaduraHierro = new ImageIcon(getClass().getResource("img/ArmaduraHierro.png")).getImage();
	Image imgArmaduraActual = imgArmaduraInicial;
	
	Image imgCorazon = new ImageIcon(getClass().getResource("img/heart.png")).getImage();
	Image imgFlecha = new ImageIcon(getClass().getResource("img/flecha.png")).getImage();
	Image imgFlecha2 = new ImageIcon(getClass().getResource("img/flecha2.png")).getImage();
	Image imgFlechaActual = imgFlecha;
	
	JPanel panelFondo;
	JLabel lblInfo = new JLabel("Información:");
	JLabel lblInfo2 = new JLabel("");
	JLabel lblPrecio = new JLabel("");
	int precioVida = 15;
	int oroEquipo = 0;
	int oroVida = 0;
	
	public JuegoTienda1(Juego juego, Jugador j, Arma a, Armadura arm, Arma armaT, Armadura armaduraT) 
	{
		arma = a;
		armadura = arm;
		armaTienda = armaT;
		armaduraTienda = armaduraT;
		jugador = j;
		
		panelFondo = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imgFondo, 0, 50, 900, 428, null);
                g.drawImage(imgFase, 345, 0, 207, 50, null);
                g.drawImage(imgHUD, 0, 478, 885, 184, null);
                g.drawImage(imgDialogoVisto, 700, 200, 142, 95, null);
                g.drawImage(imgEspadaActual, 339, 541, 21+20, 21+20, null);
                g.drawImage(imgEspadaHierro, 284, 286, 21+20, 21+20, null);
                
                g.drawImage(imgArmaduraActual, 410, 541, 21+20, 21+20, null);
                g.drawImage(imgArmaduraHierro, 539, 290, 21+20, 21+20, null);
                
                g.drawImage(imgCorazon, 415, 244, 21+20, 21+20, null);
                g.drawImage(imgFlechaActual, 104, 280, 21+20, 21+20, null);
                
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawRoundRect(339, 541, 21+20, 21+20, 3, 3);
                g.drawRoundRect(410, 541, 21+20, 21+20, 3, 3);
                g.drawRoundRect(284, 286, 21+20, 21+20, 3, 3);
                g.drawRoundRect(539, 290, 21+20, 21+20, 3, 3);
                g.drawRoundRect(415, 244, 21+20, 21+20, 3, 3);
                
                g.drawString("Oro: " + jugador.getOro(), 50, 515);
                g.drawString("Vitalidad Actual: " + jugador.getVitalidad(), 50, 540);
                g.drawString("Equipo Actual:", 340, 515);
                g.setColor(Color.yellow);
                g.drawString("Tienda de la aldea", 380, 25);
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setBackground(Color.black);
        lblInfo.setBounds(541, 495, 100, 30);
        lblInfo.setForeground(Color.white);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 16));
        lblInfo.setVisible(false);
        lblInfo2.setBounds(541, 525, 200, 30);
        lblInfo2.setForeground(Color.white);
        lblInfo2.setFont(new Font("Arial", Font.BOLD, 14));
        lblInfo2.setVisible(false);
        lblPrecio.setBounds(541, 555, 200, 30);
        lblPrecio.setForeground(Color.white);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setVisible(false);
        panelFondo.add(lblInfo);
        panelFondo.add(lblInfo2);
        panelFondo.add(lblPrecio);
        
        panelFondo.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) 
		    {
		        int mouseX = e.getX();
		        int mouseY = e.getY();

		        //Arma equipada.
		        if (mouseX >= 339 && mouseX <= 339 + 41 && mouseY >= 541 && mouseY <= 541 + 41) 
		        {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		            lblInfo.setVisible(true);
		            lblInfo2.setText(arma.getNombreArma() + "(ATQ " + arma.getMinArma() + "-" + arma.getMaxArma() + ")");
		            lblInfo2.setVisible(true);
		        }
		        //Armadura equipada.
		        else if (mouseX >= 410 && mouseX <= 410 + 41 && mouseY >= 541 && mouseY <= 541 + 41) 
		        {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armadura.getNombreArmadura() + "(DEF " + armadura.getDefensaArmadura() + ")");
		            lblInfo2.setVisible(true);
		        } 
		        //Arma tienda.
		        else if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 286 && mouseY <= 286 + 41 && imgEspadaHierro != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaTienda.getNombreArma() + "(ATQ " + armaTienda.getMinArma() + "-" + armaTienda.getMaxArma() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaTienda.getPrecioArma());
		            lblPrecio.setVisible(true);
			    }
		        //Armadura tienda.
		        else if (mouseX >= 539 && mouseX <= 539 + 41 && mouseY >= 290 && mouseY <= 290 + 41 && imgArmaduraHierro != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaduraTienda.getNombreArmadura() + "(DEF " + armaduraTienda.getDefensaArmadura() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaduraTienda.getPrecioArmadura());
		            lblPrecio.setVisible(true);
			    }
		        //Subir vitalidad.
		        else if (mouseX >= 415 && mouseX <= 415 + 41 && mouseY >= 244 && mouseY <= 244 + 41) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText("Vitalidad +1");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: 15");
		            lblPrecio.setVisible(true);
			    }
		        //Flecha.
		        else if (mouseX >= 104 && mouseX <= 104 + 41 && mouseY >= 280 && mouseY <= 280 + 41) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	imgFlechaActual = imgFlecha2;
		        	panelFondo.repaint();
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText("Camino al bosque");
		            lblInfo2.setVisible(true);
			    }
		        else 
		        {
		        	panelFondo.setCursor(Cursor.getDefaultCursor());
		        	imgFlechaActual = imgFlecha;
		        	panelFondo.repaint();
		        	lblInfo.setVisible(false);
		        	lblInfo2.setVisible(false);
		        	lblPrecio.setVisible(false);
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
		        
            	imgDialogoVisto = imgDialogo;
            	panelFondo.repaint();
            	
            	//Comprar Arma.
            	if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 286 && mouseY <= 286 + 41 && jugador.getOro() >= armaTienda.getPrecioArma() && imgEspadaHierro != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaTienda.getPrecioArma());
					arma = comprarArma();
					oroEquipo = oroEquipo + armaTienda.getPrecioArma();
			    }
            	//Comprar Armadura.
            	else if (mouseX >= 539 && mouseX <= 539 + 41 && mouseY >= 290 && mouseY <= 290 + 41 && jugador.getOro() >= armaduraTienda.getPrecioArmadura() && imgArmaduraHierro != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaduraTienda.getPrecioArmadura());
					armadura = comprarArmadura();
					oroEquipo = oroEquipo + armaduraTienda.getPrecioArmadura();
			    }
            	//Comprar Vitalidad.
            	else if (mouseX >= 415 && mouseX <= 415 + 41 && mouseY >= 244 && mouseY <= 244 + 41 && jugador.getOro() >= precioVida) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - precioVida);
					comprarVida();
					oroVida = oroVida + precioVida;
			    }
            	//Flecha.
            	else if (mouseX >= 104 && mouseX <= 104 + 41 && mouseY >= 280 && mouseY <= 280 + 41) 
			    {
            		sound = new Sonido(sndSeleccion);
		    		sound.start();
		        	juego.clickTienda(jugador, arma, armadura, oroEquipo, oroVida);
			    }
            	
            	else 
        		{
        			lblInfo2.setText("No tienes suficiente Oro");
        		}
        	}

        });
	}
	
	private Arma comprarArma()
	{
		arma = armaTienda;
		imgEspadaActual = imgEspadaHierro;
		imgEspadaHierro = null;

		panelFondo.repaint();
		
		return arma;
	}
	
	private Armadura comprarArmadura()
	{
		armadura = armaduraTienda;
		imgArmaduraActual = imgArmaduraHierro;
		imgArmaduraHierro = null;

		panelFondo.repaint();
		
		return armadura;
	}
	
	private void comprarVida()
	{
		jugador.setVitalidad(jugador.getVitalidad() + 1);

		panelFondo.repaint();
	}
}
