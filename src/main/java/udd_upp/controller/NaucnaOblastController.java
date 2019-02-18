package udd_upp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import udd_upp.converter.NaucnaOblastToNaucnaOblastDTOConverter;
import udd_upp.dto.CasopisDTO;
import udd_upp.dto.NaucnaOblastDTO;
import udd_upp.model.Casopis;
import udd_upp.model.NaucnaOblast;
import udd_upp.service.NaucnaOblastService;

@RestController
@RequestMapping(value = "/naucnaOblast")
public class NaucnaOblastController {

	@Autowired
	NaucnaOblastService naucnaOblastService;
	
	@Autowired
	NaucnaOblastToNaucnaOblastDTOConverter converter;
	
	@RequestMapping(
            value = "/getAll",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
		List<NaucnaOblast> sveNO = naucnaOblastService.findAll();
		if(sveNO == null) {
			return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
		}
		List<NaucnaOblastDTO> retVal = converter.convertList(sveNO);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
