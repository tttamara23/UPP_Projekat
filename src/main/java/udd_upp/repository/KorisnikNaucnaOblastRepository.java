package udd_upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udd_upp.model.KorisnikNaucnaOblast;

@Repository
public interface KorisnikNaucnaOblastRepository extends JpaRepository<KorisnikNaucnaOblast, Long> {

	
}
