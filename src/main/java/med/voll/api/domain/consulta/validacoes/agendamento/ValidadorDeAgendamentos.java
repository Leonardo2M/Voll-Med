package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorDeAgendamentos {
	
	void validar(DadosAgendamentoConsulta dados);
}
