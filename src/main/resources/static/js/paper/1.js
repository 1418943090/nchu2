
function paperSet(){


    var no1 = document.getElementById("no1");
     no1[0].selected = true;
    var no2 = document.getElementById("no2");
    no2[1].selected = true;
    var no3 = document.getElementById("no3");
    no3[2].selected = true;


$("#paperSetModal").modal("show");
}


function paperSetSure(){


 var data = {
     "no1":$("#no1").find("option:selected").attr("id"),
     "no2":$("#no2").find("option:selected").attr("id"),
     "no3":$("#no3").find("option:selected").attr("id")
 };
    $("#closemodal_paperSet").click();

    setTimeout(function(){
        $.ajax
        ({
            url: '/paper/set',
            contentType: 'application/json;charset=utf-8',
            type: 'post',
            async:true,
            data: JSON.stringify(data),
            dataType:"text",
            success: function (date) {
               $("#rightContainer").html(date);
            },
            error : function() {
                swal({
                    title: "服务器处理错误",
                    text: "",
                    icon: "error",
                    button: "确定",
                });
            }
        });
        },500);
}