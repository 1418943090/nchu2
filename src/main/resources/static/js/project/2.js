

    function validator_Init() {
        $('#editProjectForm').bootstrapValidator({
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
                            min: 5,
                            max: 1500,
                            message: '项目内容长度范围为5~1500'
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


                funding: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入项目经费哦'
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


function  checkbox_check(list){


        var obj = document.getElementsByClassName("pro");
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
                title: "请选择要编辑的项目!",
                text: "",
                icon: "warning",
                button: "确定",
            });
        }
        else if(num>1){
            swal({
                title: "一次只能编辑一个项目!",
                text: "",
                icon: "warning",
                button: "确定",
            });
        }
        else{
           // updateFormvalidator_Init();
           date_init(list,id);
            $('#editProjectModal').modal('show');
        }
}

function date_init(list,id){
    for(k in list){
        if(list[k].id==id){
            $("#edit_id").prop("value",list[k].id);
            $("#edit_name").prop("value",list[k].name);
            $("#edit_description").prop("value",list[k].description);
            $("#edit_content").prop("value",list[k].content);
            $("#edit_principal").prop("value",list[k].principal);
            $("#edit_member").prop("value",list[k].member);

            $("#edit_funding").prop("value",list[k].funding);
           // var no1 = document.getElementById("edit_type");
            if(list[k].type == "科研项目")
               edit_type[0].selected = true;
            if(list[k].type == "三小项目")
                edit_type[1].selected = true;
            if(list[k].type == "横向项目")
                edit_type[2].selected = true;

            //var no2 = document.getElementById("edit_select_unit");
            if(list[k].unit=="(元)")
                edit_select_unit[0].selected = true;
            else edit_select_unit[1].selected = true;


            if(list[k].date==""){

                edit_date_type[1].selected = true;
                $("#edit_date1").hide();
                $("#edit_date2").show();
                $("#edit_dateA").prop("value",list[k].dateA);
                $("#edit_dateB").prop("value",list[k].dateB);
            }
            else{
                $("#edit_date").prop("value",list[k].date);
            }
        }
    }
}


$('.card').on('click','input:checkbox',function () {

    var id = $(this).attr("id");
    if(id!=="pro_mainCheckbox") {
        var allCheck = $("#pro_mainCheckbox").prop('checked');
        if (allCheck == true) {
            $("#pro_mainCheckbox").prop("checked",false);
        }
    }else{
        var allCheck = $("#pro_mainCheckbox").prop('checked');
        var obj = document.getElementsByClassName("pro");
        var k;
        for(k in obj){
            obj[k].checked=allCheck;
        }
    }
});

function edit_project_upload_check(){

    validator_Init();
    var bootstrapValidator = $('#editProjectForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        uploadSubmit_edit();
    }
}


    function edit_date_check(){
        var type = $('#edit_date_type option:selected').text();
        if(type=="立项时间")
        {
            // alert($("#dateA").prop("value"));
            if($('#edit_date').prop("value")=="")
            {
                document.getElementById("edit_date_error_message").innerText= "你还没有输入项目立项日期哦";
                return false;
            }
            return true;
        }
        if(type=="起止时间")
        {
            if($("#edit_dateA").prop("value")=="")
            {
                document.getElementById("edit_date_error_message").innerText= "你还没有输入项目立项日期哦";
                return false;
            }
            else  if($("#edit_dateB").prop("value")=="") {
                document.getElementById("edit_date_error_message").innerText = "你还没有输入项目结题日期哦";
                return false;
            }
            var start = $("#edit_dateA").prop("value");
            var end  = $("#edit_dateB").prop("value");
            var a = new Date(start).getTime();
            var b = new Date(end).getTime();
            if(a>=b){
                document.getElementById("edit_date_error_message").innerText = "起始时间不能大于等于结题时间哦";
                return false;
            }
        }
        return true;
    }

function uploadSubmit_edit(){

    if(edit_date_check()) {


        $("#close_project_edit_modal").click();
        $("#sk-three-bounce").show();
        setTimeout(function () {
            $("#editProjectForm").ajaxSubmit(function (message) {

                $("#rightContainer").empty();
                $("#rightContainer").html(message);
                //window.location="/projectCenter/edit";
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
        }, 500);
    }
}
