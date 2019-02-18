package udd_upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.Rad;
import udd_upp.repository.RadRepository;

@Service
@Transactional
public class RadService {

	@Autowired
	RadRepository radRepository;
	
	public Rad findOne(Long id){
		return radRepository.findById(id).get();
	}
	
	public Rad save(Rad toSave){
		return radRepository.save(toSave);
	}
	
	public List<Rad> findAllByCasopisId(Long id) {
		return radRepository.findAllByCasopisId(id);
	}
	
	public List<Rad> findAllByAutorId(Long autorId){
		return radRepository.findAllByAutorId(autorId);
	}
}
