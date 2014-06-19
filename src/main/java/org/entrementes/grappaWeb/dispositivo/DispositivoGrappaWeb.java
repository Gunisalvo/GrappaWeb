package org.entrementes.grappaWeb.dispositivo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.entrementes.grappa.gpio.Raspberry;
import org.entrementes.grappa.marcacao.Dispositivo;
import org.entrementes.grappa.marcacao.Hardware;
import org.entrementes.grappa.marcacao.ObservadorGpio;
import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.MapaEletrico;
import org.entrementes.grappa.modelo.instrucao.InstrucaoLogica;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="dispositivo")
@Dispositivo(nome="dispositivo")
public class DispositivoGrappaWeb {

	@XmlTransient
	@Hardware
	private Raspberry pi;
	
	private Integer contadorBotao = 0;
	
	@ObservadorGpio(endereco = 7)
	public void processarServico(Integer estadoPino){
		if(estadoPino.intValue() == 1){
			this.contadorBotao += 1;
		}
		
		if(this.contadorBotao.intValue() % 5 == 0){
			this.pi.processarInstrucao(new InstrucaoLogica().endereco(4).escrever(1));
		}else{
			this.pi.processarInstrucao(new InstrucaoLogica().endereco(4).escrever(0));
		}
	}

	public MapaEletrico getEstado() {
		return pi.getEstado();
	}

	public InstrucaoGrappa processarInstrucao(InstrucaoGrappa comando) {
		return this.pi.processarInstrucao(comando);
	}
}
