// The root URL for the RESTful services
var rootURL = "http://localhost:8080/AuctionService/rest";

var username;
var password;
if (username == "" || username != 'undefined') {
	$('#loginMenu').show();
	$('#logoutMenu').hide();
	$('#closeaccountMenu').hide();
	$('#registerMenu').show();
	$('#myitemsMenu').hide();

} else {
	$('#loginMenu').hide();
	$('#logoutMenu').show();
	$('#closeaccountMenu').show();
	$('#registerMenu').hide();
	$('#myitemsMenu').show();

}

/** ***************Close Account********************* */
// Register listeners
$('#closeaccountMenu').click(function() {
	closeAccount();
	return false;
});

// Functions
function closeAccount() {
	console.log('closeAccount');
	$.ajax({
		type : 'DELETE',
		contentType : 'application/json',
		url : rootURL + '/userservice/delete' + '/' + username,
		success : function(data, textStatus, jqXHR) {
			if (data == true) {
				showMsg(1,'User account closed successfully');
				username = "";
				password = "";
				$('#loginMenu').show();
				$('#logoutMenu').hide();
				$('#closeaccountMenu').hide();
				$('#registerMenu').show();
				$('#myitemsMenu').hide();
			}
			else{
				showMsg(-1, 'User account closure failed!');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
//			alert('closeAccount error: ' + textStatus);
			showMessage(-1, 'User account closure failed! - '+textStatus);
		}
	});
}

/** ***************User Logout********************* */
// Register listeners
$('#logoutMenu').click(function() {
	username = "";
	password = "";
	$('#loginMenu').show();
	$('#logoutMenu').hide();
	$('#closeaccountMenu').hide();
	$('#registerMenu').show();
	$('#myitemsMenu').hide();
	location.reload();
	return false;
});

/** ***************User Login********************* */
// Register listeners
$('#loginBtn').click(function() {
	userLogin();
	return false;
});

// Functions
function userLogin() {
	console.log('userLogin');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/userservice/login',
		dataType : "json",
		data : loginFrmToJSON(),
		success : function(data, textStatus, jqXHR) {
			if (data == true) {
//				alert('User logged in successfully');
				username = $('#loginUsername').val();
				password = $('#loginPassword').val();
				$('#loginUsername').val("");
				$('#loginPassword').val("");
				$('#loginMenu').hide();
				$('#logoutMenu').show();
				$('#closeaccountMenu').show();
				$('#registerMenu').hide();
				$('#myitemsMenu').show();
				showMsg(1, 'User logged in successfully!');
				toggleSectionDivs('my_items');
				$('#myitemsMenu').click();
			}
			else{
				showMsg(-1, 'Login failed!');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
//			alert('userLogin error: ' + textStatus);
			showMsg(-1, 'Login failed! - '+textStatus);
		}
	});
}

// Helper function to serialize all the form fields into a JSON string
function loginFrmToJSON() {
	return JSON.stringify({
		"username" : $('#loginUsername').val(),
		"password" : $('#loginPassword').val()
	});
}

/** ************User Registration***************** */
// Register listeners
$('#registerBtn').click(function() {
	registerUser();
	return false;
});

// Functions
function registerUser() {
	console.log('registerUser');
//	alert(registerFrmToJSON());
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/userservice/register',
		dataType : "json",
		data : registerFrmToJSON(),
		success : function(data, textStatus, jqXHR) {
			if (data == true) {
//				alert('User created successfully');
				username = $('#regUsername').val();
				password = $('#regPassword').val();
				$('#regName').val("");
				$('#regUsername').val("");
				$('#regPassword').val("");
				$('#loginMenu').hide();
				$('#logoutMenu').show();
				$('#closeaccountMenu').show();
				$('#registerMenu').hide();
				$('#myitemsMenu').show();
				showMsg(1, 'User registered successfully!');
				toggleSectionDivs('my_items');
				$('#myitemsMenu').click();

			}
			else{
				showMsg(-1, 'User registration failed!');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
//			alert('registerUser error: ' + textStatus);
			showMsg(-1, 'User creation failed! - '+textStatus);
		}
	});
}

// Helper function to serialize all the form fields into a JSON string
function registerFrmToJSON() {
	return JSON.stringify({
		"name" : $('#regName').val(),
		"username" : $('#regUsername').val(),
		"password" : $('#regPassword').val()
	});
}

/** **********************MSG BOX****************************** */
function showMsg(type, message){
	if(type==-1){
		alert("ERROR: "+message);
	}
	if(type==1){
		alert("SUCCESS: "+message);
	}
}
