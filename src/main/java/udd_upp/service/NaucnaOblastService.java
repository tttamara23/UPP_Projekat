package udd_upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.NaucnaOblast;
import udd_upp.repository.NaucnaOblastRepository;

@Service
@Transactional
public class NaucnaOblastService {

	@Autowired
	NaucnaOblastRepository naucnaOblastRepository;

	public List<NaucnaOblast> findAll(){
		return naucnaOblastRepository.findAll();
	}
	
	public NaucnaOblast findById(Long id){
		return naucnaOblastRepository.findById(id).get();
	}
}
