package org.entrementes.grappaWeb.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.entrementes.grappaWeb.GrappaWeb;
import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;

import br.com.caelum.grappa.model.GrappaInstruction;
import br.com.caelum.grappa.model.PhysicalDeviceState;

@WebService(targetNamespace="http://grappa.entrementes.org/",serviceName="servico-grappa")
@SOAPBinding(style=Style.DOCUMENT)
public class ServicoGrappa {
	
	@WebMethod(operationName="gpio")
	@WebResult(name="gpio",targetNamespace="http://grappa.entrementes.org/")
	public PhysicalDeviceState lerEstadoGpio(){
		return GrappaWeb.carregarDispositivo().getEstado();
	}
	
	@WebMethod(operationName="dispositivo")
	@WebResult(name="dispositivo",targetNamespace="http://grappa.entrementes.org/")
	public DispositivoGrappaWeb lerEstadoDispositivo(){
		return GrappaWeb.carregarDispositivo();
	}
	
	@WebMethod(operationName="processarInstrucao")
	@WebResult(name="grappa", targetNamespace="http://grappa.entrementes.org/")
	public GrappaInstruction postarPacote(@WebParam(name="grappa", targetNamespace="http://grappa.entrementes.org/") GrappaInstruction comando){
		return GrappaWeb.carregarDispositivo().processarInstrucao(comando);
	}

}
