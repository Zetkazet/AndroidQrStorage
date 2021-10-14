<?php
	
	include_once "Connect.php";

	class usr{};
	
	$username = $_POST["username"];
    $productid = $_POST["productid"];
    $productQuantity = (int)$_POST["productquantity"];
    $receipttp = "stock in";
	
	if ((empty($productQuantity))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "quantity is 0"; 
		die(json_encode($response));
	}
	
//check account

	$query = mysqli_query($con, "SELECT * FROM accounttable WHERE id_account='$username' ");
	
	$row = mysqli_fetch_array($query);
	
	if (!empty($row)){

        $query = mysqli_query($con, "INSERT INTO receipttable VALUES ( NULL ,'$productid','$username','$receipttp','$productQuantity')"); // add receipt

        if($query){

            
            $pstock = mysqli_query($con,"SELECT product_stock FROM stocktable WHERE id_product= '$productid'"); // read the stock
            
            $resultstock = $pstock->fetch_assoc();
            $pquantity= (int)$resultstock['product_stock'];

            (int)$result = $pquantity+$productQuantity;

            $stockin = mysqli_query($con, "UPDATE stocktable SET product_stock = '$result' WHERE id_product= '$productid'"); // update the stock

            if($stockin){
                $response = new usr();
                $response->success = $productQuantity;
                $response->message = $pquantity;
                $response->id = $result;
                $response->username = $row['account_name'];
		        die(json_encode($response));
            }
            
        }else{

            echo json_encode(array('message'=>'receipt data failed to add.'));

        }
		
        
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Username error";
		die(json_encode($response));
	}

	
	mysqli_close($con);

?>