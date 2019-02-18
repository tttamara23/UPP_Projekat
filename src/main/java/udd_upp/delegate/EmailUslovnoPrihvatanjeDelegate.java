package udd_upp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.model.Casopis;
import udd_upp.model.Korisnik;
import udd_upp.model.Rad;
import udd_upp.model.StatusRada;
import udd_upp.service.CasopisService;
import udd_upp.service.EmailService;
import udd_upp.service.RadService;

@Service
public class EmailUslovnoPrihvatanjeDelegate implements JavaDelegate {
	@Autowired
	private RadService radService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long idRada = (Long) execution.getVariable("idRada");
		Long idCasopisa = (Long) execution.getVariable("idCasopisaRada");
		Casopis casopis = casopisService.findById(idCasopisa);
		Rad rad = radService.findOne(idRada);
		Korisnik autorRada = rad.getAutor();
	
		emailService.getMail().setTo(autorRada.getEmail());
		
		emailService.getMail().setSubject("Rad prihvacen");
		emailService.getMail().setText("Postovani, Vas rad je uslovno prihvacen"
				+ ". Potrebno je da ga ispravite u zadatom roku. Rad je za casopis: "
				+ casopis.getNaziv()
				+ ".\n \n"
								+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
						+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
			+".\n\n NC Admin");
		emailService.sendNotificaitionSync(autorRada);
		
	}
	
}
