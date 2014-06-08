package org.entrementes.grappaWeb.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.entrementes.grappa.ContextoGrappa;
import org.entrementes.grappa.gpio.BarramentoGpio;
import org.entrementes.grappa.modelo.MapaEletrico;
import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.RegistradoresGrappa;
import org.entrementes.grappa.registradores.BarramentoRegistradores;

@WebService(targetNamespace="http://grappa.entrementes.org/",serviceName="servico-grappa")
@SOAPBinding(style=Style.DOCUMENT)
public class ServicoGrappa {
	
	@WebMethod(operationName="mapaRegistradores")
	@WebResult(name="registradores",targetNamespace="http://grappa.entrementes.org/")
	public RegistradoresGrappa lerMapaRegistradores(){
		return BarramentoRegistradores.getBarramento().getEstado();
	}
	
	@WebMethod(operationName="mapaGPIO")
	@WebResult(name="gpio",targetNamespace="http://grappa.entrementes.org/")
	public MapaEletrico lerEstadoGpio(){
		return BarramentoGpio.getBarramento().getEstado();
	}
	
	@WebMethod(operationName="processarPacote")
	@WebResult(name="grappa", targetNamespace="http://grappa.entrementes.org/")
	public InstrucaoGrappa postarPacote(@WebParam(name="grappa", targetNamespace="http://grappa.entrementes.org/") InstrucaoGrappa comando){
		return ContextoGrappa.processarInstrucao(comando);
	}

}
