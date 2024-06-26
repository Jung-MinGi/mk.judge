function init(sort,page) {
    var tag = "<table class='grid' style='width:100%'>";
    tag += '<h2>문제</h2>';

    tag += '<div><form class="sky-form" action="#" style="margin-top: 5px;"><div class="input-group">';
    tag += '<input type="text" class="form-control" placeholder="문제검색" name="search" value = ""><span class="input-group-btn"><button class="btn btn-u" id="titleSearch" type="submit">🔎</button></span></div></form></div>';

    tag += '<div><button id="hidden">option</button></div><div id="opt"></div>';
    tag += '<br>';
    tag += '<tr style="height:30px; text-align: center;">';
    tag += '<th style="width:60px;">';
    tag += '<b>문제번호</b>';
    tag += '</th>';
    tag += '<th><b>문제제목</b></th>';
    tag += '<th style="width:75px;"><b>정보</b></th>';
    tag += '<th style="width:75px;"><b>난이도</b></th></tr>';
    tag += '</tr>';

    if (page === null || page === undefined) {
        page = 1;
    }
    var i = {
        list: sort,
        page: page
    };


    fetch("/problem",{
       method:'put',
       headers: {'Content-Type': 'application/json' },
       body:JSON.stringify(i)
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        for (var k in data) {
            tag += '<tr style="height:30px;">';
            tag += '<td style="text-align: center;">' + data[k].id + '</td>';
            tag += '<td><a href=/problem/detail/' + data[k].id + '>' + data[k].title + '</a></td>';
//            tag += '<td>' + data[k].content + '</td>';
            tag += '<td><span class="successProb" id="'+data[k].id+'"></span></td>';
            tag += '<td>' + data[k].level + '</td>';
            tag += '</tr>';
        }
        tag += '</table>';
        document.getElementById("content").innerHTML = tag;
        //        문제검색버튼에 이벤트 추가하는 코드
        document.getElementById("titleSearch").addEventListener('click', function (event) {
            const inputElement = document.getElementsByName('search')[0].value;

                           alert("문제검색: "+inputElement);
           });
         //
    }).then(function () {
        createOption();
        createPageBar(sort,page);//todo
        checkSolvedProblem();
    });
}


//옵션 창띄우기
function createOption() {
    document.getElementById("hidden").addEventListener('click', function (event) {
        var optDiv = document.getElementById("opt");
        if (optDiv.innerHTML !== "") {
            optDiv.innerHTML = "";
        } else {
            //
            fetch("/problem/level").then(function (res) {
                return res.json();
            }).then(function (data) {
                var tag = '<div class="form-check">';
                tag+='<hr>';
                tag += '<form id="optForm">';
                tag+='<p>•문제 난이도</P>';
                for (var i in data) {
                    tag += '<div class="form-check">';
                    tag += '<label class="form-check-label" for="flexCheck' + i + '">' + data[i] + '</label>';
                    tag += '<input class="form-check-input" type="checkbox" value="' + data[i] + '" id="'+ data[i] + '">';
                    tag += '</div>';
                }
                tag+='<hr>';
                tag += '</form>';
                tag += '</div>';
                tag += '<button type="submit" id="optBtn">적용</button>';
                document.getElementById("opt").innerHTML = tag;
                document.getElementById("optBtn").addEventListener('click', function (event) {
                    probSort();
                });


            })
        }
    })
};


function probSort() {
    fetch("/problem/level").then(function (res) {
        return res.json();
    }).then(function (data) {
        var li = [];
        for (var k in data) {//data가 브로즌실버골드임 현재
            if (document.getElementById(data[k]).checked) {
                li.push(data[k]);
                console.log(data[k]);
            }

        }
        console.log(li);
        init(li);
    })
}

//페이징바생성
function createPageBar(sort, page) {
    var a = {
        list: sort
    };
    fetch("/problem/page", {
        method: 'put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(a)
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        var blockNo = toInt(data / 15) + 1;//페이징 바에 나올 숫자의 개수
        var currentPageNum = page;
        console.log("blockNo::" + blockNo);
        var tag = '<nav aria-label="Page navigation example">';
        tag += '<ul class="pagination">';
        if (currentPageNum != 1) {//'<<'화살표
            tag += '<li class="page-item">';
            tag += '<button class="page-link" onclick=\'init(' + JSON.stringify(sort) + ',' + (page - 1) + ')\'><span aria-hidden="true">&laquo;</span></button>';
            tag += '</li>';
        }
        for (var i = 1; i <= blockNo; i++) {
                    if (i === currentPageNum) {
                        tag += '<li class="page-item active">'; // Adding 'active' class for current page
                        tag += '<a class="page-link" href="#">' + i + '</a>';
                    } else {
                        tag += '<li class="page-item">';
//                        tag += '<a class="page-link" href="#" onclick="init(' + sort + ',' + i + ')">' + i + '</a>';
                        tag += '<a class="page-link" href="#" onclick=\'init(' + JSON.stringify(sort) + ',' + i + ')\'>' + i + '</a>';
                    }
                    tag += '</li>';
                }

        if (currentPageNum != blockNo) {
            tag += '<li class="page-item">';
            tag += '<button class="page-link" onclick=\'init(' + JSON.stringify(sort) + ',' + (page + 1) + ')\'><span aria-hidden="true">&raquo;</span></button>';
            tag += '</li>';
        }
        tag += '</ul></nav>';
        document.getElementById("pageBar").innerHTML = tag;
    })
}
function toInt (value) {
     		if (value != null) {
     			return parseInt(value, 10);
     		}
     	}

function checkSolvedProblem(){
     fetch("/problem/solved", {
             method: 'get',
             headers: {
                 'Authorization': 'Bearer ' + localStorage.getItem("token"),
                 'Content-Type': 'application/json'
             }
         }).then(function(res){
            if(res.ok){
                return res.json();
            }else{
                console.log("로그인 안 된 상태");

            }
         }).then(function(data){
        for (var k in data) {
            var x = document.getElementById(data[k]);
            if (x) {
                x.innerHTML = "성공";
                x.style.backgroundColor = "#5cb85c";
                x.style.color = "white";
                x.style.padding = "5px";
                x.style.borderRadius = "3px";
                x.style.fontWeight = "bold";
                x.marginLeft="5px";

            }
        }
         })
}
init();