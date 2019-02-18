package udd_upp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udd_upp.model.Recenzija;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long>{

	List<Recenzija> findAllByIdRadaRecenzije(Long idRada);
}
