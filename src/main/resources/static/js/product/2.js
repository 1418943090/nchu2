//
function validator_Init2() {
    $('#editProductForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '你还没有输入产品名哦'
                    },
                    stringLength: {
                        min: 3,
                        max: 250,
                        message: '产品名长度在3~250个字符哦'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '你还没有输入产品描述哦'
                    },
                    stringLength: {
                        min: 50,
                        max: 800,
                        message: '产品描述长度范围为50~800'
                    }
                }
            },
            file_pic: {
                validators: {
                    regexp: {
                        regexp: /^.*?(png|jpg)$/,
                        message: '图片必须是png或jpg格式的哦'
                    }
                }
            },
            file_document: {
                validators: {
                    regexp: {
                        regexp: /^.*?(pdf)$/,
                        message: '文档必须是pdf格式的哦'
                    }
                }
            }
        }
    });
}

function  product_checkbox_check(list){

    var obj = document.getElementsByClassName("product_check");
    var num=0;
    var k;
    var id;
    for(k in obj){
        if(obj[k].checked && obj[k].id!=undefined){
            num++;
            id=obj[k].id;
        }
    }
    if(num==0){
        swal({
            title: "请选择要编辑的产品!",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }
    else if(num>1){
        swal({
            title: "一次只能编辑一个产品!",
            text: "",
            icon: "warning",
            button: "确定",
        });
    }
    else{
        // updateFormvalidator_Init();
        product_date_init(list,id);
        $('#editProductModal').modal('show');
    }
}
//
function product_date_init(list,id){

    for(k in list){
        if(list[k].id==id){
            $("#edit_id").prop("value",list[k].id);
            $("#edit_name").prop("value",list[k].name);
            $("#edit_description").prop("value",list[k].description);
        }
    }
}


$('.card').on('click','input:checkbox',function () {

    var id = $(this).attr("id");
    if(id!=="product_mainCheckbox") {
        var allCheck = $("#product_mainCheckbox").prop('checked');
        if (allCheck == true) {
            $("#product_mainCheckbox").prop("checked",false);
        }
    }else{
        var allCheck = $("#product_mainCheckbox").prop('checked');
        var obj = document.getElementsByClassName("product_check");
        var k;
        for(k in obj){
            obj[k].checked=allCheck;
        }
    }
});

function edit_product_upload_check(){

    validator_Init2();
    var bootstrapValidator = $('#editProductForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit_edit();
    }
}
//
function uploadSubmit_edit(){

    $("#close_product_edit_modal").click();
    $("#sk-three-bounce").show();
    setTimeout(function(){
        $("#editProductForm").ajaxSubmit(function(message) {

            $("#rightContainer").html(message);
            // if(message=='success')
            //     window.location.href="/registry/step3";
            // else{
            //     swal({
            //         title: "Error!",
            //         text:message,
            //         icon: "error",
            //         button: "确定",
            //     });
            // }
        });
    },500);

}
