<?php
require_once (__DIR__.'/controller/Controller.php');

session_start ();

		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		
		$largest = $ftm ->getMenuItem_index(0)->getName();
		$largestNum =  $ftm ->getMenuItem_index(0)->getAmountSold();
		foreach ( $ftm->getMenuItems () as $item )
		{
			if($item -> getAmountSold() > $largestNum)
			{
				$largest = $item;
				$largestNum = $item -> getAmountSold;
			}
		}
		
		echo "Most Popular Item: " . $largest . " Amount Sold: " . $largestNum;
		
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="5; url=/FoodTruckManagerPHP/" />
	</head>
</html>