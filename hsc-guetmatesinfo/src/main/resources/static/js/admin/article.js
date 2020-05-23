/*******文章相关********/

function createCities(){



    var id=$('#noticeType1').val();
    document.getElementById("noticeType2").length=0;
    $.ajax({
        type: 'POST',
        url: "selectByParentId",
        data: {
            id: id
        },
        success: function (data) {
            var typelist = eval("("+data + ")");//json为接收的后台返回的数据属；
            for (var key in typelist){
                var label=typelist[key].label;
                var tid=typelist[key].id;
                $('#noticeType2').append("<option text="+label+"  value="+tid+">"+label+"</option>");
            }
        },
        dataType: "json"
    });
}
