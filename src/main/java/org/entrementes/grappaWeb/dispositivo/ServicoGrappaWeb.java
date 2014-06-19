package org.entrementes.grappaWeb.dispositivo;

import org.entrementes.grappa.gpio.Raspberry;
import org.entrementes.grappa.gpio.ServicoGpio;
import org.entrementes.grappa.marcacao.Hardware;
import org.entrementes.grappa.marcacao.ObservadorGpio;
import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.instrucao.InstrucaoLogica;
import org.entrementes.grappaWeb.GrappaWeb;

@ObservadorGpio(endereco=3)
public class ServicoGrappaWeb implements ServicoGpio{

	@Hardware
	private Raspberry pi;
	
	private Integer contador = 0;
	
	@Override
	public void processarServico(Integer estadoPino) {
		GrappaWeb.info("processando: "+ estadoPino);
		if(estadoPino.intValue() == 1){
			this.contador += 1;
			GrappaWeb.info("contado incrementado: "+ this.contador);
		}
		if(this.contador.intValue() % 2 == 0){
			InstrucaoGrappa resultado = pi.processarInstrucao(new InstrucaoLogica().endereco(1).escrever(2));
			GrappaWeb.info("estado pino: " + resultado.getResultado() + ", " + resultado.getCorpo());
		}
	}
}
