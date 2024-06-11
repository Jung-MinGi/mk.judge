document.getElementById("loginBtn").addEventListener("click", function () {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var data = {
        "username":username,
        "password":password
    }
    console.log(data);
    fetch("/login",{
        method:"POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(function(res){
       if(res.ok){
            return res.json();

        }else{
            alert(res.json().text());
            return new Error(res.text());
        }
    }).then(function(data){
               const tk = data.token;
               console.log(tk);
               localStorage.setItem("token",tk);
               window.location.href = '/'; // 로그인 성공 후 리디렉션

    })
})