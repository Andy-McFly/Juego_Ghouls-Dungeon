package es.studium;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JuegoHUD extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	Modelo modelo = new Modelo();
	Jugador jugador;
	Armadura armadura;
	Arma arma;
	Font fontTitulo = new Font("Calibri", Font.BOLD, 18);
    Font fontTexto = new Font("Calibri", Font.PLAIN, 14);
    
    Image imgFondo = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
    Image imgTag = new ImageIcon(getClass().getResource("img/tag.png")).getImage();
    Image imgFase = new ImageIcon(getClass().getResource("img/hud.png")).getImage();
    Image imgArma;
    Image imgArmadura;
    
    int defensaTotal = 0;
    private String log = "";
	
	public JuegoHUD(Jugador j, Arma a, Armadura arm)
	{
		jugador = j;
		arma = a;
		armadura = arm;
		defensaTotal = jugador.getDefensa() + armadura.getDefensaArmadura();
		imgArmadura = modelo.actualizarHUDArmadura(armadura);
		imgArma = modelo.actualizarHUDArma(arma);
		
	}
	
	public void dibujar(Graphics g)
    {
        g.drawImage(imgFondo, 0, 478, 885, 184, null);
        g.drawImage(imgTag, 30, 500, 330, 155, null);
        g.drawImage(imgFase, 345, 0, 207, 50, null);
        g.drawImage(imgArmadura, 260, 555, 21+20, 21+20, null);
        g.drawImage(imgArma, 175, 555, 21+20, 21+20, null);

        g.setFont(fontTitulo);
        g.setColor(Color.black);
        g.drawRoundRect(260, 555, 21+20, 21+20, 3, 3);
        g.drawRoundRect(175, 555, 21+20, 21+20, 3, 3);
        g.drawString(jugador.getNombre(), 40, 525);

        g.setFont(fontTexto);
        g.drawString("Nivel: " + jugador.getNivel(), 40, 550);
        g.drawString("Vit: " + jugador.getVitalidad(), 40, 570);
        g.drawString("Atq: " + (arma.getMinArma() + jugador.getAtaque()) + " - " + (arma.getMaxArma() + jugador.getAtaque()) + "(Base " + jugador.getAtaque() + ")", 40, 590);
        g.drawString("Atq: " + arma.getMinArma() + " - " + arma.getMaxArma(), 175, 550);
        g.drawString("Def: " + (defensaTotal) + "(Base " + jugador.getDefensa() + ")", 40, 610);
        g.drawString("Def: " + armadura.getDefensaArmadura(), 260, 550);
        g.drawString("Oro: " + jugador.getOro(), 40, 630);
        g.setColor(Color.yellow);
        g.drawString(log, 550, 555);
    }
	
	public void setLog(String log)
    {
        this.log = log;
    }

}
