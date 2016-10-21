<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Event Registration</title>
		<style>
			.error {color: #FF0000;}
		</style>
	</head>
	<body>
		<?php 
		require_once 'model/FoodSupply.php';
		require_once 'model/Equipment.php';
		require_once 'model/FoodTruckManager.php';
		require_once 'persistence/PersistenceFoodTruckManager.php';
		
		session_start();
		
		//retrieve data from the model
		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		
		?>
		
		<form action="addfoodsupply.php" method="post">
			<p>Food supply to add? <input type="text" name="food_name" />
			<p>Number? <input type="text" name="food_num" />
			<span class="error">
			<?php
			if (isset($_SESSION['errorFoodSupply']) && !empty($_SESSION['errorFoodSupply'])) {
				echo " * " . $_SESSION["errorFoodSupply"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Add Food Supply"/></p>
		</form>	
			<form action="removefoodsupply.php" method="post">
			<p>Food supply to remove? <input type="text" name="food_name" />
			<p>Number? <input type="text" name="food_num" />
			<span class="error">
			<?php
			if (isset($_SESSION['errorRemoveFoodSupply']) && !empty($_SESSION['errorRemoveFoodSupply'])) {
				echo " * " . $_SESSION["errorRemoveFoodSupply"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Remove Food Supply"/></p>
		</form>
		
		<form action="addequipment.php" method="post">
			<p>Equipment to add? <input type="text" name="equipment_name" />
			<p>Number? <input type="text" name="equipment_num" />
			<span class="error">
			<?php
			if (isset($_SESSION['errorEquipment']) && !empty($_SESSION['errorEquipment'])) {
				echo " * " . $_SESSION["errorEquipment"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Add Equipment"/></p>
			
		</form>	
			<form action="removeequipment.php" method="post">
			<p>Equipment to remove? <input type="text" name="equipment_name" />
			<p>Number? <input type="text" name="food_num" />
			<span class="error">
			<?php
			if (isset($_SESSION['errorRemoveEquipment']) && !empty($_SESSION['errorRemoveEquipment'])) {
				echo " * " . $_SESSION["errorRemoveEquipment"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Remove Equipment"/></p>
		</form>
	</body>
</html>


