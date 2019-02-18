$(document).ready(function () {
	/*var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PInsId");*/
	var process = "Registracija";
	$.ajax({
		async: false,
		url: "http://localhost:4242/user/startProcess/"+process,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	divGlavni = $('#formaRegistracija');
        	var str="";
        	
        	for(i=0;i<data.formField.length;i++){
        		var tipPolja = "text";
        		if(data.formField[i].label === "Lozinka"){
        			tipPolja = "password";
        		}
        		str+="<div class=\"form-group\">";
        		str+="<label class=\"sr-only\" >"+data.formField[i].label+"</label>";
        			str+="<input type=\"" + tipPolja +"\" placeholder="+data.formField[i].label+" class=\"form-first-name form-control\" id="+data.formField[i].id+">";
        		
        		
        		str+="</div>";
        		divGlavni.append(str);
        		str="";
        	}
        	str+=" <button type=\"button\" onclick=\"buttonRegisterClick()\" class=\"btn\">Registruj me</button>";
        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
			str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
			
        	divGlavni.append(str);
        	
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	
	
})

function buttonRegisterClick(){
	taskId = $("#taskId").val();
	processInstanceId = $('#processInstanceId').val();
	email = $('#email').val();
	lozinka = $('#lozinka').val();
	//var res = email.match("^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$");
	var emailCheck = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
	var res = email.match(emailCheck);
	grad = $('#grad').val();
	drzava = $('#drzava').val();
	ime = $('#ime').val();
	prezime = $('#prezime').val();
	korisnickoIme = $('#korisnickoIme').val();
	
	/*var regexp = /^[A-Z]/;
	var resName = ime.match(regexp);
	var resSurName = last_name.match(regexp);
	var resCity = city.match(regexp);
	var resCountry = country.match(regexp);*/
	
	
	if(!ime || !prezime || !email || !lozinka || !grad || !drzava) {
		toastr["error"]('Popunite sva polja!');
		return;
	}
	
	var data = JSON.stringify([
			{"fieldId":"ime",
			"fieldValue":ime},
			{"fieldId":"prezime",
				"fieldValue":prezime},
			{"fieldId":"email",
				"fieldValue":email},
			{"fieldId":"lozinka",
				"fieldValue":lozinka},
			{"fieldId":"drzava",
				"fieldValue":drzava},
			{"fieldId":"grad",
				"fieldValue":grad},
			{"fieldId":"korisnickoIme",
				"fieldValue":korisnickoIme}]
				
	);
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/user/registerUser/"+taskId,
        type: "POST",
        contentType:"application/json",
        data : data,
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data1) {
        	top.location.href = "logIn.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	
	
}




