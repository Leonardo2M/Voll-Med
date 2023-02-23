package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorDeCancelamento {
	
	void validar(DadosCancelamentoConsulta dados);
	
}
