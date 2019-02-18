package udd_upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import udd_upp.model.Rad;
import udd_upp.repository.KorisnikNaucnaOblastRepository;
import udd_upp.repository.RadRepository;

@Service
@Transactional
public class KorisnikNaucnaOblastService {

	@Autowired
	KorisnikNaucnaOblastRepository korisnikNaucnaOblastRepository;
	
}
