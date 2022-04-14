function init() {
    curUserStr = cookie("curUser");
    curUser = curUserStr == null ? null : JSON.parse(curUserStr);
    console.log(curUser);
    document.getElementById("tximg").src = curUser.gender == 0 ? "images/team/4.png" : "images/team/1.png";
    document.getElementById("userNameInfo").innerHTML = curUser.name;
    document.getElementById("userIDInfo").innerHTML = curUser.id;
    document.getElementById("userAgeInfo").innerHTML = curUser.age;
    document.getElementById("userGenderInfo").innerHTML = curUser.gender == 0 ? "女" : "男";
    document.getElementById("userTelInof").innerHTML = curUser.tel;
}

init();