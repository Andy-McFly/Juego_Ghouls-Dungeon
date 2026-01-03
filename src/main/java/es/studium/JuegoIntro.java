package es.studium;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JuegoIntro
{
	String sndIntro = "sound/intro.mp3";
	Sonido sound;
	Image imagenFondo = new ImageIcon(getClass().getResource("img/aldea.png")).getImage();
	JPanel panelFondo;
	JLabel lblInfo = new JLabel("Prueba");
	JLabel lblInfo2 = new JLabel("Prueba2");
	JLabel lblInfo3 = new JLabel("Prueba3");
	int mostrar = 0;
	
	public JuegoIntro(Juego juego, String nombre) 
	{
		sound = new Sonido(sndIntro);
		sound.start();
		
		panelFondo = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 50, 900, 428, null);
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setBackground(Color.black);
        lblInfo.setBounds(170, 510, 500, 30);
        lblInfo2.setBounds(80, 510, 800, 30);
        lblInfo3.setBounds(300, 510, 500, 30);
        lblInfo.setForeground(Color.white);
        lblInfo2.setForeground(Color.white);
        lblInfo3.setForeground(Color.white);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo2.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo3.setFont(new Font("Arial", Font.BOLD, 14));
        lblInfo.setText("En una aldea remota, un mal acecha...");
        lblInfo2.setText("...y un intrépido héroe llamado " + nombre + " se puso en marcha para vencerlo.");
        lblInfo3.setText("Uno de tantos...");
        lblInfo.setVisible(false);
        lblInfo2.setVisible(false);
        lblInfo3.setVisible(false);
        panelFondo.add(lblInfo);
        panelFondo.add(lblInfo2);
        panelFondo.add(lblInfo3);
        
        panelFondo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	mostrar++;
            	lblInfo.setVisible(true);
            	switch(mostrar) 
            	{
            		case 2:
            			lblInfo.setVisible(false);
            			lblInfo2.setVisible(true);
            			break;
            		case 3:
            			lblInfo.setVisible(false);
            			lblInfo2.setVisible(false);
            			lblInfo3.setVisible(true);
            			break;
            		case 4:
            			sound.pararMusica();
            			juego.clickIntro();
            			break;
            	}
        	}
        });
	}

}
