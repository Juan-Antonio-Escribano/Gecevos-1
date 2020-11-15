package org.componente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class Acordeon extends JPanel {

	private ArrayList<JToggleButton> barras = new ArrayList<JToggleButton>();
	private ArrayList<JPanel> contenido = new ArrayList<JPanel>();
	private int altoContenido = 100;
	private int altoBotones = 40;

	/**
	 * 
	 */
	public Acordeon() {

		setBounds(0, 0, 600, 500);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

	}

	/**
	 * 
	 * @param componente
	 * @param titulo
	 */
	public void annadir (JPanel panel, String titulo) {
		JToggleButton boton = new JToggleButton(titulo);

		if(barras.size()>0)
			boton.setBounds(0,barras.get(barras.size()-1).getY()+barras.get(barras.size()-1).getHeight()
					,getWidth(),getAltoBotones());
		else
			boton.setBounds(0,0,getWidth(),getAltoBotones());

		add(panel);
		contenido.add(panel);

		for (JPanel Jpanel : contenido) {
			Jpanel.setVisible(false);
			Jpanel.setLayout(null);
		}

		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(boton.isSelected()) {
					contenido.get(barras.indexOf(boton)).setBounds(0,boton.getY()+boton.getHeight()
					,boton.getWidth(),getAltoContenido());

					contenido.get(barras.indexOf(boton)).setVisible(true);

					for (int i = barras.indexOf(boton)+1; i < barras.size(); i++) {
						if(barras.get(i-1).isSelected())
							barras.get(i).setLocation(contenido.get(i-1).getX()
									, contenido.get(i-1).getHeight()+contenido.get(i-1).getY());
						else
							barras.get(i).setLocation(barras.get(i-1).getX()
									, barras.get(i-1).getHeight()+barras.get(i-1).getY());
						if(barras.get(i).isSelected())
							contenido.get(i).setLocation(barras.get(i).getX()
									, barras.get(i).getHeight()+barras.get(i).getY());

					}
				}else {
					contenido.get(barras.indexOf(boton)).setVisible(false);

					for (int i = barras.indexOf(boton)+1; i < barras.size(); i++) {
						if(barras.get(i-1).isSelected())
							barras.get(i).setLocation(contenido.get(i-1).getX()
									, contenido.get(i-1).getHeight()+contenido.get(i-1).getY());
						else 
							barras.get(i).setLocation(barras.get(i-1).getX()
									, barras.get(i-1).getHeight()+barras.get(i-1).getY());
						if(barras.get(i).isSelected())
							contenido.get(i).setLocation(barras.get(i).getX()
									, barras.get(i).getHeight()+barras.get(i).getY());
					}

				}
			}
		});

		add(boton);
		barras.add(boton);
	}

	/**
	 * Elimina un componente del acordeon
	 * @param índice del componente
	 */
	public void eliminar(int indice) {

		if(indice == 0) {
			barras.get(indice+1).setLocation(0,0);
			contenido.get(indice+1).setLocation(0, barras.get(indice+1).getY()+barras.get(indice+1).getHeight());
			for (int i = indice+2; i < barras.size(); i++) {
				if(barras.get(i-1).isSelected()) {
					barras.get(i).setLocation(0, contenido.get(i-1).getY()+contenido.get(i-1).getHeight());
					contenido.get(i).setLocation(0, barras.get(i).getY()+barras.get(i).getHeight());
				}else {
					barras.get(i).setLocation(0, barras.get(i-1).getY()+barras.get(i-1).getHeight());
					contenido.get(i).setLocation(0, barras.get(i).getY()+barras.get(i).getHeight());
				}	
			}
		}else {
			
			for (int i = indice+1; i < barras.size(); i++) {
				if(barras.get(i-1).isSelected()) {
					barras.get(i).setLocation(0, contenido.get(i-2).getY()+contenido.get(i-2).getHeight());
					contenido.get(i).setLocation(0, barras.get(i).getY()+barras.get(i).getHeight());
				}else {
					barras.get(i).setLocation(0, barras.get(i-1).getY()+barras.get(i-1).getHeight());
					contenido.get(i).setLocation(0, barras.get(i).getY()+barras.get(i).getHeight());
				}
			}
		}

		barras.get(indice).setVisible(false);
		contenido.get(indice).setVisible(false);
		barras.remove(indice);
		contenido.remove(indice);

	}

	/**
	 * Devuelve el alto del contenido
	 * @return el alto del contenido
	 */
	public int getAltoContenido() {
		return this.altoContenido;
	}

	/**
	 * Asigna el alto del contenido
	 * @param alto del contenido
	 */
	public void setAltoContenido(int alto) {
		this.altoContenido = alto;
	}
	/**
	 * Devuelve el alto del botones
	 * @return el alto del botones
	 */
	public int getAltoBotones() {
		return this.altoBotones;
	}

	/**
	 * Asigna el alto del botones
	 * @param alto del botones
	 */
	public void setAltoBotones(int alto) {
		this.altoBotones = alto;
	}

}
