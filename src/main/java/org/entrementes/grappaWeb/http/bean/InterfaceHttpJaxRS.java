package org.entrementes.grappaWeb.http.bean;

import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Acao;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Formato;
import org.entrementes.grappa.modelo.MapaEletrico;
import org.entrementes.grappaWeb.GrappaWeb;
import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;
import org.entrementes.grappaWeb.http.InterfaceHttp;

public class InterfaceHttpJaxRS implements InterfaceHttp{
	
	@Override
	public MapaEletrico lerEstadoGpio() {
		return GrappaWeb.carregarDispositivo().getEstado();
	}

	@Override
	public DispositivoGrappaWeb lerEstadoDispositivo() {
		return GrappaWeb.carregarDispositivo();
	}

	@Override
	public InstrucaoGrappa postarPacote(InstrucaoGrappa comando) {
		return GrappaWeb.carregarDispositivo().processarInstrucao(comando);
	}

	@Override
	public InstrucaoGrappa postarPacotePorFormulario(	Integer endereco,
														Formato formato, 
														Acao tipo, 
														Integer corpo) {
		InstrucaoGrappa requisicao = new InstrucaoGrappa(endereco, formato, tipo, corpo);
		return postarPacote(requisicao);
	}
}
