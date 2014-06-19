package org.entrementes.grappaWeb.http;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Acao;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Formato;
import org.entrementes.grappa.modelo.MapaEletrico;
import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;

@Path("/")
public interface InterfaceHttp {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("dispositivo")
	public DispositivoGrappaWeb lerEstadoDispositivo();

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("gpio")
	public MapaEletrico lerEstadoGpio();
	
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("log")
	public Response lerLog();

	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("resultado-instrucao")
	public InstrucaoGrappa postarPacote(InstrucaoGrappa comando);
	
	@POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("resultado-instrucao.xml")
	public InstrucaoGrappa postarPacotePorFormulario(	@FormParam("endereco") Integer endereco,
														@FormParam("formato") Formato formato, 
														@FormParam("acao") Acao tipo,
														@FormParam("corpo") Integer corpo);

}
