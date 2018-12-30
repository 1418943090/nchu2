function deleteProducts(){
    var obj = document.getElementsByClassName("product_check");
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
            title: "你还没有选择要删除的产品哦",
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
                    dele(check_id);
                }
            });
    }
}
function dele(check_id){
    var data = {
        "listProductsId": check_id

    };

    $("#sk-three-bounce").show();
    $.ajax
    ({
        url: '/product/delete',
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