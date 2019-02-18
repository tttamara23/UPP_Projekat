package udd_upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udd_upp.model.RadNaucnaOblast;

@Repository
public interface RadNaucnaOblastRepository extends JpaRepository<RadNaucnaOblast, Long> {
	RadNaucnaOblast findByRadId(Long idRada);
}
