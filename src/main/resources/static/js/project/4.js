

function projectSet2(list){

    var type = $("#main_type").prop("value");
    var obj1=document.getElementById('pro_no1');
    var obj2=document.getElementById('pro_no2');
    var obj3=document.getElementById('pro_no3');
    obj1.options.length=0;
    obj2.options.length=0;
    obj3.options.length=0;
    for(k in list){
        if(type=="所有项目"){
        obj1.options.add(new Option(list[k].name));
        obj2.options.add(new Option(list[k].name));
        obj3.options.add(new Option(list[k].name));
        }
        else{
            if(type==list[k].type){
                obj1.options.add(new Option(list[k].name));
                obj2.options.add(new Option(list[k].name));
                obj3.options.add(new Option(list[k].name));

            }
        }
    }
   obj1[0].selected = true;
   obj2[1].selected = true;
   obj3[2].selected = true;
   $("#projectSetModal").modal("show");
}
function projectSetSure2(list){

    var no1;
    var no2;
    var no3;

    for(k in list){
        if(list[k].name==$('#pro_no1 option:selected').text()){
            no1 = list[k].id;
            break;
        }
    }
    for(k in list){
        if(list[k].name==$('#pro_no2 option:selected').text()){
            no2 = list[k].id;
            break;
        }
    }
    for(k in list){
        if(list[k].name==$('#pro_no3 option:selected').text()){
            no3 = list[k].id;
            break;
        }
    }


    var data = {
        "no1":no1,
        "no2":no2,
        "no3":no3,
        "type":$('#main_type option:selected').text()
    };
    $("#closemodal_projectsSet").click();
    $("#sk-three-bounce").show();
    setTimeout(function(){
        $.ajax
        ({
            url: '/projectCenter/set',
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