function redirectWithToken(){
    fetch("/api/myPage", {
        method: 'get',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    }).then(function(res){
        if(res.ok){

        return res.json();
        }else throw new Error();
    }).then(function(data){
        console.log(data);
        document.getElementById("username").innerText =data.user.username;
        createChart(data.labels,data.solved,data.backGroundColor);
        createSolvedProblemsList(data.solvedProblems);
   if (data.user.level === "BRONZE") {
         document.getElementById("rank").innerText = "🥉";
     } else if (data.user.level === "SILVER") {
         document.getElementById("rank").innerText = "🥈";
     } else {
         document.getElementById("rank").innerText = "🥇";
     }
    }).catch(error=>{
      alert("로그인 후 이용 가능 합니다");
      window.location.href="/login";
    })

}

redirectWithToken();




//도넛차트
function createChart(a,b,c){
    const data = {
    labels: a,
    datasets: [{
        data: b,
        backgroundColor: c,
        hoverOffset:c.length
    }]
};

const config = {
    type: 'doughnut',
    data: data,
};

const chart = new Chart(document.getElementById('doughnut'),
    config
)}


function createSolvedProblemsList(list){
    var tag="";
    for(var k=1;k<=list.length;k++){
        tag+='<li>Problem '+k+' - <a href="#">'+list[k-1].title+'</a></li>';
    }
    document.getElementsByClassName("problem-list")[0].innerHTML = tag; // [0] 추가

}


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
            }
        })
    }
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


function logout() {
    document.getElementById("logout").addEventListener('click', function (event) {
        event.preventDefault();
        localStorage.removeItem('token');
        window.location.href = "/";
    })
}

changeTag();