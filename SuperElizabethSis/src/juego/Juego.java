package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego
{
	Princesa princesa;	
	public static final String Princesa = null;
	
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Obstaculo[] obstaculo;
	private Soldado[] soldado;
	private Nube[] nube;
	private Coin[] coin;
	private Fuego[] fuegos;
	private Image fondo;
	boolean contacto;	
	private Fuego fuego;
	int posc=850;
	int Vidas = 3;
	int puntaje = 0;
	int posc1 =0;
	double pi = Math.PI;
	int signo = -1;	
	public Object x;
	private Image img;
	private Image noche;
	private Image ganaste;
	//private Clip salta; no funciono audio de sonido
	int cont=350;
	int poscx=850;
	Random alea =new Random();
	
	
	// Variables y métodos propios de cada grupo
	// ...	

	Juego()
	{
		
		
		
		// Cargamos entorno
		
		this.entorno = new Entorno(this, "Super Elizabeth Sis - Grupo ... - v1", 800, 600);	
		// Cargamos imagenes
		this.fondo = Herramientas.cargarImagen("imagenes/Cielo.png");
		this.img = Herramientas.cargarImagen("imagenes/fin.png");
		this.noche = Herramientas.cargarImagen("imagenes/CieloNoche.png");
		this.ganaste = Herramientas.cargarImagen("imagenes/ganaste1.png");
		//this.salta = Herramientas.cargarSonido("imagenes/jump.wav"); sonido fallido de salto
		
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.fuegos=new Fuego[100];
		this.princesa=new Princesa(100,490,20,40,0);
		this.soldado=new Soldado[3];
		this.obstaculo=new Obstaculo[3];
		this.nube=new Nube[3];
		this.coin=new Coin[2];
		
		
		//soldados iniciales
		
		for(int i=0;i<soldado.length;i++) {
						
			
			this.soldado[i]=new Soldado(alea.nextDouble()*200+i*200+200,490,20,40,0);
						
			}
			
		//obstaculos iniciales
		
		this.obstaculo[0]=new Obstaculo(700,480,20,60,0);
		this.obstaculo[1]=new Obstaculo(900,480,20,60,0);
		this.obstaculo[2]=new Obstaculo(1200,480,20,60,0);
		
		// coin iniciales
		
		this.coin[0]=new Coin(800,350,100,60,0);
		this.coin[1]=new Coin(500,400,100,60,0);
		
		//elementos
		
		this.nube[0]=new Nube(900,50,100,60,0);
		this.nube[1]=new Nube(600,200,100,60,0);
		this.nube[2]=new Nube(300,100,100,60,0);
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	
	}

		
	public void tick()
	{
		//fondo
		this.entorno.dibujarImagen(this.fondo,400,300, 0);
		
		//Movimiento "princesa" 
		
		if (entorno.sePresiono(entorno.TECLA_ARRIBA) || princesa.saltando)
			princesa.princesaSaltar(entorno);

		
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			princesa.moverAdelante();
			
		}
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			princesa.moverAtras();
		
		}
		
		//Colision
		
		for(int i=0;i<obstaculo.length;i++) {
			if(princesa.toca(obstaculo[i]) && !obstaculo[i].contacto && Vidas!=0){	 
					obstaculo[i].setContacto(true);
					this.obstaculo[i]=new Obstaculo(poscx,480,20,60,0);
					Vidas--;
					}
			}
		for(int i=0;i<soldado.length;i++) {
		if(princesa.toca(soldado[i]) && !soldado[i].contacto && Vidas!=0){	 
			soldado[i].setContacto(true);
			this.soldado[i]=new Soldado(poscx,490,20,60,0);			
			Vidas--;
				}
		}
		for(int i=1;i<soldado.length;i++) {
			if(soldado[i-1].toca(soldado[i]) && !soldado[i].contacto && Vidas!=0){	 
				soldado[i].setContacto(true);
				this.soldado[i]=new Soldado(alea.nextDouble()*500+i*200+700,480,20,60,0);
			}
			}
		for(int i=0;i<coin.length;i++) {
				if(princesa.toca(coin[i]) ){	 
				this.coin[i]=new Coin(850,cont,100,60,0);
					cont=cont+50;
					puntaje=puntaje+5;
				}
				if (cont==450) {
					cont=350;
				}
		}
		
		
		if (Vidas==0) {
			this.entorno.dibujarImagen(this.img,400,300, 0);
			this.entorno.cambiarFont("Arial", 30, Color.white);
			this.entorno.escribirTexto("Puntaje: " + puntaje, 550, 200);
			fuego.delete();
			
		}	
		if (puntaje>=90 && puntaje<200) {
			for(int i=0;i<soldado.length;i++) {
				this.entorno.dibujarImagen(this.noche,400,300, 0);
				this.soldado[i].moverAlas();
				this.soldado[i].soldadoSaltar(entorno);
				
				}
			}
		
		
		if (puntaje>=200) {
			for(int i=0;i<soldado.length;i++) {
				this.entorno.dibujarImagen(this.ganaste,400,300, 0);
				this.entorno.cambiarFont("Arial", 30, Color.white);
				this.entorno.escribirTexto("Puntaje: " + puntaje, 300,550);
				fuego.delete();

				}
			}
		
		
		//dibujos
		for(int f=0;f<this.fuegos.length;f++) {
			if(this.fuegos[f]!=null) {
				this.fuegos[f].dibujarse(entorno);
				this.fuegos[f].lanzar();
				for(int i=0;i<soldado.length;i++) {
				if(fuegos[f].Quema(soldado[i])){
					posc1=posc;
					this.soldado[i]=new Soldado(alea.nextDouble()*500+i*200+700,480,20,60,0);
					if (soldado[i].toca(soldado[i]));
					this.x=950;
					if (posc>1100) {
						 posc=1000 ;
					 }
					if (posc1==posc){
						 posc=posc+50;
					 }
					puntaje=puntaje+5;					
					fuegos[f].delete();
						}
			}
		}
			}
		int j=0;
		while(j<this.fuegos.length && this.fuegos[j]!=null) {
			j++;
		}
		
		this.princesa.dibujarse(this.entorno);
		
		this.entorno.cambiarFont("Arial", 18, Color.white);
		this.entorno.escribirTexto("Cantidad de vidas: " + Vidas, 550, 50);
		this.entorno.cambiarFont("Arial", 18, Color.white);
		this.entorno.escribirTexto("puntaje: " + puntaje, 550, 25);
		
		for(int i=0;i<nube.length;i++) {
			this.nube[i].dibujarse(this.entorno);
		}
		for(int i=0;i<coin.length;i++) {
			this.coin[i].dibujarse(this.entorno);
		}
		for(int i=0;i<obstaculo.length;i++) {
			this.obstaculo[i].dibujarse(this.entorno);
					
		}
		for(int i=0;i<soldado.length;i++) {
			
			soldado[i].dibujarse(entorno);
		}
		//movimientos para adelante
		
		
		for(int i=0;i<nube.length;i++) {
			this.nube[i].moverAdelante();
		}for(int i=0;i<coin.length;i++) {
			this.coin[i].moverAdelante();
		}for(int i=0;i<obstaculo.length;i++) {
			this.obstaculo[i].moverAdelante();
		}
		for(int i=0;i<soldado.length;i++) {
			this.soldado[i].moverAdelante();
			
			}
		if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO)) {
			this.fuegos[j]=princesa.dispararFuego();
		}
		
		}
	
		
	private void obstaculo(Entorno entorno2) {
		// TODO Apéndice de método generado automáticamente
			
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}