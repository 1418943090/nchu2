
$(function(){
    function validator_Init() {
        $('#addProductForm').bootstrapValidator({
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
                        notEmpty: {
                            message: '你还没选择配图哦!'
                        },
                        regexp: {
                            regexp: /^.*?(png|jpg)$/,
                            message: '图片必须是png或jpg格式的哦'
                        }
                    }
                },
                file_document: {
                    validators: {
                        notEmpty: {
                            message: '你还没选择产品介绍文档哦!'
                        },
                        regexp: {
                            regexp: /^.*?(pdf)$/,
                            message: '文档必须是pdf格式的哦'
                        }
                    }
                }
            }
        });
    }
    $('.card').on('click','.btn-product-add',function() {
        validator_Init();
        $('#addProductModal').modal('show');
    });

});

function upload_check_add(){
    // $("#upForm").data('bootstrapValidator').destroy();
    // $('#upForm').data('bootstrapValidator', null);
    var bootstrapValidator = $('#addProductForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit_add();
    }
}
function uploadSubmit_add(){
    $("#closemodal_add").click();
    $("#sk-three-bounce").show();
    setTimeout(function(){
        $("#addProductForm").ajaxSubmit(function(message) {

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



    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转

}