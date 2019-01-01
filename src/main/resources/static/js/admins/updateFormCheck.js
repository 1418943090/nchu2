
$(function(){
    function updateFormvalidator_Init() {
        $('#updateForm').bootstrapValidator({
            feedbackIcons: {
                 valid: 'glyphicon glyphicon-ok',
                 invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                updatePublishDate:{
                    trigger:'click',
                    validators:{
                        notEmpty:{
                            message:'请输入论文发表时间'
                        }
                    }
                },

                updateTitle: {
                    validators: {
                        notEmpty: {
                            message: '你还没有输入论文标题哦'
                        },
                        stringLength: {
                            min: 3,
                            max: 400,
                            message: '论文标题长度在3~400个字符哦'
                        }
                    }
                },
                updateFile: {
                    validators: {
                        regexp: {
                            regexp: /^.*\.pdf$/,
                            message: '论文格式必须是PDF格式的哦'
                        }
                    }
                },
            }
        });
    }
//ajax刷新页面后，所有checkbox全部更新为未选中，不然的话按钮绑定的是刷新之前的checkbox导致功能异常
    var obj = document.getElementsByClassName("cb");
    var k;
    for(k in obj){
        obj[k].checked=false;
    }

     $('.btn-upd').click(function(){

         var obj = document.getElementsByClassName("cb");
         var num=0;
         var k;
         var id;
         var title;
         var publishDate;
         for(k in obj){
             if(obj[k].checked && obj[k].id!=undefined){
                 num++;
                 id=obj[k].id;
                 title=obj[k].title;
                 publishDate = obj[k].value;
             }
         }
         if(num==0){
             swal({
                 title: "请选择要编辑的论文!",
                 text: "",
                 icon: "warning",
                 button: "确定",
             });
         }
         else if(num>1){
             swal({
                 title: "一次只能编辑一篇论文!",
                 text: "",
                 icon: "warning",
                 button: "确定",
             });
         }
         else{
             updateFormvalidator_Init();
             document.getElementById("updateTitle").value = title;
             document.getElementById("updateId").value = id;
             document.getElementById("updateUsername").value =  $("#user").attr("username");
             document.getElementById("updatePublishDate").value = publishDate;
             $('#updateModal').modal('show');
         }
     });
});
function update_check(){
    var bootstrapValidator = $('#updateForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if(bootstrapValidator.isValid()){//如果校验成功后执行的操作
        updateSubmit();
    }
}
