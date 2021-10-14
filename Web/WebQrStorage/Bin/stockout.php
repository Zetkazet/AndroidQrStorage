<?php
require_once('connection.php');

$idproduct = $_POST["pid"];
$idaccount = $_POST["pac"];
$receipttp = $_POST["rtp"];
$productsum = $_POST["pqn"];



if(!$idproduct || !$idproduct || !$receipttp || !$productsum){

  echo json_encode(array('message'=>'required field is empty.'));

}else{

    $query = mysqli_query($CON, "INSERT INTO receipttable VALUES ('$idproduct','$idaccount','$receipttp','$productsum')"); // add receipt

        if($query){

            echo json_encode(array('message'=>'receipt data successfully added.'));
            $result = array();
            $pstock = mysqli_query($CON,"SELECT product_stock FROM stocktable WHERE id_product= '$pid'"); // read the stock

            $result = $pstock - $productsum ;

            $stockin = mysqli_query($CON, "UPDATE stocktable SET product_stock = '$result' WHERE id_product= '$pid'"); // update the stock
            
        }else{

            echo json_encode(array('message'=>'receipt data failed to add.'));

        }
}



?>