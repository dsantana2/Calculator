<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	println();

}


function println()
{
	global $connect;
	
	
    $query = "Select firstname, lastname, username, password, Date, profileImage FROM person";
	
	$result = mysqli_query($connect, $query);
	$number_of_rows = mysqli_num_rows($result);
	
	
	if($number_of_rows > 0) {
	while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}
	
	header('Content-Type: application/json');
	echo json_encode(array("Profile_List"=>$temp_array));
	mysqli_close($connect);
	
}
?>