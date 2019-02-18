$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PID");
	var naucneOblasti;
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var idRada = url.searchParams.get("idRada");
	
	var divPdf = $('#divPdf');
	divPdf.append("<h3 style=\"margin-left:15%\"> ovde sad kao on vidi pdf... :) </h3>");
	divPdf.append("<button style=\"margin-left:15%\" class=\"btn btn-primary\" id=\"prihvatiRad\" onclick=\"prihvatiRad("+idRada + ",\'" + PInsId + "\')\">Prihvati</button>");
	divPdf.append("<button style=\"margin-left:5%\" class=\"btn btn-primary\" onclick=\"korekcijaFormata("+idRada + ",\'" + PInsId + "\')\">Korekcija formata</button>");
	
});

function prihvatiRad(idRada,PID){
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/prihvatiRad/"+PID+"/radId/"+idRada,
        type: "GET", 
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function () {
        	toastr["success"]("Rad je prihvacen i poslat na recenziju.");
        	top.location.href="homePageGlavniUrednik.html";
		},
		error: function (jqxhr, textStatus, errorThrown) {
		    toastr['error']('Ne radi');
		} 
	});
	
}

function korekcijaFormata(idRada,PID){
	$.ajax({
		async: false,
		url: "http://localhost:4242/rad/korekcijaFormata/"+PID+"/radId/"+idRada,
        type: "GET", 
        crossDomain: true,
        withCredentials: true,
        headers: {  'Access-Control-Allow-Origin': '*' },
        success: function () {
        	top.location.href="homePageGlavniUrednik.html";
		},
		error: function (jqxhr, textStatus, errorThrown) {
		    toastr['error']('Ne radi');
		} 
	});
	
}