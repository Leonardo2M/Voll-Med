package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;

@Component
public class ValidadorPacienteInativo implements ValidadorDeAgendamentos{
	
	@Autowired
	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var paciente = repository.getReferenceById(dados.idPaciente());
		
		if(!paciente.getAtivo()) {
			throw new ValidacaoException("Paciente inativo no sistema.");
		}
	}
}
