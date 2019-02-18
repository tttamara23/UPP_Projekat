package udd_upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.CasopisRecenzent;
import udd_upp.repository.CasopisRecenzentRepository;

@Service
@Transactional
public class CasopisRecenzentService {

	@Autowired
	CasopisRecenzentRepository casopisRecenzentRepository;
	
	public List<CasopisRecenzent> findByCasopisId(Long idCasopisa){
		return casopisRecenzentRepository.findAllByCasopisId(idCasopisa);
	}
}
