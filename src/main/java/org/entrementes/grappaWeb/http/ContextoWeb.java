package org.entrementes.grappaWeb.http;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.entrementes.grappaWeb.GrappaWeb;
import org.entrementes.grappaWeb.dispositivo.DispositivoGrappaWeb;

import br.com.caelum.grappa.context.GrappaContext;
import br.com.caelum.grappa.context.ServletBoundContext;

@WebListener
public class ContextoWeb implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent evento) {
		ServletContext contexto = evento.getServletContext();
		GrappaContext grappa = (GrappaContext) contexto.getAttribute("grappa");
		grappa.getPhysicalDevice().shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent evento) {
		ServletContext contexto = evento.getServletContext();
		GrappaContext grappa = new ServletBoundContext(contexto);
		GrappaWeb.construir((DispositivoGrappaWeb)grappa.getDevices().get("dispositivo"));
	}

}
