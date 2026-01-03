package es.studium;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MenuDiario
{
	Modelo modelo = new Modelo();
	String sndSeleccion = "sound/seleccion.mp3";
	Sonido sound;
	
	List<HistorialPartidas> partidas = modelo.datosPartidas();
	
	JTextArea textArea = new JTextArea();
	Font fuente = new Font("Calibri", Font.BOLD, 14);
	
	public MenuDiario(JFrame vPrincipal) 
	{
		JFrame vDiario = new JFrame();
		vDiario.getContentPane().setBackground(new Color(143, 148, 40));
		vDiario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				textArea.setText("");
				vDiario.dispose();
				vPrincipal.setVisible(true);
			}
		});
		vDiario.setTitle("Ghouls n' Dungeon: Diario");
		vDiario.setSize(573, 547);
		vDiario.setResizable(false);
		vDiario.setLocationRelativeTo(null);
		vDiario.getContentPane().setLayout(null);
		textArea.setEditable(false);
		textArea.setBackground(new Color(75, 79, 21));
		textArea.setFont(fuente);
		textArea.setForeground(Color.lightGray);
				
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 47, 539, 356);
		vDiario.getContentPane().add(scrollPane);
		
		JLabel lblInfo = new JLabel("Registro de Aventuras:");
		lblInfo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInfo.setBounds(194, 10, 173, 27);
		vDiario.getContentPane().add(lblInfo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(0, 0, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound = new Sonido(sndSeleccion);
				sound.start();
				textArea.setText("");
				vDiario.dispose();
				vPrincipal.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVolver.setBounds(242, 445, 84, 36);
		vDiario.getContentPane().add(btnVolver);
		
		for(int i = 0; i < partidas.size(); i++) 
		{
			String resFase = "";
			String resOro;
			String separador = "-----------------------------------------------------------------"
					+ "----------------------------------" + "\n";
			String fecha = partidas.get(i).getFecha();
			String nombre = partidas.get(i).getNombreJugador();
			int nivel = partidas.get(i).getNivel();
			int oroEquipo = partidas.get(i).getOroEquipo();
			int oroVida = partidas.get(i).getOroVida();
			int oroGastado = oroEquipo + oroVida;
			int ultimoCombate = partidas.get(i).getCombateFinal();
			
			switch(ultimoCombate) 
			{
				case 1:
					resFase = fecha + " " + nombre + " fue derrotado en el bosque al nivel " + nivel;
					break;
				case 2:
					resFase = fecha + " " + nombre + " fue derrotado en la puerta del castillo al nivel " + nivel;
					break;
				case 3:
					resFase = fecha + " " + nombre + " fue derrotado en el castillo al nivel " + nivel;
					break;
				case 4:
					resFase = fecha + " " + nombre + " fue derrotado en la sala del trono al nivel " + nivel;
					break;
				case 5:
					resFase = fecha + " " + nombre + " venció al rey y ocupó su lugar al nivel " + nivel;
					break;
				case 6:
					resFase = fecha + " " + nombre + " fue derrotado en combate contra la maldad al nivel " + nivel;
					break;
				case 7:
					resFase = fecha + " " + nombre + " venció a la maldad y tuvo un reinado próspero al nivel " + nivel;
					break;
			}
			resOro = "Gastó un total de " + oroGastado + " monedas de oro: " 
					+ oroEquipo + " en equipo y " + oroVida + " en puntos de vitalidad.";
			textArea.append(separador + resFase + "\n" + resOro + "\n" + separador + "\n");
		}
		
		vDiario.setVisible(true);
	}
}
