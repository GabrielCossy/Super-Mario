package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Fuego {
	
	double x;
	double y;
	double ancho;
	double alto;
	boolean movimiento;
	boolean contacto;
	Image img;
	
	Fuego(double x,double y){
		this.x=x;
		this.y=y;
		
		this.ancho=50;
		this.alto=10;
		this.movimiento=false;
		this.img =  Herramientas.cargarImagen("imagenes/fuego.png");
	}
	
	//dibujo
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0);
	}
	public void lanzar() {
			this.x=this.x+5;
	
	}
	
		
	
	public boolean salenDeRango(Entorno entorno) {
		if(this.x+(this.ancho/2)>=entorno.ancho())
			return true;
		else
			return false;
	}
	
	//quema a soldados
	
	public boolean Quema(Soldado soldado) {
		if(( x > soldado.x-soldado.ancho/2) &&( x < soldado.x+soldado.ancho/2) && (y > soldado.y-soldado.alto/2)&& (y < soldado.y+soldado.alto/2) && (x<820))   {
				return true;
		}
		else {
			return false;
		
		}
	}
	public void delete() {
		this.y=this.y+5000;
	}
	public boolean isContacto() {
		
		return contacto;
	}
	public void setContacto(boolean contacto) {
		
		this.contacto = contacto;	
	}	
	
	
	public void pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
}
	
