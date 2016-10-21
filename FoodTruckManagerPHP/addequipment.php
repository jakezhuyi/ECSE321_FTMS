<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->addEquipment( $_POST ['equipment_name'], $_POST['equipment_num']);
	$_SESSION["errorEquipment"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorEquipment"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="5; url=/FoodTruckManagerPHP/" />
	</head>
</html>