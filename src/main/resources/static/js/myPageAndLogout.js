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
                element.onclick = myPage();


                element = document.getElementById("login");
                element.id = "logout";
                element.textContent = '로그아웃';
                element.onclick = logout();
            } else {
                console.log(res.text());
            }
        })


    }
}



function myPage() {
    document.getElementById("myPage").addEventListener('click', function (event) {
        event.preventDefault();
        alert("mypage");
    })

}

function logout() {
    document.getElementById("logout").addEventListener('click', function (event) {
        event.preventDefault();
        localStorage.removeItem('token');
        window.location.href = "/";
    })
}

changeTag();