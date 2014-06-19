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
import org.entrementes.grappaWeb.GrappaWeb;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="dispositivo")
@Dispositivo(nome="dispositivo")
public class DispositivoGrappaWeb {

	@XmlTransient
	@Hardware
	private Raspberry pi;
	
	private Boolean sinalA = false;
	
	private Boolean sinalB = false;
	
	private Integer ativacoes = 0;
	
	@ObservadorGpio(endereco = 4)
	public void processarSinalA(Integer estadoPino){
		GrappaWeb.info("processando sinal A: "+ estadoPino);
		
		if(estadoPino.intValue() == 1){
			this.sinalA = !this.sinalA;
			GrappaWeb.info("estado: "+ this.sinalA);
			checarAtivacao();
		}
		
	}
	
	@ObservadorGpio(endereco = 5)
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
			InstrucaoGrappa resposta = this.pi.processarInstrucao(new InstrucaoLogica().endereco(2).escrever(2));
			GrappaWeb.info("ativacao: " + this.ativacoes + " - " + resposta.getResultado() + ", " + resposta.getCorpo());
		}
	}

	public MapaEletrico getEstado() {
		return pi.getEstado();
	}

	public InstrucaoGrappa processarInstrucao(InstrucaoGrappa comando) {
		return this.pi.processarInstrucao(comando);
	}
}
