package udd_upp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.Koautor;
import udd_upp.repository.KoautorRepository;

@Service
@Transactional
public class KoautorService {
	
	@Autowired
	KoautorRepository koautorRepository;
	
	public void save(Koautor k){
		koautorRepository.save(k);
	}
}
