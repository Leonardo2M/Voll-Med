package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidarMedicoOutraConsulta implements ValidadorDeAgendamentos{
	
	@Autowired
	private ConsultaRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {
		if (repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data())) {
			throw new ValidacaoException("Médico já possui outra consulta");
		}
	}
}
