<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();
$c = new Controller ();
try {
	$c->removeEquipment( $_POST ['equipment_name'], $_POST['equipment_num']);
	$_SESSION["errorRemoveEquipment"] = "";
} catch ( Exception $e ) {
	$_SESSION["errorRemoveEquipment"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>