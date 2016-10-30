<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->createEmployee( $_POST ['employee'], $_POST['role']);
	$_SESSION["errorEmployee"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorEmployee"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>