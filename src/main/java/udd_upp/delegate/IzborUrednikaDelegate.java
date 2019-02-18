package udd_upp.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.model.Casopis;
import udd_upp.model.Korisnik;
import udd_upp.model.Rad;
import udd_upp.service.CasopisService;
import udd_upp.service.EmailService;
import udd_upp.service.KorisnikService;
import udd_upp.service.NaucnaOblastService;
import udd_upp.service.RadNaucnaOblastService;
import udd_upp.service.RadService;

@Service
public class IzborUrednikaDelegate implements JavaDelegate{

	@Autowired
	RadService radService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	RadNaucnaOblastService radNOService;
	
	@Autowired
	NaucnaOblastService noService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long radId = (Long) execution.getVariable("idRada");
		
		Long idCasopisa = (Long) execution.getVariable("idCasopisaRada");
		Casopis casopisRada = casopisService.findById(idCasopisa);
		Rad rad = radService.findOne(radId);
		Korisnik autorRada = rad.getAutor();
		Korisnik glavniUrednik = null;
		List<Korisnik> uredniciCasopisa = korisnikService.findByCasopisId(casopisRada.getId());
		for(Korisnik k : uredniciCasopisa){
			if(k.getIsGlavni()){
				glavniUrednik = k;
				break;
			}
		}
		if(glavniUrednik!=null){
			emailService.getMail().setTo(glavniUrednik.getEmail());
			
			emailService.getMail().setSubject("Notifikacija o novom radu");
			emailService.getMail().setText("Postovani, \n\n Novi rad je prijavljen."
					+ " Izvrsite izbor recenzenata rada."
					+ "\n \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
							+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
				+".\n\n NC Admin");
			emailService.sendNotificaitionSync(glavniUrednik);
		}
	}

}
