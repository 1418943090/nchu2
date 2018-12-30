
$("select#date_type").change(function(){
    //var list = [[${list}]];
    var type = $('#date_type option:selected').text();
    if(type=="立项时间")
    {
        $("#dateA").prop("value",null);
        $("#dateB").prop("value",null);
        document.getElementById("date_error_message").innerText= "";
        $("#date2").hide();
        $("#date1").show();
    }
    if(type=="起止时间")
    {
        $("#date").prop("value",null);
        document.getElementById("date_error_message").innerText= "";
        $("#date1").hide();
        $("#date2").show();
    }
});