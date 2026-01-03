package es.studium;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuNombre
{
	String sndSeleccion = "sound/seleccion.mp3";
	Sonido sound;
	
	private JTextField txfNombre;
	
	public MenuNombre(JFrame vPrincipal, Jugador jugador) 
	{
		JFrame vMenuNombre = new JFrame();
		vMenuNombre.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vMenuNombre.dispose();
				vPrincipal.setVisible(true);
			}
		});
		vMenuNombre.setTitle("Ghouls n' Dungeon: Nueva Partida");
		vMenuNombre.setSize(450, 300);
		vMenuNombre.setLocationRelativeTo(null);
		vMenuNombre.getContentPane().setBackground(new Color(0, 0, 0));
		vMenuNombre.getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("Introduce el nombre del héroe:");
		lblInfo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfo.setForeground(Color.yellow);
		lblInfo.setBounds(94, 58, 238, 23);
		vMenuNombre.getContentPane().add(lblInfo);
		
		JLabel lblError = new JLabel("Debes escribir un nombre!");
		lblError.setFont(new Font("Calibri", Font.BOLD, 18));
		lblError.setForeground(Color.black);
		lblError.setBounds(117, 160, 238, 23);
		vMenuNombre.getContentPane().add(lblError);
		
		txfNombre = new JTextField();
		txfNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		txfNombre.setBounds(165, 115, 96, 23);
		vMenuNombre.getContentPane().add(txfNombre);
		txfNombre.setColumns(10);
		
		JButton btnComenzar = new JButton("Comenzar Aventura");
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txfNombre.getText().isBlank()) 
				{
					lblError.setForeground(Color.black);
					sound = new Sonido(sndSeleccion);
					sound.start();
					jugador.setNombre(txfNombre.getText());
					vMenuNombre.dispose();
					new Juego(vPrincipal, jugador);
				}
				else 
				{
					lblError.setForeground(Color.red);
				}
				
			}
		});
		btnComenzar.setFont(new Font("Calibri", Font.BOLD, 16));
		btnComenzar.setBounds(117, 191, 201, 37);
		vMenuNombre.getContentPane().add(btnComenzar);
		
		vMenuNombre.setVisible(true);
	}
}
