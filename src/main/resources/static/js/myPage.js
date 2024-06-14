function redirectWithToken(){
    fetch("/api/myPage", {
        method: 'get',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    }).then(function(res){

        return res.json();
    }).then(function(data){
        console.log(data);
        document.getElementById("username").innerText =data.user.username;
        createChart(data.labels,data.solved,data.backGroundColor);
        createSolvedProblemsList(data.solvedProblems);
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
//        document.getElementsByClassName("problem-list").innerHTML=tag;

    document.getElementsByClassName("problem-list")[0].innerHTML = tag; // [0] 추가
}