package org.entrementes.grappaWeb.servico.gpio;

import org.entrementes.grappa.ContextoGrappa;
import org.entrementes.grappa.ContextoGrappa.NivelLog;
import org.entrementes.grappa.gpio.ObservadorGpio;
import org.entrementes.grappa.gpio.ServicoGpio;
import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Conexao;
import org.entrementes.grappa.modelo.InstrucaoGrappa.TipoAcao;

@ObservadorGpio(endereco = 7)
public class ServicoGPIOTeste implements ServicoGpio{

	@Override
	public void processarServico(Integer estadoPino){
		String resultado = (String) ContextoGrappa.processarInstrucao(new InstrucaoGrappa(99,Conexao.REGISTRADOR,TipoAcao.LEITURA,null)).getCorpoValor();
		Integer numero = resultado == null ? 1 : Integer.parseInt(resultado);
		numero += 1;
		ContextoGrappa.getAplicacao().log("Evento pino - estado : " + estadoPino, NivelLog.INFO);
		ContextoGrappa.processarInstrucao(new InstrucaoGrappa(99,Conexao.REGISTRADOR,TipoAcao.ESCRITA,numero.toString()));
	}
	
}
