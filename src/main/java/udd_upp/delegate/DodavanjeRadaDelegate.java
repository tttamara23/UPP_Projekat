package udd_upp.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd_upp.converter.ListOfFormFieldsToRadConverter;
import udd_upp.dto.FieldIdNamePairDto;
import udd_upp.model.Casopis;
import udd_upp.model.NaucnaOblast;
import udd_upp.model.Rad;
import udd_upp.model.RadNaucnaOblast;
import udd_upp.service.NaucnaOblastService;
import udd_upp.service.RadNaucnaOblastService;
import udd_upp.service.RadService;

@Service
public class DodavanjeRadaDelegate implements JavaDelegate {

	@Autowired
	private ListOfFormFieldsToRadConverter converter;
	
	@Autowired
	private RadService radService;
	
	@Autowired
	private RadNaucnaOblastService radNOService;
	
	@Autowired
	private NaucnaOblastService naucnaOblastService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		NaucnaOblast naucnaOblast = null;
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("radDTO");
		Rad dodatiRad = converter.convert(dto);
		for(FieldIdNamePairDto field : dto){
			if(field.getFieldId().equals("naucnaOblast")){
				naucnaOblast = naucnaOblastService.findById(Long.parseLong(field.getFieldValue()));
			}
			if(field.getFieldId().equals("idCasopisa")){
				execution.setVariable("idCasopisaRada", Long.parseLong(field.getFieldValue()));
			}
		}
		String PID = (String) execution.getVariable("PID");
		dodatiRad.setPID(PID);
		Rad saved = radService.save(dodatiRad);
		if(naucnaOblast!=null){
			RadNaucnaOblast radNO = new RadNaucnaOblast();
			radNO.setNaucnaOblastId(naucnaOblast.getId());
			radNO.setRadId(saved.getId());
			radNOService.save(radNO);
		}
		execution.setVariable("idRada", saved.getId());
		execution.setVariable("idAutoraRada", saved.getAutor().getId().toString());
	}
}
