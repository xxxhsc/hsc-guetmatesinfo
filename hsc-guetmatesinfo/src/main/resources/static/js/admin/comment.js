/*******用户相关********/


function stateFormatter(value,row,index) {
    if (value == 1)
        return "启用";
    else
        return "禁用";
}



function sexFormatter(value, row, index) {
    if (value == 1)
        return "男";
    else if (value == 0)
        return "女";
    else
        return "未知";
}

function actionFormatter(value, row, index) {
    var state = row.state;
    var cid = row.cid;
    var res = '';
    if (state == 1)
        res += ' <button type="button" class="btn btn-default" onclick="changeSate(\'' + cid + '\',0)" >禁用</button>';
    else
        res += ' <button typ-e="button" class="btn btn-info" onclick="changeSate(\'' + cid + '\',1)" >启用</button>';
    res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + cid + '\')">删除</button>';
    return res;
}

function changeSate(cid, state) {
    $.ajax({
        type: 'POST',
        url: "changeState",
        data: {
            cid: cid,
            state: state
        },
        success: function () {
            $('#adminTable').bootstrapTable('refresh', {url: '/admin/comment/list'});
        },
        dataType: "json"
    });
}

function closeModal() {
    $('#modal-form').modal('hide');
}

function deleteOne(cid) {
    layer.confirm('您确定要删除此行数据？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: 'POST',
            url: "deleteOne",
            data: {
                cid: cid,
            },
            success: function (status) {
                if (status == 1) {
                    layer.msg('删除成功', {icon: 1});
                    $('#adminTable').bootstrapTable('refresh', {url: '/admin/comment/list'});
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



/**
 * 列表单行点击事件
 */
// $('#adminTable').on('click-row.bs.table', function (row, $element) {
//     alert(row);
// })
