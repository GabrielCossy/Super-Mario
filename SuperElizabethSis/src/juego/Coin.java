package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Coin {
	double x;
	double y;
	double ancho;
	double alto;
	double angulo;
	Image img;
	boolean contacto;
		
	Coin(double x,double y,double ancho,double alto,double angulo){
	    this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.img =  Herramientas.cargarImagen("imagenes/coin.png");
		
	}
		
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0);
	}
	
	public void moverAdelante() {
		
		this.x -= Math.cos(this.angulo)*2;
		
		
		if (this.x > 850) {
			this.x=0;
		}
		if (this.x < -50) {
			this.x=850;
		}	
		
	}
	public boolean isContacto() {
		return contacto;
	}
	public void setContacto(boolean contacto) {
		this.contacto = contacto;	
	}
		
	
	
}