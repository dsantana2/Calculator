<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	createProfile();
}


function createProfile()
{
	global $connect;
	
	$firstname = $_POST["name"];	
	$lastname = $_POST["lastname"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	$image = $_POST["image"];

	
	$query = "Insert into person(firstname, lastname, username, password, date, profileImage) values ('$firstname','$lastname','$username','$password',CURRENT_TIMESTAMP(),'$image');";
	
	mysqli_query($connect, $query) or die (mysqli_error($connect));
	mysqli_close($connect);
	
}
?>