<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();

$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();

foreach ( $ftm->getEquipment () as $equipment )
{
	echo 'There are ' . $equipment->getSupply() . ' ' . $equipment->getName() . "<br>";
}

?>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="5; url=/FoodTruckManagerPHP/" />
	</head>
</html>