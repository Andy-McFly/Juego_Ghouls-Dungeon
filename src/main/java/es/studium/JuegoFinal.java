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

public class JuegoFinal
{
	String sndFinal;
	Sonido sound;
	
	Image imgEnd = new ImageIcon(getClass().getResource("img/theEnd.png")).getImage();
	Image imgFinalA1 = new ImageIcon(getClass().getResource("img/portalFinal.png")).getImage();
	Image imgFinalA2 = new ImageIcon(getClass().getResource("img/portalFinal2.png")).getImage();
	Image imgFinalB1 = new ImageIcon(getClass().getResource("img/aldeaFinal.png")).getImage();
	Image imgFinalB2 = new ImageIcon(getClass().getResource("img/aldeaFinal2.png")).getImage();
	
	JPanel panelFondo;
	JLabel lblInfo = new JLabel("Prueba");
	JLabel lblInfo2 = new JLabel("Prueba2");
	JLabel lblInfo3 = new JLabel("Prueba3");
	JLabel lblInfo4 = new JLabel("Prueba4");
	JLabel lblInfo5 = new JLabel("Fin.");
	
	int mostrar = 0;

	public JuegoFinal(Juego juego, Jugador jugador, boolean secreto) 
	{
		if(secreto) 
		{
			sndFinal = "sound/finalAldea.mp3";
		}
		else 
		{
			sndFinal = "sound/finalPortal.mp3";
		}
		panelFondo = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imgEnd, getWidth()/2 - 110, 50, 220, 60, null);
                
                if(secreto) 
                {
                	switch(mostrar) 
                	{
                	case 3:
                		g.drawImage(imgFinalB1, getWidth()/2 - 284, 250, 568, 146, null);
                		break;
                	case 4:
                		g.drawImage(imgFinalB1, getWidth()/2 - 284, 250, 568, 146, null);
                		g.drawImage(imgFinalB2, 550, 175, 54, 54, null);
                		break;
                	}
                }
                else
                {
                	switch(mostrar) 
                	{
                	case 3:
                		g.drawImage(imgFinalA1, getWidth()/2 - 156, 250, 314, 326, null);
                		break;
                	case 4:
                		g.drawImage(imgFinalA1, getWidth()/2 - 156, 250, 314, 326, null);
                		g.drawImage(imgFinalA2, getWidth()/2 - 26, 413-27, 63, 63, null);
                		break;
                	}
                }
            }
        };
        
        sound = new Sonido(sndFinal);
        sound.start();
        panelFondo.setLayout(null);
        panelFondo.setBackground(Color.black);
        lblInfo.setBounds(105, 200, 600, 30);
        lblInfo2.setBounds(105, 200, 600, 30);
        lblInfo3.setBounds(105, 200, 700, 30);
        lblInfo4.setBounds(105, 200, 600, 30);
        lblInfo5.setBounds(435, 200, 100, 30);
        
        lblInfo.setForeground(Color.white);
        lblInfo2.setForeground(Color.white);
        lblInfo3.setForeground(Color.white);
        lblInfo4.setForeground(Color.white);
        lblInfo5.setForeground(Color.white);
        
        lblInfo.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo2.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo3.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo4.setFont(new Font("Arial", Font.BOLD, 18));
        lblInfo5.setFont(new Font("Arial", Font.BOLD, 18));
        
        lblInfo.setVisible(false);
        lblInfo2.setVisible(false);
        lblInfo3.setVisible(false);
        lblInfo4.setVisible(false);
        lblInfo5.setVisible(false);
        
        if(secreto) 
        {
        	lblInfo.setText(jugador.getNombre() + " venció al mal y atravesó el portal de vuelta.");
        	lblInfo2.setText("Los habitantes festejaron su victoria y lo proclamaron rey.");
        	lblInfo3.setText("El Rey " + jugador.getNombre() + " gobernó pacíficamente durante muchos años.");
        	lblInfo4.setText("Construyeron una estatua de oro en su honor.");
        }
        else
        {
        	lblInfo.setText(jugador.getNombre() + " derrotó al rey maldito y ocupó su lugar en el trono.");
        	lblInfo2.setText("Prosperó la paz durante un tiempo...");
        	lblInfo3.setText("Pero " + jugador.getNombre() + " sentía que algo lo observaba dese otro plano...");
        	lblInfo4.setText("Finalmente enloqueció y se convirtió en el siguiente rey maldito.");
        }
        panelFondo.add(lblInfo);
        panelFondo.add(lblInfo2);
        panelFondo.add(lblInfo3);
        panelFondo.add(lblInfo4);
        panelFondo.add(lblInfo5);
        
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
            			lblInfo.setVisible(false);
            			lblInfo3.setVisible(false);
            			lblInfo4.setVisible(true);
            			break;
            		case 5:
            			lblInfo.setVisible(false);
            			lblInfo4.setVisible(false);
            			lblInfo5.setVisible(true);
            			break;
            		case 6:
            			lblInfo.setVisible(false);
            			sound.pararMusica();
            			juego.clickFinal();
            			break;
            	}
            	panelFondo.repaint();
        	}
        });
	}
}
