package org.entrementes.grappaWeb.http;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.entrementes.grappaWeb.http.bean.InterfaceHttpJaxRS;

@ApplicationPath(value="/v1")
public class RaizHttp extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(InterfaceHttpJaxRS.class));
	}

}
