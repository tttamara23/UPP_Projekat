package udd_upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.Rad;
import udd_upp.model.RadNaucnaOblast;
import udd_upp.repository.RadNaucnaOblastRepository;
import udd_upp.repository.RadRepository;

@Service
@Transactional
public class RadNaucnaOblastService {

	@Autowired
	RadNaucnaOblastRepository naucnaOblastRepository;
	
	public void save(RadNaucnaOblast radNO){
		naucnaOblastRepository.save(radNO);
	}

	public RadNaucnaOblast findByRad(Long idRada){
		return naucnaOblastRepository.findByRadId(idRada);
	}
	
}
