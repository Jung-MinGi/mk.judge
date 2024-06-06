var flag=false;
var prevId="";
var idDoubleFlag=false;

function idCheck(){
    var username = document.getElementById("username").value;
    if(username.length>0){
    document.getElementById("idBtn").disabled=false;
    }else {
    document.getElementById("idBtn").disabled=true;
    }
}

function idDoubleCheck(){
    var username = document.getElementById("username").value;
    var flag=  fetch("/join/id?username="+username)
    .then(function(res){
       res.text().then(function(data){
               return data;
        }).then(function(data){

            if(data=="true"){
            alert("사용가능한 아이디입니다");
            idDoubleFlag=true;
            prevId = username;
            }else{
            alert("사용불가능합니다");
            }
        })
    });
}

//id중복확인 성공했는데 아이디 변경시 다시 중복확인체크해야됨
document.getElementById("username").addEventListener('click',function(event){
    idDoubleFlag=false;
    prevId=tmp;
});

function pwCheck(){

    var pw1 = document.getElementById("password").value;
    var pw2 = document.getElementById("password2").value;
    var ret = document.getElementById("p");

     if(pw1!=''&&pw1==pw2){
        ret.innerHTML="일치";
        ret.style.color = 'green';
        flag=true;
        console.log(flag);
        }
        else{
        ret.innerHTML="불일치";
        ret.style.color = 'red';
        flag=false;
        }
}

function join(){
    if(flag&&idDoubleFlag){
        var data ={
            username:document.getElementById("username").value,
            password:document.getElementById("password").value

        };

        fetch('/join', {
             method: 'PUT',
            headers: {'Content-Type'= 'application/json'},
            body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(result => {
             window.location.href = result;
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }else{
      if(!flag&&!idDoubleFlag){
         alert("필수 입력란 작성해주세요");
     }else if(!flag){
     alert("비밀번호를 확인해주세요");
     }else {
       alert("아이디 중복확인해주세요");
      }
   }
}