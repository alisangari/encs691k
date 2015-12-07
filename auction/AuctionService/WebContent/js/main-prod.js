// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AuctionService/rest";


/** ************List Products***************** */
//Register listeners
$('#myitemsMenu').click(function() {
	alert("prod list");
	listProds();
	return false;
});

function listProds() {
	console.log('listProds');
	$.ajax({
		type: 'GET',
		url: rootURL+"/productservice/list/"+username,
		dataType: "json", // data type of response
		success: renderList
	});
}

function renderList(data) {
	alert(data);
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

//	$('#prodList li').remove();
	$('#prodTable tr.datarecord').remove();
	$.each(list, function(index, product) {
//		$('#prodList').append('<li>'+'<h3>'+product.name+'</h3><h2>'+product.basePrice+'</h2>'+ '<a href="#" data-identity="' + product.id + '">'+ product.name+'</a></li>');
		$('#prodTable').append('<tr class="datarecord"><td>'+product.name+'</td><td>'+product.basePrice+'</td><td><a href="#" data-identity="' + product.id + '">auction</a></td></tr>');
	});
}







/** ************Add Product***************** */
// Register listeners
$('#newprodBtn').click(function() {
	addProduct();
	return false;
});

// Functions
function addProduct() {
	console.log('addProduct');
	alert(newprodFrmToJSON());
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/productservice/addnew',
		dataType : "json",
		data : newprodFrmToJSON(),
		success : function(data, textStatus, jqXHR) {
			if (data == true) {
				alert('Product created successfully');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('addProduct error: ' + textStatus);
		}
	});
}

// Helper function to serialize all the form fields into a JSON string
function newprodFrmToJSON() {
	return JSON.stringify({
		"name" : $('#newprodName').val(),
		"basePrice" : $('#newprodBasePrice').val(),
		"ownerUsername" : username
	});
}
