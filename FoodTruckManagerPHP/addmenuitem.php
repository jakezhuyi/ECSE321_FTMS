<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->addMenuItem( $_POST ['menuitem']);
	$_SESSION["errorMenuItem"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorMenuItem"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>