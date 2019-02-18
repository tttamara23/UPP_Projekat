$(document).ready(function () {


})

function ispravljeniRadovi(){
	top.location.href = "ispravljeniRadovi.html";
	   
}

function addArticle(idMagazine){
	/*var url_string = window.location.href;
	var url = new URL(url_string);
	var PInsId = url.searchParams.get("PInsId");*/
	
		var process = "Process_1";
		$.ajax({
			async: false,
			url: "http://localhost:1555/user/startProcess/"+process,
	        type: "GET",
	        dataType:"json",
	        crossDomain: true,
	        withCredentials: true,
	        headers: {  'Access-Control-Allow-Origin': '*' },
	        success: function (data) {
				$.ajax({
					async: false,
					url: "http://localhost:1555/article/addArticleForm/"+data.processInstanceId+"/"+idMagazine,
			        type: "GET",
			        crossDomain: true,
			        withCredentials: true,
			        headers: {  'Access-Control-Allow-Origin': '*' },
			        success: function () {
			        	top.location.href = "addArticle.html?idMagazine="+idMagazine+"&PID="+data.processInstanceId;
			        },
			        error: function (jqxhr, textStatus, errorThrown) {
			        	toastr['error']('Ne radi');
			        } 
			        });
	        },
	        error: function (jqxhr, textStatus, errorThrown) {
	        	toastr['error']('Ne radi');
	        } 
	        });
}