package udd_upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udd_upp.model.CasopisNaucnaOblast;

@Repository
public interface CasopisNaucnaOblastRepository extends JpaRepository<CasopisNaucnaOblast, Long> {

	
	
}
