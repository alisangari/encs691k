// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AuctionService/rest";

/** ************Place bids***************** */
function placeBid(id) {
	console.log('placeBid');
	$
			.ajax({
				type : 'GET',
				url : rootURL + "/auctionservice/placebid/" + id+"/"+username,
				dataType : "json", // data type of response
				success : function(data, textStatus, jqXHR) {
					if (data > 0) {
						showMsg(1, 'Bid placed successfully!');
						toggleSectionDivs('auction');
						$('#auctionMenu').click();
					} else {
						showMsg(-1, 'Bid placement failed!');
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMsg(-1, 'Product placement in auction failed! - '
							+ textStatus);
				}
			});
}



/** ************List auction products***************** */
//Register listeners
$('#auctionMenu').click(function() {
	listAuctionProds();
	return false;
});

function listAuctionProds() {
	console.log('listAuctionProds');
	$.ajax({
		type : 'GET',
		url : rootURL + "/auctionservice",
		dataType : "json", // data type of response
		success : renderAuctionList
	});
}

function renderAuctionList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#auctionProdTable tr.datarecord').remove();
	$.each(list, function(index, product) {
		bid = 0;
		if(product.basePrice > product.highestBid){
			bid = product.basePrice;
		}
		else{
			bid = product.highestBid + 5;
		}
		$('#auctionProdTable').append(
				'<tr class="datarecord"><td>' + product.name + '</td><td>'
						+ product.basePrice + '</td><td>'+product.highestBid+'</td><td onclick="placeBid('
						+ product.id + ')"><a href="#" data-identity="'
						+ product.id + '">Click to bid for '+bid+'</a></td></tr>');
	});
}


/** ************Place product in auction***************** */
function placeInAuction(id) {
	console.log('placeInAuction');
	$
			.ajax({
				type : 'GET',
				url : rootURL + "/auctionservice/placeinauction/" + id,
				dataType : "json", // data type of response
				success : function(data, textStatus, jqXHR) {
					if (data == true) {
						showMsg(1, 'Product placed in auction successfully!');
						toggleSectionDivs('my_items');
						$('#myitemsMenu').click();
					} else {
						showMsg(-1, 'Product placement in auction failed!');
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					showMsg(-1, 'Product placement in auction failed! - '
							+ textStatus);
				}
			});
}

/** ************List Products***************** */
// Register listeners
$('#myitemsMenu').click(function() {
	listProds();
	return false;
});

function listProds() {
	console.log('listProds');
	$.ajax({
		type : 'GET',
		url : rootURL + "/productservice/list/" + username,
		dataType : "json", // data type of response
		success : renderList
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#prodTable tr.datarecord').remove();
	$.each(list, function(index, product) {
		if (product.inAuction) {
			$('#prodTable').append(
					'<tr class="datarecord"><td>' + product.name + '</td><td>'
							+ product.basePrice
							+ '</td><td>Is in auction</td></tr>');
		} else {
			$('#prodTable').append(
					'<tr class="datarecord"><td>' + product.name + '</td><td>'
							+ product.basePrice
							+ '</td><td onclick="placeInAuction(' + product.id
							+ ')"><a href="#" data-identity="' + product.id
							+ '">Click to place in auction</a></td></tr>');
		}
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
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/productservice/addnew',
		dataType : "json",
		data : newprodFrmToJSON(),
		success : function(data, textStatus, jqXHR) {
			if (data == true) {
				showMsg(1, 'Product created successfully!');
				toggleSectionDivs('my_items');
				$('#myitemsMenu').click();
			}
			else{
				showMsg(-1, 'Product creation failed!');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			showMsg(-1, 'Product creation failed - ' + textStatus);
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
