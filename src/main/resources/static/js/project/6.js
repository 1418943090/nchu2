
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

$("select#edit_date_type").change(function(){
    //var list = [[${list}]];
    var type = $('#edit_date_type option:selected').text();
    if(type=="立项时间")
    {
        $("#edit_dateA").prop("value",null);
        $("#edit_dateB").prop("value",null);
        document.getElementById("edit_date_error_message").innerText= "";
        $("#edit_date2").hide();
        $("#edit_date1").show();
    }
    if(type=="起止时间")
    {
        $("#edit_date").prop("value",null);
        document.getElementById("edit_date_error_message").innerText= "";
        $("#edit_date1").hide();
        $("#edit_date2").show();
    }
});