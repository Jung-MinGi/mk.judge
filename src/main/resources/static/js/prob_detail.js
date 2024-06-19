  document.getElementById("answerBtn").addEventListener('click', function (event) {
        var data = {
            answer: document.getElementById("answer").value,
            id: document.getElementById("id").innerText
        }

        async function aaa() {
            const ret = await fetch("/problem/submit", {
                method: 'put',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem("token"),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            return await ret.json();
        };



        aaa().then(res => {
            console.log(res);
        })
    });
