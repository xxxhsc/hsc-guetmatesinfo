/**
 * Created by hsc on 2020/3/10.
 * link:http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation
 */
function handlerData(res) {
    return res.records;
}

function stateFormatter(value,row,index) {
    if (value == 1)
        return "启用";
    else
        return "禁用";
}
