
$(function(){

    function validator_Init() {
        $('#editContentForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                editContent: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入新闻内容哦'
                        },
                        stringLength: {
                            min: 50,
                            max: 2000,
                            message: '新闻内容长度范围为50~2000'
                        }
                    }
                  }
                }
        });
    }
validator_Init();

});

function upload_check_edit(){
    var bootstrapValidator = $('#editContentForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit();
    }
}

function uploadSubmit(){

    $("#closemodal_edit").click();
    $("#sk-three-bounce").show();
    setTimeout(function(){
        $("#editContentForm").ajaxSubmit(function(message) {
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