package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.voll.api.paciente.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	Page<Paciente> findAllByAtivoTrue(Pageable page);
}
