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
        }
    }).then(function(data){
        console.log(data);
        document.getElementById("username").innerText =data.user.username;
        createChart(data.labels,data.solved,data.backGroundColor);
        createSolvedProblemsList(data.solvedProblems);
        createSolvedProblemAndRank(data.solved,data.rank);
   if (data.user.level === "BRONZE") {
         document.getElementById("level").innerText = "🥉";
     } else if (data.user.level === "SILVER") {
         document.getElementById("level").innerText = "🥈";
     } else {
         document.getElementById("level").innerText = "🥇";
     }
    })
}



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
)
}


function createSolvedProblemsList(list){
    var tag="";
    for(var k=1;k<=list.length;k++){
        tag+='<li>Problem '+k+' - <a href="/problem/detail/'+list[k-1].id+'">'+list[k-1].title+'</a></li>';
    }
    document.getElementsByClassName("problem-list")[0].innerHTML = tag;

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
            }else{
                alert("로그인 후 이용해주세요.");
                window.location.href="/login";
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



//solvedProblems개수 채우기
function createSolvedProblemAndRank(li,a){
    var cnt=0;
    for(var k=0;k<li.length-1;k++){
        cnt+=Number(li[k]);
    }
    document.getElementById("cnt").innerHTML=cnt;
    document.getElementById("rank").innerHTML=a;
}


redirectWithToken();
changeTag();