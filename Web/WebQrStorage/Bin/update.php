<?php
require_once('connection.php');

$nim = $_POST['nim'];
$name = $_POST['name'];
$address = $_POST['address'];
$gender = $_POST['gender'];
if(!$nim || !$name || !$address || !$gender){
  echo json_encode(array('message'=>'required field is empty.'));
}else{
$query = mysqli_query($CON, "UPDATE tb_student SET name='$name', address='$address', gender='$gender' WHERE nim = '$nim'");
if($query){
    echo json_encode(array('message'=>'student data successfully updated.'));
  }else{
    echo json_encode(array('message'=>'student data failed to update.'));
  }
}
?>