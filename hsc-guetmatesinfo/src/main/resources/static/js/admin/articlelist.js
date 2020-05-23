/*******用户相关********/


function stateFormatter(value,row,index) {
    if (value == 1)
        return "启用";
    else
        return "禁用";
}


function actionFormatter(value, row, index) {
    var status = row.status;
    var aid = row.aid;
    var res = '';
    if (status == 1)
        res += ' <button type="button" class="btn btn-default" onclick="changeSate(\'' + aid + '\',0)" >禁用</button>';
    else
        res += ' <button typ-e="button" class="btn btn-info" onclick="changeSate(\'' + aid + '\',1)" >启用</button>';
    res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + aid + '\')">删除</button>';
    return res;
}

function changeSate(aid, status) {
    $.ajax({
        type: 'POST',
        url: "updatestatus",
        data: {
            aid: aid,
            status: status
        },
        success: function () {
            $('#adminTable').bootstrapTable('refresh', {url: '/admin/article/articlelist'});
        },
        dataType: "json"
    });
}

function closeModal() {
    $('#modal-form').modal('hide');
}




function deleteOne(aid) {
    layer.confirm('您确定要删除此行数据？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: 'POST',
            url: "deleteOne",
            data: {
                aid: aid,
            },
            success: function (status) {
                if (status == 1) {
                    layer.msg('删除成功', {icon: 1});
                    $('#adminTable').bootstrapTable('refresh', {url: '/admin/article/articlelist'});
                }else {
                    layer.msg('删除失败', {icon: 1});
                }
            },
            dataType: "json"
        });

    }, function(){
        layer.msg('您已取消操作', {
            time: 1000, //20s后自动关闭
            btn: ['明白了', '知道了']
        });
    });
}

function createCities(){

    var id=$('#noticeType1').val();
    document.getElementById("noticeType2").length=0;
    $.ajax({
        type: 'POST',
        url: "selectByParentId",
        async: true,
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


function typeFormatter(value,row,index) {

    var pattern3 = new RegExp("[0-9]+");
if(pattern3.test(value)){
    var aid=value;
    var label;
    $.ajax({
        type: 'POST',
        url: "getArticleTypeById",
        async: false,
        data: {
            aid: aid
        },
        success: function (data) {
            label=data.toString();
            },
        dataType: "json"
    });
    return label;
    }
}




/**
 * 列表单行点击事件
 */
// $('#adminTable').on('click-row.bs.table', function (row, $element) {
//     alert(row);
// })
/*关于我们
新闻公告
校友风采
校友服务
校基金会
学校情况
总会简介
组织机构
地方分会
行业协会
规章制度
校友之家
联系我们
新闻动态
总会公告
活动预告
桂电人
校友企业
桂电故事
返校指南
校友卡申请
校友通讯
基金会概况
理事会构成
信息公开
捐赠指南
捐赠项目
捐赠鸣谢
报账流程
联系我们*/