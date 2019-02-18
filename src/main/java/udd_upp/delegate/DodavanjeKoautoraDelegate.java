package udd_upp.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.converter.ListOfFormFieldsToKorisnikConverter;
import udd_upp.dto.FieldIdNamePairDto;
import udd_upp.model.Koautor;
import udd_upp.model.Korisnik;
import udd_upp.service.KoautorService;
import udd_upp.service.KorisnikService;

@Service
public class DodavanjeKoautoraDelegate implements JavaDelegate  {

	@Autowired
	private KoautorService koautorService;
	
	@Autowired
	private ListOfFormFieldsToKorisnikConverter converter;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("koautor");
		for(FieldIdNamePairDto pair: dto){
			if(pair.getFieldId().equals("josKoautora")){
				execution.setVariable("josKoautora", pair.getFieldValue());
				break;
			}
		}
		Korisnik user = converter.convert(dto);
		Koautor noviKoautor = new Koautor();
		noviKoautor.setDrzava(user.getDrzava());
		noviKoautor.setGrad(user.getGrad());
		noviKoautor.setIme(user.getIme());
		noviKoautor.setPrezime(user.getPrezime());
		noviKoautor.setEmail(user.getEmail());
		Long idRada = (Long) execution.getVariable("idRada");
		noviKoautor.setIdRada(idRada);
		Korisnik ulogovani = korisnikService.getCurrentUser();
		noviKoautor.setIdAutora(ulogovani.getId());
		koautorService.save(noviKoautor);
	}

}
