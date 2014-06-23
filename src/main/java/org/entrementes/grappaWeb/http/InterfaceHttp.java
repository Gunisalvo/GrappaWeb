package org.entrementes.grappaWeb.http;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;

import br.com.caelum.grappa.model.GrappaInstruction;
import br.com.caelum.grappa.model.GrappaInstruction.Action;
import br.com.caelum.grappa.model.GrappaPin.PinFormat;
import br.com.caelum.grappa.model.PhysicalDeviceState;

@Path("/")
public interface InterfaceHttp {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("dispositivo")
	public DispositivoGrappaWeb lerEstadoDispositivo();

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("gpio")
	public PhysicalDeviceState lerEstadoGpio();
	
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("log")
	public Response lerLog();

	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("resultado-instrucao")
	public GrappaInstruction postarPacote(GrappaInstruction comando);
	
	@POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("resultado-instrucao.xml")
	public GrappaInstruction postarPacotePorFormulario(	@FormParam("address") Integer endereco,
														@FormParam("format") PinFormat formato, 
														@FormParam("action") Action tipo,
														@FormParam("body") GrappaInstructionBody corpo);

}
