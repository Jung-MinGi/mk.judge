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