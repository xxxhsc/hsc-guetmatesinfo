function statusFormatter(value,row,index) {
    if (value == 1)
        return "启用";
    else
        return "禁用";
}
function actionFormatter(value, row, index) {
    var status = row.status;
    var id = row.fid;
    var res = '';
    if (status == 1)
        res += ' <button type="button" class="btn btn-default" onclick="changeSate(\'' + id + '\',0)" >禁用</button>';
    else
        res += ' <button typ-e="button" class="btn btn-info" onclick="changeSate(\'' + id + '\',1)" >启用</button>';
    res += ' <button typ-e="button" class="btn btn-danger" onclick="deleteOne(\'' + id + '\')">删除</button>';
    return res;
}

function changeSate(id, status) {
    $.ajax({
        type: 'POST',
        url: "changeState",
        data: {
            fid: id,
            status: status
        },
        success: function () {
            $('#adminTable').bootstrapTable('refresh', {url: '/admin/file/list'});
        },
        dataType: "json"
    });
}

function closeModal() {
    $('#modal-form').modal('hide');
}

function deleteOne(id) {
    layer.confirm('您确定要删除此行数据？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: 'POST',
            url: "deleteOne",
            data: {
                fid: id,
            },
            success: function (status) {
                if (status == 1) {
                    layer.msg('删除成功', {icon: 1});
                    $('#adminTable').bootstrapTable('refresh', {url: '/admin/file/list'});
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
