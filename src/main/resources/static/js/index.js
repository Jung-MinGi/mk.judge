document.getElementById("mk").addEventListener('click',function(event){

    fetch("/tmp/getAll").then(function(ss){
        return ss.json();
    })
    .then(function(data){
    var tag='';
        tag+='<table>';
        tag+='<tr>';
        tag+='<td>username</td>';
        tag+='<td>password</td>';
        tag+='<td>Level</td>';
        tag+='</tr>';
        for(var k in data){
            tag += '<tr><td>' + data[k].username + '</td>' + '<td>' + data[k].password + '</td>' + '<td>' + data[k].level + '</td></tr>';
        }
        tag+='</table>';
document.getElementById("content").innerHTML=tag;;

        });
});

document.getElementById("mg").addEventListener('click', function (event) {

    const tk = localStorage.getItem("token");
    var data = {
        token: tk
    }
    console.log(data.token);
    if (tk!=null) {
        fetch("/manager", {
            method: 'get',
            headers: {
                'Authorization': 'Bearer ' + tk,
                'Content-Type': 'application/json'
            }
        }).then(function(res){
            return res.text();
        }).then(function(data){
            alert(data);
        })
    } else {

    }
})


document.getElementById("prob_list").addEventListener('click', function (event) {
    event.preventDefault();

    const tk = localStorage.getItem("token");
    var data = {
        token: tk
    }
//    console.log(data.token);
//    if (tk != null) {
        fetch("/prob_list", {
            method: 'get',
            headers: {
//                'Authorization': 'Bearer ' + tk,
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.ok) {
                return res.text();
            } else {
                alert("로그인후 이용가능합니다.")
                window.location.href = "/login";
            }
        }).then(function (data) {
            document.write(data);
        })
//    } else {
//        alert("로그인후 이용가능합니다.")
//        window.location.href = "/login";
//    }
})

