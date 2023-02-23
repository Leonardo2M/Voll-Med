package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorDeAgendamentos;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorDeCancelamento;
import med.voll.api.domain.medico.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendaDeConsultas {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorDeAgendamentos> validadoresAgendamento;
	
	@Autowired
	private List<ValidadorDeCancelamento> validadoresCancelamento;
	
	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!");
		}
		
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do medico informado não existe!");
		}
		
		validadoresAgendamento.forEach(v -> v.validar(dados));
		
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolherMedico(dados);
		
		if(medico == null) {
			throw new ValidacaoException("Não existe médico disponível!");
		}
		
		var consulta = new Consulta(null, medico, paciente, dados.data(),null);
		
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
		
	}
	
	public void cancelar(DadosCancelamentoConsulta dados) {
		
		if(!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("Consulta não encontrada.");
		}
		
		validadoresCancelamento.forEach(v -> v.validar(dados));		
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelar(dados.motivo());
	} 


	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatório quando médico não for escolhido!");
			
		}
		
		return medicoRepository.escolherMedicoDisponivel(dados.especialidade(), dados.data());
		
	}


}
