<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->createParticipant ( $_POST ['food_name'] );
	$_SESSION ["errorFoodSupply"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorFoodSupply"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>