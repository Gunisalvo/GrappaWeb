package org.entrementes.grappaWeb.http.bean;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.entrementes.grappaWeb.GrappaWeb;
import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;
import org.entrementes.grappaWeb.http.GrappaInstructionBody;
import org.entrementes.grappaWeb.http.InterfaceHttp;

import br.com.caelum.grappa.model.GrappaInstruction;
import br.com.caelum.grappa.model.GrappaInstruction.Action;
import br.com.caelum.grappa.model.GrappaPin.PinFormat;
import br.com.caelum.grappa.model.PhysicalDeviceState;

public class InterfaceHttpJaxRS implements InterfaceHttp{
	
	@Override
	public PhysicalDeviceState lerEstadoGpio() {
		return GrappaWeb.carregarDispositivo().getEstado();
	}

	@Override
	public DispositivoGrappaWeb lerEstadoDispositivo() {
		return GrappaWeb.carregarDispositivo();
	}

	@Override
	public GrappaInstruction postarPacote(GrappaInstruction comando) {
		return GrappaWeb.carregarDispositivo().processarInstrucao(comando);
	}

	@Override
	public GrappaInstruction postarPacotePorFormulario(	Integer endereco,
														PinFormat formato, 
														Action tipo, 
														GrappaInstructionBody corpo) {
		GrappaInstruction requisicao = new GrappaInstruction(endereco, formato, tipo, corpo.getValue());
		return postarPacote(requisicao);
	}

	@Override
	public Response lerLog() {
		return Response.ok(GrappaWeb.getConteudoLog(), MediaType.TEXT_PLAIN).build();
	}
}
