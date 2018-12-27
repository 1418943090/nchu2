
$("select#main_type").change(function(){

    //var list = [[${list}]];
    var type = $('#main_type option:selected').text();
    var url;
    if(type=="所有项目")
         url = "all";
    else if(type=="科研项目")
          url="researchProject";
    else if(type=="三小项目")
         url = "threesmallProject";
    else if(type=="横向项目")
         url = "hengProject";
    $("#sk-three-bounce").show();
    $.ajax
    ({
        url: '/projectCenter/type/'+url,
        type: 'get',
        async:true,
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


});