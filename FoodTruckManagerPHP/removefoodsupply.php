<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->removeFoodSupply( $_POST ['food_name'], $_POST['food_num']);
	$_SESSION["errorRemoveFoodSupply"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorRemoveFoodSupply"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>