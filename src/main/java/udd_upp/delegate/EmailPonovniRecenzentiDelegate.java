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
import udd_upp.service.RadService;

@Service
public class EmailPonovniRecenzentiDelegate implements JavaDelegate{
	@Autowired
	private RadService radService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
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
		emailService.getMail().setTo(glavniUrednik.getEmail());
		
		emailService.getMail().setSubject("Kasnjenje pri recenziranju");
		emailService.getMail().setText("Postovani, \n\n Odabrani recenzenti nisu dali recenzije u roku."
				+ " Potrebno je odabrati nove recenzente."
				+ "\n Casopis: \"" + casopisRada.getNaziv() + "\", ciji je glavni urednik "
						+ glavniUrednik.getIme() + " " + glavniUrednik.getPrezime() + ".\n"
								+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
						+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
			+".\n\n NC Admin");
		emailService.sendNotificaitionSync(glavniUrednik);
	}
	

}
