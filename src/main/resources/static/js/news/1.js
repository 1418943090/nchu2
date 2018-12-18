
$(function(){
    function validator_Init() {
        $('#addNewsForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                title: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入新闻标题哦'
                        },
                        stringLength: {
                            min: 3,
                            max: 60,
                            message: '论文标题长度在3~60个字符哦'
                        }
                    }
                },
                content: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入消息内容哦'
                        },
                        stringLength: {
                            min: 50,
                            max: 2000,
                            message: '新闻内容长度范围为50~2000'
                        }
                    }
                },
                pic: {
                    validators: {
                        notEmpty: {
                            message: '你还没选择配图哦!'
                        },
                        regexp: {
                            regexp: /^.*?(png|jpg)$/,
                            message: '图片必须是png或jpg格式的哦'
                        }
                    }
                }
            }
        });
    }
    $('.card').on('click','.btn-news-add',function() {
        validator_Init();
        $('#addNewsModal').modal('show');
    });

});

function upload_check_add(){
    // $("#upForm").data('bootstrapValidator').destroy();
    // $('#upForm').data('bootstrapValidator', null);
    var bootstrapValidator = $('#addNewsForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit_add();
    }
}
function uploadSubmit_add(){
    $("#closemodal_add").click();
    setTimeout(function(){
        $("#addNewsForm").ajaxSubmit(function(message) {

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