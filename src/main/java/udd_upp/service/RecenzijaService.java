package udd_upp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.model.Recenzija;
import udd_upp.repository.RecenzijaRepository;

@Service
@Transactional
public class RecenzijaService {
	
	@Autowired
	RecenzijaRepository recenzijaRepository;
	
	public Recenzija save(Recenzija toSave){
		return recenzijaRepository.save(toSave);
	}
	
	public List<Recenzija> findAllByRadId(Long idRada){
		return recenzijaRepository.findAllByIdRadaRecenzije(idRada);
	}
}
