package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosListagemPacientes;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteDTO;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
		
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid PacienteDTO dados) {
		pacienteRepository.save(new Paciente(dados));
	}
	
	@GetMapping
	public Page<DadosListagemPacientes> listar(Pageable page){
		return pacienteRepository.findAllByAtivoTrue(page).map(DadosListagemPacientes::new);
	}
	
	@PutMapping
	@Transactional
	public void alterar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		paciente.excluir();
	}
}
