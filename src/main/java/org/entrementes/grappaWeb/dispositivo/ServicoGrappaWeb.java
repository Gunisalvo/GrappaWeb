package org.entrementes.grappaWeb.dispositivo;

import org.entrementes.grappaWeb.GrappaWeb;

import br.com.caelum.grappa.annotation.Hardware;
import br.com.caelum.grappa.annotation.PinListener;
import br.com.caelum.grappa.model.GrappaInstruction;
import br.com.caelum.grappa.model.builder.LogicInstruction;
import br.com.caelum.grappa.pin.PhysicalDevice;
import br.com.caelum.grappa.pin.PinService;

@PinListener(addresses=3)
public class ServicoGrappaWeb implements PinService{

	@Hardware
	private PhysicalDevice pi;
	
	private Integer contador = 0;
	
	@Override
	public void processEvent(Integer estadoPino) {
		GrappaWeb.info("processando: "+ estadoPino);
		
		if(estadoPino.intValue() == 1){
			this.contador += 1;
			GrappaWeb.info("contador incrementado: "+ this.contador);
			
			if(this.contador.intValue() % 2 == 0){
				GrappaInstruction resposta = pi.processInstruction(new LogicInstruction().address(1).write(2));
				GrappaWeb.info("estado pino: " + resposta.getResult() + ", " + resposta.getBody());
			}
		}
	}
}
