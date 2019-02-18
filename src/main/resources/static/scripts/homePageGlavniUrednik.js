$(document).ready(function () {
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/getLoggedIn",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	if(data!=null) {
        		var naslovKorisnikDiv = $('#naslovKorisnikDiv');
        		naslovKorisnikDiv.append("<h3 id=\"naslovKorisnik\">Zdravo, " + data.ime + "</h3>");
        	} else {
        		top.location.href = "home.html";
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/getAllByGlavniUrednik",
        type: "GET",
        dataType: "json",
        success: function (data) {
    	        	if(data!=null) {
    	        		var tabelaRadovi = $('#tabelaRadovi');
    	        		var divRadovi = $('#divRadovi');
    	        		for(i=0;i<data.length;i++){
    	        			if(data[i].statusRada == "PODNET" || data[i].statusRada == "KOREKCIJA_FORMAT"){
    	        				tabelaRadovi.append('<tr><td>'+data[i].naslov+'</td><td>'+data[i].apstrakt+'</td><td>'+data[i].kljucniPojmovi+'</td><td><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"tematskiPrihvatljiv('+data[i].id + ',\'' + data[i].pid + '\')\">Tematski prihvatljiv</button><button style=\"margin-left:15%\" class=\"btn btn-primary\" onclick=\"odbij('+data[i].id + ",\'" + data[i].pid+ '\')\">Odbij</button></td></tr>');
    	        				divRadovi.append(tabelaRadovi);
    	        			}
    	    	        	}
    	        	} else {
    	        		toastr["error"]('nije ok');
    	        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/getTaskovi",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	var divTaskovi = $('#divTaskovi');
        	divTaskovi.append("<h2 style=\"margin-left:15%\"> TASKOVI: </h2>");
        	for(i=0;i<data.length;i++){
        		divTaskovi.append("<button style=\"margin-left:15%\" onclick=\"task(\'"+data[i].id+"\',\'" + data[i].name+ "\')\" class=\"btn btn-primary\">"+data[i].name+"</button>");
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
})

function task(taskId,taskName){
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/getRadDTO/"+taskId,
        type: "GET",
        dataType:"json",
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function (data) {
        	if(taskName=="Konacna odluka"){
	        	divGlavni = $('#divTaskovi');
	        	var str="";	
	        	divGlavni.append("<h3 style=\"margin-left:15%;\">Naslov rada: "+data[0].naslovRada+"</h3>");
	        	divGlavni.append("<h4 style=\"margin-left:15%;\">Autor rada: "+data[0].autorRada+"</h4>");
	        	
	        	for(i=0;i<data.length;i++){	        		
	        		divGlavni.append("<h4 style=\"margin-left:15%;\">Recenzija: \'"+i+"\'</h4>");
	        		divGlavni.append("<p style=\"margin-left:15%;\">Autor recenzije: "+data[i].autorRecenzije+"</p>");
	        		divGlavni.append("<p style=\"margin-left:15%;\">Komentar recenzije: "+data[i].komentarRecenzije+"</p>");	
	        	}
	        	
	        	str+=" <button class=\"btn btn-primary\" style=\"margin-left:15%;\" onclick=\"prihvatiRad(\'"+taskId + "\')\" class=\"btn\">Prihvati</button>";
	        	str+=" <button class=\"btn btn-primary\" onclick=\"odbijRad(\'"+taskId + "\')\" class=\"btn\">Odbij</button>";
	        	str+=" <button class=\"btn btn-primary\" onclick=\"vecaKorekcija(\'"+taskId + "\')\" class=\"btn\">Veca korekcija</button>";
	        	str+=" <button class=\"btn btn-primary\" onclick=\"manjaKorekcija(\'"+taskId + "\')\" class=\"btn\">Manja korekcija</button>";
	        	
	        	str+=" <button class=\"btn btn-primary\" onclick=\"vratiNovimRecenzentima(\'"+taskId + "\')\" class=\"btn\">Ponovno recenziranje</button>";
	        	
	        	
	        	str+="<input type=\"hidden\" id=\"taskId\" name=\"taskId\" value=\""+data.taskId+"\">";
				str+="<input type=\"hidden\" id=\"processInstanceId\" name=\"processInstanceId\" value=\""+data.processInstanceId+"\">";
				
	        	divGlavni.append(str);
        	}else if(taskName == "Izbor recenzenata"){
        		top.location.href="odaberiRecenzente.html?taskId="+taskId;
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr['error']('Ne radi');
        } 
        });	
}
function prihvatiRad(taskId){
		
		$.ajax({
			async: false,
			url: "http://localhost:4242/korisnik/prihvatiRadKonacno/"+taskId,
	        type: "POST",
	        success: function () {
	        	toastr["success"]("Rad prihvacen!");
	        	top.location.href="homePageGlavniUrednik.html";
	        },
	        error: function (jqxhr, textStatus, errorThrown) {
	        	toastr["error"]('nije ok');              
	        }
	    });
}
function odbijRad(taskId){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/odbijRadKonacno/"+taskId,
        type: "POST",
        success: function () {
        	toastr["success"]("Rad odbijen!");
        	top.location.href="homePageGlavniUrednik.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
}

function vecaKorekcija(taskId){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/vecaKorekcija/"+taskId,
        type: "POST",
        success: function () {
        	toastr["success"]("Rad poslat na vecu korekciju!");
        	top.location.href="homePageGlavniUrednik.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
}


function manjaKorekcija(taskId){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/manjaKorekcija/"+taskId,
        type: "POST",
        success: function () {
        	toastr["success"]("Rad poslat na manju korekciju!");
        	top.location.href="homePageGlavniUrednik.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
}

function vratiNovimRecenzentima(taskId){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/noviRecenzenti/"+taskId,
        type: "POST",
        success: function () {
        	toastr["success"]("Poslato na ponovno biranje recenzenata!");
        	top.location.href="homePageGlavniUrednik.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	toastr["error"]('nije ok');              
        }
    });
}

function odbij(idRada,PID){
		var process = "DodavanjeRada";
		$.ajax({
			async: false,
			url: "http://localhost:4242/rad/odbijRad/"+PID+"/radId/"+idRada,
	        type: "GET", 
	        crossDomain: true,
	        withCredentials: true,
	        headers: {  'Access-Control-Allow-Origin': '*' },
	        success: function () {
	        	toastr["success"]('Rad odbijen.');
			},
			error: function (jqxhr, textStatus, errorThrown) {
			    toastr['error']('Ne radi');
			} 
		});
    	
}

function tematskiPrihvatljiv(idRada,PID){
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/tematskiPrihvatljiv/"+PID,
        type: "GET", 
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function () {
        	
        	top.location.href="pregledPdf.html?PID="+PID+"&idRada="+idRada;
		},
		error: function (jqxhr, textStatus, errorThrown) {
		    toastr['error']('Ne radi');
		} 
	});
	
}

function logout(){
	
	$.ajax({
		async: false,
		url: "http://localhost:4242/korisnik/logout",
        type: "GET",
        dataType: "json",
        success: function (data) {
        	top.location.href = "home.html";
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
	
}