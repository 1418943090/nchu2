
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
            text: "删除该天的节假日登记后,系统将会覆盖所有用户该天的签到状态。如果需要更改的话可以到签到情况栏进行自定义更改",
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
    swal("请输入备注:", {
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
                        dateCheck(note,value,type);
                       //add_submit(note,value,type);
                    }
                });
            }
        });
}

function dateCheck(note,value,type){

    var now = (new Date()).getTime();
    var date = (new Date(value)).getTime();
    var week = (new Date(value)).getDay();
    if(week!=0 && week!=6){
        swal({
            title: "Warning!",
            text: value+"并不是周末哦",
            icon: "warning",
            button: "确定",
        });
    }
   else

    if(now>date){
        swal({
            title: "Are you sure?",
            text: value+"是过去的时间哦,如果你执意添加该登记日期,那么该天的签到记录会覆盖哦",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                   add_submit(note,value,type);
                }
            });
    }
    else{
        add_submit(note,value,type);
    }
}
function add_submit(note,date,type){
    $.ajax({
        url: "/festival/add/"+note+"/"+date+"/"+type,
        async:true,
        success: function(data){
            $("#rightContainer").html(data);
        },
        error: function() {
            swal({
                title: "Error!",
                text: '服务器处理错误',
                icon: "error",
                button: "确定",
            });
        }
    });
}


