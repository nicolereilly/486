<?php session_start();
if (!isset($_SESSION['user'])) {
    header("location: login.php");
}
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        Administrator Menu
        <ul>
            <li>TODO: Create</li>
            <li>TODO: Read</li>
            <li>Update</li>
            <li>Delete</li>
        </ul>
          
    </body>
</html>
