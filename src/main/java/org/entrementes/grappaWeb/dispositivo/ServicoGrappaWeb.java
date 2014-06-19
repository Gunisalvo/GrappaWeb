package org.entrementes.grappaWeb.dispositivo;

import org.entrementes.grappa.gpio.Raspberry;
import org.entrementes.grappa.gpio.ServicoGpio;
import org.entrementes.grappa.marcacao.Hardware;
import org.entrementes.grappa.marcacao.ObservadorGpio;
import org.entrementes.grappa.modelo.instrucao.InstrucaoLogica;

@ObservadorGpio(endereco=3)
public class ServicoGrappaWeb implements ServicoGpio{

	@Hardware
	private Raspberry pi;
	
	private Integer contador;
	
	@Override
	public void processarServico(Integer estadoPino) {
		if(estadoPino.intValue() == 1){
			this.contador += 1;
		}
		if(this.contador.intValue() % 2 == 0){
			pi.processarInstrucao(new InstrucaoLogica().endereco(1).escrever(2));
		}
	}
}
