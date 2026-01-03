package es.studium;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JuegoTienda2
{
	Modelo modelo = new Modelo();
	String sndComprar = "sound/comprar.mp3";
	String sndSeleccion = "sound/seleccion.mp3";
	Sonido sound;
	
	Jugador jugador;
	Arma arma;
	Arma armaPlataTienda;
	Arma armaOroTienda;
	Armadura armadura;
	Armadura armaduraPlataTienda;
	Armadura armaduraOroTienda;
	
	List<Arma> armas = modelo.datosArmas();
	List<Armadura> armaduras = modelo.datosArmaduras();
	
	Image imgFondo = new ImageIcon(getClass().getResource("img/tiendaCastillo.png")).getImage();
	Image imgFase = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
	Image imgHUD = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
	Image imgDialogo = new ImageIcon(getClass().getResource("img/dialogoTienda2.png")).getImage();
	Image imgDialogo2 = new ImageIcon(getClass().getResource("img/dialogoTienda2B.png")).getImage();
	Image imgDialogoVisto;
	
	Image imgEspadaPlata = new ImageIcon(getClass().getResource("img/espadaPlata.png")).getImage();
	Image imgEspadaOro = new ImageIcon(getClass().getResource("img/espadaOro.png")).getImage();
	Image imgEspadaActual;
	
	Image imgArmaduraPlata = new ImageIcon(getClass().getResource("img/ArmaduraPlata.png")).getImage();
	Image imgArmaduraOro = new ImageIcon(getClass().getResource("img/ArmaduraOro.png")).getImage();
	Image imgArmaduraActual;
	
	Image imgCorazon = new ImageIcon(getClass().getResource("img/heart.png")).getImage();
	Image imgFlecha = new ImageIcon(getClass().getResource("img/flecha.png")).getImage();
	Image imgFlecha2 = new ImageIcon(getClass().getResource("img/flecha2.png")).getImage();
	Image imgFlechaActual = imgFlecha;
	
	JPanel panelFondo;
	JLabel lblInfo = new JLabel("Información:");
	JLabel lblInfo2 = new JLabel("");
	JLabel lblPrecio = new JLabel("");
	boolean compraArma = false;
	boolean compraArmadura = false;
	int precioVida = 15;
	int oroEquipo = 0;
	int oroVida = 0;
	int mostrar = 0;
	
	public JuegoTienda2(Juego juego, Jugador j, Arma a, Armadura arm) 
	{
		jugador = j;
		arma = a;
		armadura = arm;
		armaPlataTienda = armas.get(2);
		armaOroTienda = armas.get(3);
		armaduraPlataTienda = armaduras.get(2);
		armaduraOroTienda = armaduras.get(3);
		
		imgEspadaActual = modelo.actualizarHUDArma(arma);
		imgArmaduraActual = modelo.actualizarHUDArmadura(armadura);
		
		panelFondo = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imgFondo, 0, 50, 900, 428, null);
                g.drawImage(imgFase, 345, 0, 207, 50, null);
                g.drawImage(imgHUD, 0, 478, 885, 184, null);
                g.drawImage(imgDialogoVisto, 420, 86, 151, 81, null);
                
                g.drawImage(imgEspadaActual, 339, 541, 21+20, 21+20, null);
                g.drawImage(imgEspadaPlata, 284, 400, 21+20, 21+20, null);
                g.drawImage(imgEspadaOro, 284, 300, 21+20, 21+20, null);
                
                g.drawImage(imgArmaduraActual, 410, 541, 21+20, 21+20, null);
                g.drawImage(imgArmaduraPlata, 495, 400, 21+20, 21+20, null);
                g.drawImage(imgArmaduraOro, 495, 300, 21+20, 21+20, null);
                
                g.drawImage(imgCorazon, 381, 200, 21+20, 21+20, null);
                g.drawImage(imgFlechaActual, 104, 280, 21+20, 21+20, null);
                
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawRoundRect(339, 541, 21+20, 21+20, 3, 3);
                g.drawRoundRect(410, 541, 21+20, 21+20, 3, 3);
                g.drawRoundRect(284, 400, 21+20, 21+20, 3, 3);
                g.drawRoundRect(284, 300, 21+20, 21+20, 3, 3);
                g.drawRoundRect(495, 400, 21+20, 21+20, 3, 3);
                g.drawRoundRect(495, 300, 21+20, 21+20, 3, 3);
                g.drawRoundRect(381, 200, 21+20, 21+20, 3, 3);
                
                g.drawString("Oro: " + jugador.getOro(), 50, 515);
                g.drawString("Vitalidad Actual: " + jugador.getVitalidad(), 50, 540);
                g.drawString("Equipo Actual:", 340, 515);
                g.setColor(Color.yellow);
                g.drawString("Tienda del castillo", 380, 25);
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
		        //Espada Plata.
		        else if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 400 && mouseY <= 400 + 41 && imgEspadaPlata != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaPlataTienda.getNombreArma() + "(ATQ " + armaPlataTienda.getMinArma() + "-" + armaPlataTienda.getMaxArma() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaPlataTienda.getPrecioArma());
		            lblPrecio.setVisible(true);
			    }
		        //Espada Oro.
		        else if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 300 && mouseY <= 300 + 41 && imgEspadaOro != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaOroTienda.getNombreArma() + "(ATQ " + armaOroTienda.getMinArma() + "-" + armaOroTienda.getMaxArma() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaOroTienda.getPrecioArma());
		            lblPrecio.setVisible(true);
			    }
		        //Armadura Plata.
		        else if (mouseX >= 495 && mouseX <= 495 + 41 && mouseY >= 400 && mouseY <= 400 + 41 && imgArmaduraPlata != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaduraPlataTienda.getNombreArmadura() + "(DEF " + armaduraPlataTienda.getDefensaArmadura() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaduraPlataTienda.getPrecioArmadura());
		            lblPrecio.setVisible(true);
			    }
		        //Armadura Oro.
		        else if (mouseX >= 495 && mouseX <= 495 + 41 && mouseY >= 300 && mouseY <= 300 + 41 && imgArmaduraOro != null) 
			    {
		        	panelFondo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	lblInfo.setVisible(true);
		        	lblInfo2.setText(armaduraOroTienda.getNombreArmadura() + "(DEF " + armaduraOroTienda.getDefensaArmadura() + ")");
		            lblInfo2.setVisible(true);
		            lblPrecio.setText("Precio: " + armaduraOroTienda.getPrecioArmadura());
		            lblPrecio.setVisible(true);
			    }
		        //Vitalidad.
		        else if (mouseX >= 381 && mouseX <= 381 + 41 && mouseY >= 200 && mouseY <= 200 + 41) 
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
		        	lblInfo2.setText("Continuar al castillo");
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
		        
		        mostrar++;
		        imgDialogoVisto = imgDialogo;
		        if(mostrar == 2) 
		        {
		        	imgDialogoVisto = imgDialogo2;
		        	imgDialogo = imgDialogo2;
		        }
            	panelFondo.repaint();
            	
            	//Comprar Espada Plata.
            	if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 400 && mouseY <= 400 + 41 && jugador.getOro() >= armaPlataTienda.getPrecioArma() && imgEspadaPlata != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaPlataTienda.getPrecioArma());
					arma = comprarArma(armaPlataTienda, imgEspadaPlata);
					oroEquipo = oroEquipo + armaPlataTienda.getPrecioArma();
			    }
            	//Comprar Espada Oro.
            	if (mouseX >= 284 && mouseX <= 284 + 41 && mouseY >= 300 && mouseY <= 300 + 41 && jugador.getOro() >= armaPlataTienda.getPrecioArma() && imgEspadaOro != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaOroTienda.getPrecioArma());
					arma = comprarArma(armaOroTienda, imgEspadaOro);
					oroEquipo = oroEquipo + armaOroTienda.getPrecioArma();
			    }
            	//Comprar Armadura Plata.
            	else if (mouseX >= 495 && mouseX <= 495 + 41 && mouseY >= 400 && mouseY <= 400 + 41 && jugador.getOro() >= armaduraPlataTienda.getPrecioArmadura() && imgArmaduraPlata != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaduraPlataTienda.getPrecioArmadura());
					armadura = comprarArmadura(armaduraPlataTienda, imgArmaduraPlata);
					oroEquipo = oroEquipo + armaduraPlataTienda.getPrecioArmadura();
			    }
            	//Comprar Armadura Oro.
            	else if (mouseX >= 495 && mouseX <= 495 + 41 && mouseY >= 300 && mouseY <= 300 + 41 && jugador.getOro() >= armaduraOroTienda.getPrecioArmadura() && imgArmaduraOro != null) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
            		jugador.setOro(jugador.getOro() - armaduraOroTienda.getPrecioArmadura());
					armadura = comprarArmadura(armaduraOroTienda, imgArmaduraOro);
					oroEquipo = oroEquipo + armaduraOroTienda.getPrecioArmadura();
			    }
            	//Comprar Vitalidad.
            	else if (mouseX >= 381 && mouseX <= 381 + 41 && mouseY >= 200 && mouseY <= 200 + 41 && jugador.getOro() >= precioVida) 
			    {
            		sound = new Sonido(sndComprar);
		    		sound.start();
					comprarVida();
					oroVida = oroVida + precioVida;
			    }
            	//Flecha.
            	else if (mouseX >= 104 && mouseX <= 104 + 41 && mouseY >= 280 && mouseY <= 280 + 41) 
			    {
            		sound = new Sonido(sndSeleccion);
		    		sound.start();
		        	juego.clickTienda2(jugador, arma, armadura, oroEquipo, oroVida);
			    }
            	
            	else 
        		{
        			lblInfo2.setText("No tienes suficiente Oro");
        		}
        	}

        });
	}

	private Arma comprarArma(Arma armaTienda, Image imgArma)
	{
		arma = armaTienda;
		imgEspadaActual = imgArma;
		
		if(imgArma.equals(imgEspadaPlata)) 
		{
			imgEspadaPlata = null;
		}
		else if(imgArma.equals(imgEspadaOro)) 
		{
			imgEspadaOro = null;
		}

		panelFondo.repaint();
		
		return arma;
	}
	
	private Armadura comprarArmadura(Armadura armaduraTienda, Image imgArmadura)
	{
		armadura = armaduraTienda;
		imgArmaduraActual = imgArmadura;
		
		if(imgArmadura.equals(imgArmaduraPlata)) 
		{
			imgArmaduraPlata = null;
		}
		else if(imgArmadura.equals(imgArmaduraOro)) 
		{
			imgArmaduraOro = null;
		}

		panelFondo.repaint();
		
		return armadura;
	}
	
	private void comprarVida()
	{
		jugador.setVitalidad(jugador.getVitalidad() + 1);
		jugador.setOro(jugador.getOro() - precioVida);

		panelFondo.repaint();
	}
	
}
