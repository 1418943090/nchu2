function  uploadSubmit(){
    document.getElementById("closemodal").click();
  setTimeout(function(){
     // document.getElementById("upForm").submit();//js原生方式表单提交
      $("#sk-three-bounce").show();
      $("#upForm").ajaxSubmit(function(message) {
          if(message=='success')
          {
              var url =  "/userPapers";
              setTimeout(function(){
                  $.ajax({
                      url: url,
                      async:true,
                      success: function(data){
                          $("#rightContainer").html(data);
                      },
                      error: function() {
                          swal({
                              title: "服务器处理错误",
                              text: "",
                              icon: "error",
                              button: "确定",
                          });
                      }
                  });
              },1000);
          }
          else{
              //  toastr.warning(message);
              swal({
                  title: "上传失败",
                  text: "",
                  icon: "error",
                  button: "确定",
              });
          }
      });
     // $("#upForm").submit();//或者jQuery方式,二选一，不过现在已经没啥项目不引入jQuery了吧。。。
  },500);

}
function  updateSubmit(){
    document.getElementById("closeupdatemodal").click();
    setTimeout(function(){
        document.getElementById("updateForm").submit();//js原生方式表单提交
        //$("#updateForm").submit();//或者jQuery方式,二选一，不过现在已经没啥项目不引入jQuery了吧。。。
    },500);
    $("#sk-three-bounce").show();
    var url = "/userPapers";
    setTimeout(function(){
        $.ajax({
            url: url,
            async:true,
            success: function(data){
                $("#rightContainer").html(data);
            },
            error: function() {
                window.location.href="/error";
            }
        });
    },1000);
}
