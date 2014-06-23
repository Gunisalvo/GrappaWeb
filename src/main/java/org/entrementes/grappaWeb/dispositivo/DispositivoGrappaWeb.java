package org.entrementes.grappaWeb.dispositivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.entrementes.grappaWeb.GrappaWeb;

import br.com.caelum.grappa.annotation.Device;
import br.com.caelum.grappa.annotation.Hardware;
import br.com.caelum.grappa.annotation.PinListener;
import br.com.caelum.grappa.model.GrappaInstruction;
import br.com.caelum.grappa.model.PhysicalDeviceState;
import br.com.caelum.grappa.model.builder.LogicInstruction;
import br.com.caelum.grappa.pin.PhysicalDevice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="dispositivo")
@Device(nome="dispositivo")
public class DispositivoGrappaWeb {

	@XmlTransient
	@Hardware
	private PhysicalDevice pi;
	
	private Boolean sinalA = false;
	
	private Boolean sinalB = false;
	
	private Integer ativacoes = 0;
	
	@PinListener(addresses = 4)
	public void processarSinalA(Integer estadoPino){
		GrappaWeb.info("processando sinal A: "+ estadoPino);
		
		if(estadoPino.intValue() == 1){
			this.sinalA = !this.sinalA;
			GrappaWeb.info("estado: "+ this.sinalA);
			checarAtivacao();
		}
		
	}
	
	@PinListener(addresses = 5)
	public void processarSinalB(Integer estadoPino){
		GrappaWeb.info("processando sinal B: "+ estadoPino);
		
		if(estadoPino.intValue() == 1){
			this.sinalB = !sinalB;
			GrappaWeb.info("estado: "+ this.sinalB);
			checarAtivacao();
		}
		
	}

	private void checarAtivacao() {
		if(this.sinalA && this.sinalB){
			this.ativacoes += 1;
			GrappaInstruction resposta = this.pi.processInstruction(new LogicInstruction().address(2).write(2));
			GrappaWeb.info("ativacao: " + this.ativacoes + " - " + resposta.getResult() + ", " + resposta.getBody());
		}
	}

	public PhysicalDeviceState getEstado() {
		return pi.getState();
	}

	public GrappaInstruction processarInstrucao(GrappaInstruction comando) {
		return this.pi.processInstruction(comando);
	}
}
