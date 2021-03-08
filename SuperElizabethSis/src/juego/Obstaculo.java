package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	double x;
	double y;
	double ancho;
	double alto;
	double angulo;
	boolean contacto;
	Image img;
	
	Obstaculo(double x,double y,double ancho,double alto,double angulo){
	    this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.contacto=false;
		this.img =  Herramientas.cargarImagen("imagenes/bloque.png");
		
	}
	
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0);
	}
	
	
	public boolean tocaPrincesa(Princesa princesa) {
		if(( x >princesa.x-princesa.ancho/2) &&( x < princesa.x+princesa.ancho/2) && (y > princesa.y-princesa.alto/2)&& (y < princesa.y+princesa.alto/2)) {
			
			return true;
		}
		else {	
			return false;
			}
	}	
		public boolean isContacto() {
			return contacto;
		}
		public void setContacto(boolean contacto) {
			this.contacto = contacto;	
		}
		
		//colison
		
		
		public boolean toca(Fuego Fuego) {
			
			return x > Fuego.x - Fuego.ancho/2 && 
					x < Fuego.x + Fuego.ancho/2 &&
					y > Fuego.y - Fuego.alto/2 && 
					y < Fuego.y + Fuego.alto/2 ;
					
		}
		public boolean toca(Obstaculo Obstaculo) {
			
			return x > Obstaculo.x - Obstaculo.ancho/2 && 
					x < Obstaculo.x + Obstaculo.ancho/2 &&
					y > Obstaculo.y - Obstaculo.alto/2 && 
					y < Obstaculo.y + Obstaculo.alto/2 ;
					
		}

		//movimiento para delante
		
		public void moverAdelante() {
			if(x>0) {
				x-=1.5;
			}
			else {
				x=780;
				this.contacto=false;
			}
}

}
