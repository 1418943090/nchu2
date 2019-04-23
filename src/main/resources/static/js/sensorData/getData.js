$(function(){

    getData();
    $('body').everyTime('5s',function(){
    getData();
    });
});


function getData(){
    $.ajax
    ({
        url: '/sensorData/getData',
        contentType: 'application/json;charset=utf-8',
        type: 'get',
        async:true,
        dataType:"json",
        success: function (data) {
            for(var i  in data){

                if(data[i].status==0){ //传感器状态0下线 1在线
                    $("#"+data[i].sensorId+'inLine').hide();
                    $("#"+data[i].sensorId+'unLine').show();
                }else{
                    $("#"+data[i].sensorId+'inLine').show();
                    $("#"+data[i].sensorId+'unLine').hide();
                    $("#"+data[i].sensorId).text(data[i].value+data[i].unit);
                }

            }
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