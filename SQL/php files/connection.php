<?php

define('hostname', 'localhost');
define('user', 'root');
define('password', '');
define('databaseName', 'DbInsert_app');


$connect = mysqli_connect(hostname, user, password, databaseName);

// Check connection
if ($connect->connect_error) {
    die("Connection failed: " . $connect->connect_error);
} 
?>