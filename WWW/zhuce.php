<?php

$logname = $_POST['user'];
$password1 = $_POST['password1'];
$password2 = $_POST['password2'];
$tel = $_POST['tel'];

// 使用正则表达式验证用户名和密码
if (!preg_match('/^[a-zA-Z0-9_]{4,20}$/', $logname)) {
    echo '用户名格式不正确，请使用4-20位字母、数字或下划线！';
    header('Refresh:3,http://localhost/zhuce.html');
    exit();
}

if (!preg_match('/^[a-zA-Z0-9_!@#$%^&*()-]{6,20}$/', $password1)) {
    echo '密码格式不正确，请使用6-20位字母、数字或特殊字符！';
    header('Refresh:3,http://localhost/zhuce.html');
    exit();
}

if ($password1!= $password2) {
    echo '密码不一致。';
    header('Refresh:3,http://localhost/zhuce.html');
    exit();
}

// 验证 tel 是否为 11 位整数
if (!preg_match('/^\d{11}$/', $tel)) {
    echo '电话号码格式不正确，请输入 11 位数字！';
    header('Refresh:3,http://localhost/zhuce.html');
    exit();
}

$conn = new mysqli('localhost', 'root', 'root', 'newsql');

if ($conn->connect_error) {
    die("连接失败: ". $conn->connect_error);
}

// 使用预处理语句检查用户名是否存在
$stmt = $conn->prepare("SELECT * FROM user WHERE user =?");
$stmt->bind_param("s", $logname);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    echo '用户已存在,3秒后跳转,请重新输入。';
    header('Refresh:3,http://localhost/zhuce.html');
} else {
    // 对密码进行哈希加密
    $hashed_password = password_hash($password1, PASSWORD_DEFAULT);

    // 使用预处理语句插入新用户
    $stmt = $conn->prepare("INSERT INTO user(user, password, tel) VALUES(?,?,?)");
    $stmt->bind_param("ssi", $logname, $hashed_password,$tel);
    if ($stmt->execute()) {
        echo '注册成功,3秒后返回登录页面。';
        header('Refresh:3,http://localhost/denglushi.html');
    } else {
        echo '注册失败。';
    }
}

$stmt->close();
$conn->close();
