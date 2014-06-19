package org.entrementes.grappaWeb;

import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;

public class GrappaWeb {
	
	private static GrappaWeb INSTANCIA;
	
	private DispositivoGrappaWeb dispositivo;
	
	private GrappaWeb(DispositivoGrappaWeb dispositivo){
		this.dispositivo = dispositivo;
	}
	
	public static void construir(DispositivoGrappaWeb dispositivo){
		INSTANCIA = new GrappaWeb(dispositivo);
	}

	public static DispositivoGrappaWeb carregarDispositivo() {
		return INSTANCIA.getDispositivo();
	}
	
	public DispositivoGrappaWeb getDispositivo() {
		return this.dispositivo;
	}
	
	
	

}
