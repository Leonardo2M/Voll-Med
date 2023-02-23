package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	Page<Medico> findAllByAtivoTrue(Pageable page);
	
	@Query("""
			SELECT m from Medico m
			WHERE 
			m.ativo = 1
			AND
			m.especialidade = :especialidade
			AND
			m.id NOT IN(
				SELECT c.medico.id from Consulta c
				WHERE
				c.data = :data
				AND
				c.motivoCancelamento is null)
			ORDER BY rand()
			LIMIT 1
			""")
	
	Medico escolherMedicoDisponivel(Especialidade especialidade, LocalDateTime data);

}
