<?php
$conn = new mysqli('localhost', 'root', 'root', 'newsql');

// 检查连接
if ($conn->connect_error) {
    die("连接失败: ". $conn->connect_error);
}

$logname = $_POST['user'];
$password1 = $_POST['password1'];
$password2 = $_POST['password2'];
$tel = $_POST['tel']; 

// 用户名、密码和电话号码的正则表达式验证
if (!preg_match('/^[a-zA-Z0-9_]{4,20}$/', $logname)) {
    echo '用户名格式不正确，请使用4-20位字母、数字或下划线！';
    header('Refresh:3,http://localhost/wangjimiam.html');
    exit();
}

if (!preg_match('/^[a-zA-Z0-9_!@#$%^&*()-]{6,20}$/', $password1)) {
    echo '密码格式不正确，请使用6-20位字母、数字或特殊字符！';
    header('Refresh:3,http://localhost/wangjimiam.html');
    exit();
}

if ($password1!= $password2) {
    echo '密码不一致。';
    header('Refresh:3,http://localhost/wangjimiam.html');
    exit();
}

if (!preg_match('/^\\d{11}$/', $tel)) {
    echo '电话号码格式不正确，请输入 11 位数字！';
    header('Refresh:3,http://localhost/wangjimiam.html');
    exit();
}

// 对密码进行哈希处理
$hashed_password = password_hash($password1, PASSWORD_DEFAULT);

// 使用预处理语句查询用户是否存在
$stmt = $conn->prepare("SELECT * FROM user WHERE user =?");
$stmt->bind_param("s", $logname);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    // 用户存在，使用预处理语句更新哈希后的密码
    $stmt_update = $conn->prepare("UPDATE user SET password =? WHERE user =? and tel = ?");
    $stmt_update->bind_param("ssi", $hashed_password, $logname,$tel);

    // 执行更新
    if (!$stmt_update->execute()) {
        echo '密码修改失败';
    } else {
        echo '密码修改成功！';
    }

    // 关闭预处理语句
    $stmt_update->close();
} else {
    echo "用户不存在";
}

// 关闭查询预处理语句和连接
$stmt->close();
$conn->close();

