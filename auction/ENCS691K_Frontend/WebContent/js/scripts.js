$('#about').show();
var serverBaseUrl = "http://localhost:8080/";
function toggleSectionDivs(id) {
	$('.section').hide();
	$('#' + id).show();
}

function submitForm(action, formId) {
	alert('d');
	alert(formId);
	var $inputs = $('#myForm :input');

    // not sure if you wanted this, but I thought I'd add it.
    // get an associative array of just the values.
    var values = {};
    $inputs.each(function() {
        values[this.name] = $(this).val();
    });
    alert(values);
    alert(serverBaseUrl + action);
	$.post(serverBaseUrl + action, values);//, registrationResults(data));
}

function registrationResults(data){
	alert(data);
}