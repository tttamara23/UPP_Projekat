package udd_upp.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.converter.ListOfFormFieldsToKorisnikConverter;
import udd_upp.dto.FieldIdNamePairDto;
import udd_upp.model.Korisnik;
import udd_upp.model.Uloga;
import udd_upp.service.KorisnikService;

@Service
public class RegistracijaKorisnikaDelegate implements JavaDelegate {
	
	@Autowired
	private ListOfFormFieldsToKorisnikConverter registerConverter;
	
	@Autowired
	private KorisnikService userService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("registerData");
		Korisnik kreiraniKorisnik = registerConverter.convert(dto);
		kreiraniKorisnik.setUloga(Uloga.AUTOR);
		Korisnik saved = null;
		if(kreiraniKorisnik !=null){
			 saved = userService.save(kreiraniKorisnik);
		}
		
	}

}
