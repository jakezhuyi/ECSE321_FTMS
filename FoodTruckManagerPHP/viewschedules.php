<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();

$pm = new PersistenceFoodTruckManager();
$ftm = $pm->loadDataFromStore();

foreach ( $ftm->getEmployees () as $employee )
{
	$times = "";
	foreach ($employee->getSchedules() as $schedule)
	{
		$times = $schedule->getWorkday() . " from " . $schedule->getStartTime() . " to " .$schedule->getEndTime();
	}
	echo $employee->getName() . " works on " . $times . "<br>";
}

?>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="5; url=/FoodTruckManagerPHP/" />
	</head>
</html>