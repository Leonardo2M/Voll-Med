package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.voll.api.domain.consulta.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	boolean existsByPacienteIdAndData(Long idPaciente, LocalDateTime data);
	
	boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

}
