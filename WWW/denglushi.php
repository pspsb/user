<?php
$conn = new mysqli('localhost', 'root', 'root', 'newsql');

if ($conn->connect_error) {
    die("连接失败: ". $conn->connect_error);
}

$logname = $_POST['user'];
$pw = $_POST['password'];

// 使用正则表达式验证用户名和密码

//用户名验证：/^[a-zA-Z0-9_]{4,20}$/，要求用户名由 4 到 20 位字母、数字或下划线组成。
if (!preg_match('/^[a-zA-Z0-9_]{4,20}$/', $logname)) {
    echo '用户名格式不正确，请使用 4-20 位字母、数字或下划线！';
    exit();
}

//密码验证：/^[a-zA-Z0-9_!@#$%^&*()-]{6,20}$/，要求密码由 6 到 20 位字母、数字或特殊字符组成。
if (!preg_match('/^[a-zA-Z0-9_!@#$%^&*()-]{6,20}$/', $pw)) {
    echo '密码格式不正确，请使用 6-20 位字母、数字或特殊字符！';
    exit();
}

$sql = "SELECT * FROM user WHERE user =?;"; // 使用预处理语句防止 SQL 注入

$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $logname);
$stmt->execute();
$res = $stmt->get_result();

if ($res->num_rows > 0) {
    $row = $res->fetch_assoc();
    $hashed_password = $row['password'];

    // 使用 password_verify() 函数验证密码
    if (password_verify($pw, $hashed_password)) {
        echo '登陆成功。';
        header('location:http://localhost/denluhou.html');
    } else {
        echo '密码错误，请重新输入。';
    }
} else {
    echo '用户名不存在，请重新输入。';
}

$conn->close();

