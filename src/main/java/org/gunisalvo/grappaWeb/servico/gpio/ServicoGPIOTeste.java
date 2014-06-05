package org.gunisalvo.grappaWeb.servico.gpio;

import org.gunisalvo.grappa.Grappa;
import org.gunisalvo.grappa.Grappa.NivelLog;
import org.gunisalvo.grappa.gpio.GPIOListener;
import org.gunisalvo.grappa.gpio.ServicoGpio;
import org.gunisalvo.grappa.modelo.PacoteGrappa;
import org.gunisalvo.grappa.modelo.PacoteGrappa.Conexao;
import org.gunisalvo.grappa.modelo.PacoteGrappa.TipoAcao;

@GPIOListener(pino = 7)
public class ServicoGPIOTeste implements ServicoGpio{

	@Override
	public void processarServico(Integer estadoPino){
		String resultado = (String) Grappa.processarPacote(new PacoteGrappa(99,Conexao.REGISTRADOR,TipoAcao.LEITURA,null)).getCorpoValor();
		Integer numero = resultado == null ? 1 : Integer.parseInt(resultado);
		numero += 1;
		Grappa.getAplicacao().log("Evento pino - estado : " + estadoPino, NivelLog.INFO);
		Grappa.processarPacote(new PacoteGrappa(99,Conexao.REGISTRADOR,TipoAcao.ESCRITA,numero.toString()));
	}
	
}
