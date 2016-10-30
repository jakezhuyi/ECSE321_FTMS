<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();

$_SESSION ["errorEmployeeName"] = "";
$_SESSION ["errorEventDate"] = "";
$_SESSION ["errorEventStartTime"] = "";
$_SESSION ["errorEventEndTime"] = "";

$c = new Controller ();

try
{
	$c->setSchedule($_POST['employee_name'], $_POST['event_date'], $_POST['starttime'], $_POST['endtime']);
}
catch ( Exception $e )
{
	// echo "CATCHED";
	// echo $e;
	$errors = explode ( "@", $e->getMessage () );
	foreach ( $errors as $error )
	{
		//echo $error;
		if (substr ( $error, 0, 1 ) == "1") $_SESSION["errorName"] = substr($error, 1);
		if (substr ( $error, 0, 1 ) == "2") $_SESSION["errorDate"] = substr($error, 1);
		if (substr ( $error, 0, 1 ) == "3") $_SESSION["errorStartTime"] = substr($error, 1);
		if (substr ( $error, 0, 1 ) == "4") $_SESSION["errorEndTime"] = substr($error, 1);
	}
	// echo "*************errorEventDate: " . $_SESSION["errorEventDate"];
}
?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagerPHP/" />
	</head>
</html>