
function changeTag() {
    if (localStorage.getItem('token') != null) {
        fetch("/isExpired", {
            method: 'get',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.ok) {
                console.log(1);
                var element = document.getElementById("join");
                element.id = "myPage";
                element.textContent = '마이페이지';
//                element.onclick = myPage();
                element.href="/myPage";

                element = document.getElementById("login");
                element.id = "logout";
                element.textContent = '로그아웃';
                element.onclick = logout();
            }else{
                console.log(res);
            }
        })
    }
}



function logout() {
    document.getElementById("logout").addEventListener('click', function (event) {
        event.preventDefault();
        localStorage.removeItem('token');
        window.location.href = "/";
    })
}

function myPage() {
    document.getElementById("myPage").addEventListener('click', function (event) {
        event.preventDefault();
        fetch("/api/myPage", {
            method: 'get',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }

        }).then(function (res) {
           if(res.ok){
            alert(res.ok);
            window.location.href="/myPage";
           }else throw new Error();
        }).catch(error=>{
            alert("로그인 후 이용 가능 합니다: "+error);
             window.location.href="/login";
        })
    })
}


changeTag();