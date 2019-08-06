<?php

$host = "localhost";
$user = "root";
$password ="";
$database = "beacons";

$regno = $_POST['regno'];
$iter = $_POST['iter'];
$regno_array = explode (";", $regno);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
$length = count($regno_array);

try{
    $connect = mysqli_connect($host, $user, $password, $database);
} 
catch (mysqli_sql_exception $ex) {
    echo 'Error';
}
$colname = "iter".$iter;
$preiter = (int)$iter - 1;
$preiter = (string)$preiter;
$precolname = "iter".$preiter;

$column_query = "ALTER TABLE `cc1` ADD `$colname` VARCHAR(2) NOT NULL AFTER `$precolname`";
$excute = mysqli_query($connect, $column_query);
for ($i = 0; $i < $length; $i++) {
    $number = $regno_array[$i];
    if(!empty($number))
    {
        
        $search_Query = "SELECT * FROM beacon WHERE regno = '$number'";
        
        $search_Result = mysqli_query($connect, $search_Query);
        
        if($search_Result)
        {
            if(mysqli_num_rows($search_Result))
            {
                while($row = mysqli_fetch_array($search_Result))
                {
                    $reg = $row['registration_number'];
                    $attendance1 = "p";
                    $update_Query = "UPDATE `cc1` SET `$colname`='$attendance1' WHERE `registration_number` = '$reg'";
                    try{
                        $update_Result = mysqli_query($connect, $update_Query);
        
                    if($update_Result){
                        if(mysqli_affected_rows($connect) > 0){
                            echo 'Marked Present';
                            }
                        else{
                            echo 'Error Reported';
                            }
                        }
                    }
                    catch (Exception $ex) {
                        echo 'Error Update '.$ex->getMessage();
                        }
                    }

                }
            }
        }
    }
?>