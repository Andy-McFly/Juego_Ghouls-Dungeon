package es.studium;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal
{
	Modelo modelo = new Modelo();
	String sndPrincipal = "sound/principal.mp3";
	String sndSeleccion = "sound/seleccion.mp3";
	Sonido sound;
	
	public Principal() 
	{
		Image imagenFondo = new ImageIcon(getClass().getResource("img/menuPrincipal.png")).getImage();
		Image imagenTitulo = new ImageIcon(getClass().getResource("img/titulo.png")).getImage();
		
		JPanel panelFondo = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);
                g.drawImage(imagenTitulo, 20, 40, 350, 29, null);
            }
        };
		
		JFrame vPrincipal = new JFrame();
		vPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vPrincipal.setTitle("Ghouls n' Dungeon");
		vPrincipal.setSize(526,334);
		vPrincipal.setLocationRelativeTo(null);
		vPrincipal.setResizable(false);
		vPrincipal.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JButton btnNueva = new JButton("Nueva Partida");
		btnNueva.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				sound.pararMusica();
				sound = new Sonido(sndSeleccion);
				sound.start();
				List<Jugador> jugadores = modelo.datosJugador();
				Jugador jugador = jugadores.get(0);
				vPrincipal.setVisible(false);
				new MenuNombre(vPrincipal, jugador);
			}
		});
		btnNueva.setBackground(new Color(143, 148, 40));
		btnNueva.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNueva.setBounds(177, 105, 164, 53);
		panelFondo.add(btnNueva);
		
		JButton btnHistorial = new JButton("Diario");
		btnHistorial.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				sound.pararMusica();
				sound = new Sonido(sndSeleccion);
				sound.start();
				vPrincipal.setVisible(false);
				new MenuDiario(vPrincipal);
			}
		});
		btnHistorial.setBackground(new Color(81, 84, 22));
		btnHistorial.setFont(new Font("Calibri", Font.BOLD, 18));
		btnHistorial.setForeground(Color.lightGray);
		btnHistorial.setBounds(197, 182, 128, 43);
		panelFondo.add(btnHistorial);
		
		vPrincipal.setVisible(true);
		vPrincipal.requestFocusInWindow();
		
		sound = new Sonido(sndPrincipal);
		sound.start();
	}

	public static void main(String[] args)
	{
		new Principal();
	}

}
