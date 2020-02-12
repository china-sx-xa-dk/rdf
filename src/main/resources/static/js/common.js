//查看详情
function openStockInfo(context, stockId) {
    $.jBox(
        "iframe:" + context + "product/stockDetail?stockId=" + stockId,
        {
            title: "库存详情查看",
            width: 1000,
            height: 500,
            buttons: {
                '关闭': true
            },
            showClose: true
        });
}

//查看订单详情
function openOrderInfo(context, orderId) {
    $.jBox(
        "iframe:" + context + "parentOrder/orderDetail?id=" + orderId,
        {
            title: "订单详情查看",
            width: 1000,
            height: 500,
            buttons: {
                '关闭': true
            },
            showClose: true
        });
}


function onpickingHandler($dp,opt) {
    var datetimeStr = $dp.cal.getNewDateStr();
    var dateTime =new Date(datetimeStr);
    $("#"+opt.name).val(dateTime.getTime())
}


function occurredConvert() {
    var datetimeStr=$("#occurredDate").val();
    $("#occurred").val(new Date(datetimeStr).getTime());
}