

function rem(flag){
    var obj = document.getElementsByClassName(flag);
    var username;
    var check_id = [];
    var k;
    for(k in obj){
        if(obj[k].checked && obj[k].id!=undefined)
        {
            check_id.push(obj[k].id);
        }
    }
    if(check_id.length==0)
    {
        swal({
            title: "你还没有选择要删除的假日记录哦",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }else {

        swal({
            title: "Are you sure?",
            text: "数据删除后将无法恢复",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    dele(check_id,flag);
                }
            });
    }
}
function dele(check_id,flag){

    var data = {
        "listFestivalId":check_id,
        "flag":flag
    };
    $.ajax
    ({
        url: '/delete/festival/',
        contentType: 'application/json;charset=utf-8',
        type: 'post',
        async:true,
        data: JSON.stringify(data),
        //dataType:"text",
        success: function (data) {
            $("#rightContainer").html(data);
        },
        error : function() {
            // toastr.warning("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
            swal({
                title: "Error!",
                text: '服务器处理错误',
                icon: "error",
                button: "确定",
            });
        }
    });
}
$('.card').on('click','input:checkbox',function () {
    var id = $(this).attr("id");
    if(id!=="f_main") {
        var allCheck = $("#f_main").prop('checked');
        if (allCheck == true) {
            $("#f_main").prop("checked",false);
        }
    }else{
        var allCheck = $("#f_main").prop('checked');
        var obj = document.getElementsByClassName("1");
        var k;
        for(k in obj){
            obj[k].checked=allCheck;
        }
    }

    if(id!=="f_main2") {
        var allCheck = $("#f_main2").prop('checked');
        if (allCheck == true) {
            $("#f_main2").prop("checked",false);
        }
    }else{
        var allCheck = $("#f_main2").prop('checked');
        var obj = document.getElementsByClassName("2");
        var k;
        for(k in obj){
            obj[k].checked=allCheck;
        }
    }
});

function add(type){

    var note;
    swal("请输入节日备注:", {
        content: "input",
    })
        .then((value) => {

            if(value==null  || value.replace(/\s+/g,"")==""){
                swal("格式错误", "请输入有效的备注!");
            }
            else{
            note = value;
            swal("请输入日期(yyyy-MM-dd):", {
                content: "input",
            })
                .then((value) => {


                    if(value==null ||  value.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/) == null || isNaN(Date.parse(value))){
                        swal("格式错误", "请输入正确格式的日期!");
                    }
                    else{
                        add_submit(note,value,type);
                    }

                });
            }
        });
}

function add_submit(note,date,type){
    $.ajax({
        url: "/festival/add/"+note+"/"+date+"/"+type,
        async:true,
        success: function(data){
            $("#rightContainer").html(data);
        },
        error: function() {
            //toastr.error("一不小心就出错了^_^,请刷新试试嘻嘻,还不行的话,及时联系管理员哦");
            swal({
                title: "Error!",
                text: '服务器处理错误',
                icon: "error",
                button: "确定",
            });
        }
    });
}


