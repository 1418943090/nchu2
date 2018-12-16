


function globalChangesign(){


   $("#globalChangeSignin").modal("show");


}

function globalChangesignSubmit(){

    document.getElementById("closeChangeSignmodal2").click();
    setTimeout(function(){
        $("#globalChangeSigninForm").ajaxSubmit(function(message) {

           $("#rightContainer").html(message);
       });
    },500);

}


function  sign_In_Search(){

    var date = $("#datetime").val();

    if(date==null || date=="") {
        swal({
            title: "请输入查询日期!",
            text: '',
            icon: "warning",
            button: "确定",
        });
    }
    else {
        $.ajax
        ({
            url: '/sign_in/show/all/' + date,
            type: 'get',
            async: true,
            success: function (data) {
                $("#rightContainer").html(data);
            },
            error: function () {
                swal({
                    title: "Error!",
                    text: '服务器处理错误',
                    icon: "error",
                    button: "确定",
                });
            }
        });
    }
}


function showInterval(){

    var startDate;
    var endDate;

    swal("请输入起始日期(yyyy-MM-dd):", {
        content: "input",
    })
        .then((value) => {
            if(value==null ||  value.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/) == null || isNaN(Date.parse(value))) {
                swal("格式错误", "请输入正确格式的日期!");
            }
            else{
                startDate = value;
                swal("请输入终止日期(yyyy-MM-dd):", {
                    content: "input",
                })
                    .then((value) => {
                        if(value==null ||  value.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/) == null || isNaN(Date.parse(value))){
                            swal("格式错误", "请输入正确格式的日期!");
                        }
                        else{
                            endDate = value;
                            dateCheck(startDate,endDate);
                            //add_submit(note,value,type);
                        }
                    });
            }
        });
}

function dateCheck(startDate,endDate){

    var s = new Date(startDate).getTime();
    var e = new Date(endDate).getTime();
    var now = new Date().getTime();
    if(s>=e){
        swal({
            title: "Error!",
            text:"终止日期:"+endDate+"不能小于等于起始日期:"+startDate,
            icon: "error",
            button: "确定",
        });
    }
    else{
        if(e>now){
            swal({
                title: "Error!",
                text:"只能查询过去时间段的签到信息",
                icon: "error",
                button: "确定",
            });
        }
        else{
            showIntervalSubmit(startDate,endDate);
        }
    }
}

function  showIntervalSubmit(startDate,endDate)
{
    $.ajax
    ({
        url: '/sign_in/show/interval/'+startDate+'/'+endDate,
        type: 'get',
        async:true,
        success: function (data) {
            $("#tableBody").html(data);
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


function showday(){

    var date = $("#datetime").val();
    if(date==null || date=="") {
        swal({
            title: "请输入查询日期!",
            text: '',
            icon: "warning",
            button: "确定",
        });
    }else {
        $.ajax
        ({
            url: '/sign_in/show/all/' + date,
            type: 'get',
            async: true,
            success: function (data) {
                $("#rightContainer").html(data);
            },
            error: function () {
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
}

function showweek(){
    var date = $("#datetime").val();
    if(date==null || date=="") {
        swal({
            title: "请输入查询日期!",
            text: '',
            icon: "warning",
            button: "确定",
        });
    }
    $.ajax
    ({
        url: '/sign_in/show/week/'+date,
        type: 'get',
        async:true,
        success: function (data) {
            $("#tableBody").html(data);
        },
        error : function() {
            swal({
                title: "Error!",
                text: '服务器处理错误',
                icon: "error",
                button: "确定",
            });
        }
    });

}