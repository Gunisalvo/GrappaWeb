package org.entrementes.grappaWeb.servico.registrador;

import org.entrementes.grappa.ContextoGrappa;
import org.entrementes.grappa.ContextoGrappa.NivelLog;
import org.entrementes.grappa.modelo.InstrucaoGrappa;
import org.entrementes.grappa.modelo.InstrucaoGrappa.Conexao;
import org.entrementes.grappa.modelo.InstrucaoGrappa.TipoAcao;
import org.entrementes.grappa.registradores.ObservadorRegistrador;
import org.entrementes.grappa.registradores.ServicoRegistrador;

@ObservadorRegistrador(endereco=99)
public class ServicoRegistradorTeste implements ServicoRegistrador{

	private int posicao = 0;
	
	@Override
	public void processarServico(Object valorEndereco) {
		try{
			String resultado = valorEndereco.toString();
			Integer numero = resultado == null ? 0 : Integer.parseInt(resultado);
			int atual = numero / 10;
			if(atual > posicao){
				this.posicao = atual;
				ContextoGrappa.processarInstrucao(new InstrucaoGrappa(4, Conexao.GPIO, TipoAcao.ESCRITA, "2"));
				ContextoGrappa.getAplicacao().log("Evento registrador, valor: " + numero + " mundando voltagem pino 4", NivelLog.INFO);
			}else{
				ContextoGrappa.getAplicacao().log("Evento registrador, valor: " + numero , NivelLog.INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
