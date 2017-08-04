<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	does_user_exist();
}
	
		
	 function does_user_exist()
		{
			global $connect;

			$username = $_POST["username"];	
	        $password = $_POST["password"];


			$query = "Select * from person where username = '$username' and password = '$password' ";
			$result = mysqli_query($connect, $query);
			

			if(mysqli_num_rows($result)>0){
				$json['success'] = $username;
				echo json_encode($json);
				mysqli_close($connect);
			}else{
				$json['error'] = ' Wrong Email or password';
				echo json_encode($json);
				mysqli_close($connect);
			}
		}
	
?>