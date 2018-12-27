
$(function(){
    function validator_Init() {
        $('#addProjectForm').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目名哦'
                        },
                        stringLength: {
                            min: 3,
                            max: 100,
                            message: '项目名长度在3~100个字符哦'
                        }
                    }
                },
                description: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目描述哦'
                        },
                        stringLength: {
                            min: 3,
                            max: 100,
                            message: '项目描述长度范围为3~100'
                        }
                    }
                },
                content: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目内容哦'
                        },
                        stringLength: {
                            min: 50,
                            max: 1500,
                            message: '项目内容长度范围为50~1500'
                        }
                    }
                },
                principal: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目负责人哦'
                        },
                        stringLength: {
                            min: 2,
                            max: 50,
                            message: '项目负责人长度范围为2~50'
                        }
                    }
                },
                member: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目成员哦(没有写无)'
                        },
                        stringLength: {
                            min: 1,
                            max: 100,
                            message: '项目成员长度范围为1~100'
                        }
                    }
                },
                date: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目立项时间哦'
                        }
                    }
                },

                funding: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目经费哦'
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
                            message: '你还没选择项目文档哦!'
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
    $('.card').on('click','.btn-project-add',function() {
        validator_Init();
        $('#addProjectModal').modal('show');
    });

});

function add_project_upload_check(){
    // $("#upForm").data('bootstrapValidator').destroy();
    // $('#upForm').data('bootstrapValidator', null);
    var bootstrapValidator = $('#addProjectForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit_add();
    }
}

function uploadSubmit_add(){
    $("#close_project_add_modal").click();
    $("#sk-three-bounce").show();
    setTimeout(function(){
        $("#addProjectForm").ajaxSubmit(function(message) {
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