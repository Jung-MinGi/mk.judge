
window.onload = function(){
    var tag = "  <table class='grid' style='width:100%'>";
    tag+='<h2>문제</h2>';
    tag+='<div><button id="hidden">option</button></div><div id="opt"></div>';
    tag+='<br>';
    tag+='<tr style="height:30px; text-align: center;">';
    tag+='<th style="width:60px;">';
    tag+='<b>number</b>';
    tag+='</th>';
    tag+='<th><b>title</b></th>';
    tag+='<th style="width:75px;"><b>정보</b></th>';
    tag+='<th style="width:75px;"><b>등급</b></th></tr>';
    tag+='</tr>';


    fetch("/problem").then(function(res){
        return res.json();
    }).then(function(data){
        for(var k in data){
        tag+='<tr style="height:30px;">';
        tag+='<td style="text-align: center;">'+data[k].id+'</td>';
        tag+='<td><a href=/problem/detail/'+data[k].id+'>'+data[k].title+'</a></td>';
        tag+='<td>'+data[k].content+'</td>';
        tag+='<td>'+data[k].answer+'</td>';
        tag+='</tr>';
        }
        tag+='</table>';
        document.getElementById("content").innerHTML=tag;
    }).then(function(){

createOption();
    });
}


//옵션 창띄우기
function createOption(){document.getElementById("hidden").addEventListener('click',function(event){
var optDiv = document.getElementById("opt");
  if (optDiv.innerHTML !== "") {
                    // 옵션 창이 이미 표시되어 있는 경우, 숨깁니다.
                    optDiv.innerHTML = "";
                } else {
    var tag='<div class="form-check">';
    tag+='<label>문제 난이도</label>';
  tag+='<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">';
  tag+='<label class="form-check-label" for="flexCheckDefault">';
  tag+='Default checkbox'
  tag+='</label>'
  tag+='</div>'
  tag+='<div class="form-check">';
  tag+='<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>';
  tag+='<label class="form-check-label" for="flexCheckChecked">';
  tag+='Checked checkbox';
  tag+='</label>';
  tag+='</div>';
  document.getElementById("opt").innerHTML=tag;
  }
})};

