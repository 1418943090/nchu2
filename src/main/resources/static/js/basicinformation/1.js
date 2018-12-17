

$(function() {
    function pic_validator() {
        $('#editPicForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                pic: {
                    validators: {
                        notEmpty: {
                            message: '你还没有选择头像文件哦'
                        },
                        regexp: {
                            regexp: /^.*?(png|jpg)$/,
                            message: '图像必须是png或jpg格式的哦'
                        }
                    }
                }
            }
        });
    }
    pic_validator();
});
function edit_pic_check(){
    var bootstrapValidator = $('#editPicForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
          edit_pic_submit();
    }
}

function edit_pic_submit(){

    $('#editPicModal').modal("hide");
    setTimeout(function () {
        $("#editPicForm").ajaxSubmit(function(message) {
            $("#rightContainer").html(message);
        });
    }, 500);
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}

$('.btn-pic').click(function(){

    $('#editPicModal').modal("show");

});

