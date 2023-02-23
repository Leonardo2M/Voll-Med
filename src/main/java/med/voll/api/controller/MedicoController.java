package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoDTO;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		medicoRepository.save(medico);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); // código 201
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable page) {
		var pages = medicoRepository.findAllByAtivoTrue(page).map(DadosListagemMedico::new);

		return ResponseEntity.ok(pages); // código 200
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@PutMapping
	@Transactional
	public ResponseEntity alterar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); // código 200 com corpo
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build(); // código 204
	}

}
