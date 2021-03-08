package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;



public class Soldado {
	double x;
	double y;
	double ancho;
	double alto;
	double angulo;
	boolean contacto;
	Image img;
	private final int ALTURA_MAX_SALTO = 50;
	private boolean saltandoArriba = true;
	private int alturaSalto = 0;
	public boolean saltando = false;
	double pi = Math.PI;
	int signo = -1;
	
	Soldado(double x,double y,double ancho,double alto,double angulo){
	    this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.img =  Herramientas.cargarImagen("imagenes/soldado.png");
	}
	
	
	public void delete() {
		this.y=this.y+5000;
	}
	
	public void Agente(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0);
	}
	
	public void mover() {
		//con angulo indico el sentido del movimiento con 0 a la izq y 180 a la der
		
		this.x += Math.cos(pi)*2;
		//this.y += Math.sin(this.angulo)*2;
		
		if (this.x > 1600) {
			this.x=950;
			pi = pi + Math.PI*signo;
			signo = signo*-2;
		}
		
	}
	
	// movimientos de soldados
	public void moverAtras() {
		this.x += Math.cos(this.angulo)*2;
		
		if (this.x < -50) {
			this.x=850;
		}	
	}	
	
	
	
	public void subir(int dy) {
		this.y = this.y + dy;
	}

	
	//salto de soldados voladores
	
	public void soldadoSaltar(Entorno entorno) {
		if (!saltando)
			saltando = true;

		if (alturaSalto < ALTURA_MAX_SALTO && saltandoArriba) {
			this.subir(-3);
			alturaSalto += 3;
			if (alturaSalto >= ALTURA_MAX_SALTO)
				saltandoArriba = false;
		}
		if (saltando && !saltandoArriba) {
			this.img =  Herramientas.cargarImagen("imagenes/soldadovolador.png");
			this.subir(3);
			alturaSalto -= 3;
			if (alturaSalto <= 0) {
				
				saltandoArriba = true;
				saltando = false;
				alturaSalto = 0;
			}
		}
	}
	
	public boolean toca(Soldado Soldado) {
			
			return x > Soldado.x - Soldado.ancho/2 && 
					x < Soldado.x + Soldado.ancho/2 &&
					y > Soldado.y - Soldado.alto/2 && 
					y < Soldado.y + Soldado.alto/2 ;
					
		}
	
	
	
	public boolean quema(Soldado soldado,Fuego fuegos) {
		if(( soldado.x ==fuegos.x-fuegos.ancho/2) ||( soldado.x == fuegos.x+fuegos.ancho/2) || (soldado.y == fuegos.y-fuegos.alto/2)|| (soldado.y == fuegos.y+fuegos.alto/2)) {
			pi = pi + Math.PI*signo;
			signo = signo*-1;
		
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
		
		
	public boolean tocaPrincesa(Princesa princesa) {
		if(( x >princesa.x-princesa.ancho/2) &&( x < princesa.x+princesa.ancho/2) && (y > princesa.y-princesa.alto/2)&& (y < princesa.y+princesa.alto/2)) {
			return true;
		}
		else {
			return false;
		}
	
	}
	//movimientos
	public void moverAdelante() {
		
		this.x -= Math.cos(this.angulo)*2;
		this.y += Math.sin(this.angulo)*2;
		
		
		if (this.x < -50) {
			this.x=1000;
		}	
		
		
		
	}
public void moverAlas() {
		
		this.x -= Math.cos(this.angulo)*1;
				
		
		if (this.x < -50) {
			this.x=1000;
		}	
		
	}
	
	
	}
	