package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	double x;
	double y;
	double ancho;
	double alto;
	double angulo;
	int Vidas;
	Image img;
	private int ALTURA_MAX_SALTO = 150;
	private boolean saltandoArriba = true;
	private int alturaSalto = 0;
	public boolean saltando = false;
		
	
	
	
	Princesa(double x,double y,double ancho,double alto,double angulo){
	    this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.img =  Herramientas.cargarImagen("imagenes/princesa_cam.png");
		
	
	}
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(img, x, y, 0);
	}
	
	public void subir() {
		this.y-=0.5;
		this.y=300;
	}	

		

	public void subir(int dy) {
			this.y = this.y + dy;
		}

	
	public void bajar() {
		this.y+=0.5;
		this.y=480;
	}
	
	
	//colision con objetos
	
	public boolean toca(Obstaculo obstaculo) {
	
		return x > obstaculo.x - obstaculo.ancho/2 && 
				x < obstaculo.x + obstaculo.ancho/2 &&
				y > obstaculo.y - obstaculo.alto/2 && 
				y < obstaculo.y + obstaculo.alto/2 ;
				
	}
	public boolean toca(Soldado Soldado) {
		
		return x > Soldado.x - Soldado.ancho/2 && 
				x < Soldado.x + Soldado.ancho/2 &&
				y > Soldado.y - Soldado.alto/2 && 
				y < Soldado.y + Soldado.alto/2 ;
	}
	
	public boolean toca(Coin Coin) {
			
			return x > Coin.x - Coin.ancho/2 && 
					x < Coin.x + Coin.ancho/2 &&
					y > Coin.y - Coin.alto/2 && 
					y < Coin.y + Coin.alto/2 ;
		}
	public int detenerse() {
		this.x=100;
		this.y=480;
		return 0;
	}
	
	// funcion de disparar
	Fuego dispararFuego() {
		return new Fuego(this.x,this.y);
	}
	
	
	public void moverAdelante() {
			
			this.x += Math.cos(this.angulo)*2;
			this.y += Math.sin(this.angulo)*2;
			
			if (this.x > 300) {
				this.x=300;
			}
				
			
		}
	public void moverAtras() {
			
			this.x -= Math.cos(this.angulo)*2;
			
			
			if (this.x > 850) {
				this.x=0;
			}
			if (this.x < 0) {
				this.x=0;
			}	
			
		}
	
	public void delete() {
		this.y=this.y+5000;
	}
	
	// salto de princesa
	
	public void princesaSaltar(Entorno entorno) {
		if (!saltando)
			saltando = true;

		if (alturaSalto < ALTURA_MAX_SALTO && saltandoArriba) {
			this.subir(-3);
			alturaSalto += 3;
			if (alturaSalto >= ALTURA_MAX_SALTO)
				saltandoArriba = false;
		}
		if (saltando && !saltandoArriba) {
			this.img =  Herramientas.cargarImagen("imagenes/princesa_sal.png");
		
			this.subir(3);
			alturaSalto -= 3;
			if (alturaSalto <= 0) {
				this.img =  Herramientas.cargarImagen("imagenes/princesa_cam.png");
				
				saltandoArriba = true;
				saltando = false;
				alturaSalto = 0;
			}
		}
	}
	
	}