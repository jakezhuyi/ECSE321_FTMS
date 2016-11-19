<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->order( $_POST ['order'], $_POST ['amount']);
	$_SESSION["errorOrder"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorOrder"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>