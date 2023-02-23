package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;

@Component
public class ValidadorMedicoInativo implements ValidadorDeAgendamentos{
	
	@Autowired
	private MedicoRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null) {
			return;
		}
		
		var medico = repository.getReferenceById(dados.idMedico());
		
		if(!medico.getAtivo()) {
			throw new ValidacaoException("Paciente inativo no sistema.");
		}
	}
}
