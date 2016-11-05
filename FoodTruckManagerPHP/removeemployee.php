<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->fireEmployee($_POST ['name']);
	$_SESSION["errorRemoveEmployee"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorRemoveEmployee"] = $e->getMessage();
}

echo "*************errorRemoveEmployee: " . $_SESSION["errorRemoveEmployee"];
?>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="5; url=/FoodTruckManagerPHP/" />
	</head>
</html>