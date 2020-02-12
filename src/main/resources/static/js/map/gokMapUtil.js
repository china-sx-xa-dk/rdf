/**
 * 百度地图相关工具方法，使用前先导入百度地图相关js
 */

/**
 * 项目域名
 * @type {null}
 */
var map_util_base_path = null;

/**
 * 百度地图 map 对象
 * @type {null}
 */
var map_util_obj = null;

/**
 * 定义当地图级别变化到该级别进行查询
 */
var map_util_zoom = 14;

/**
 * 覆盖物默认参数，当没有数据时添加当前数据
 * @type {{strokeColor: string, strokeWeight: number, strokeOpacity: number}}
 */
var cover_default_parameter = {strokeColor: "blue", strokeWeight: 2, strokeOpacity: 0.5};

/**
 * 是否需要鼠标右键，默认需要，不需要传 false
 *
 * @type {boolean}
 */
var map_need_right_click = true;

/**
 * 根据点信息绘制覆盖物，并开启右键编辑功能
 *
 * @param mapObj 百度地图map对象，必传
 * @param arrSpot 点信息，必传
 * @param arrSpot needRightClick 是否需要鼠标右键，默认需要，不需要传 false
 * arrSpot数据结构 [{arr:[],obj:{},data:{}}]
 * arrSpot参数介绍 外层数组为多个覆盖物, 循环体为覆盖物的数据，arr为经纬度(lon: Number, lat: Number)，obj为构造函数的参数
 *
 * obj 区域构造函数的参数介绍
 * 百度地图参数点信息 http://lbsyun.baidu.com/cms/jsapi/reference/jsapi_reference.html#a3b15
 * strokeColor    String    边线颜色
 * fillColor    String    填充颜色。当参数为空时，折线覆盖物将没有填充效果
 * strokeWeight    Number    边线的宽度，以像素为单位
 * strokeOpacity    Number    边线透明度，取值范围0 - 1
 * fillOpacity    Number    填充的透明度，取值范围0 - 1
 * strokeStyle    String    边线的样式，solid或dashed
 * enableMassClear    Boolean    是否在调用map.clearOverlays清除此覆盖物，默认为true
 * enableEditing    Boolean    是否启用线编辑，默认为false
 * enableClicking    Boolean    是否响应点击事件，默认为true
 * data 区域字段，与表字段一致
 */
function map_draw_region(mapObj, arrSpot, needRightClick) {
    // 验证参数，为 null 或者 数组为空 方法不执行
    if (!mapObj || !arrSpot || arrSpot.length == 0) {
        console.log("空数据");
        return false;
    }

    var markers = []; // 获取所有覆盖物的中心点，然后聚合

    // 绘制
    for (var i = 0; i < arrSpot.length; i++) { // 遍历所有覆盖物数组
        (function (item) {
            var arr = []; // 覆盖物的所有点数据
            var arrLength = item.arr.length; // 点数量
            if (arrLength == 0) { // 当没有点数据，进入下次循环
                return false;
            }
            for (var j = 0; j < arrLength; j++) { // 遍历所有点数据
                arr.push(new BMap.Point(item.arr[j].lon, item.arr[j].lat));
            }
            var obj = {}; // 覆盖物对象，先获取默认数据，在添加自定义的数据
            for (var key in cover_default_parameter) { // 获取默认数据
                obj[key] = cover_default_parameter[key];
            }
            if (item.obj) { // 存在该对象进行遍历
                for (var key in item.obj) { // 遍历覆盖物对象
                    obj[key] = item.obj[key];
                }
            }
            var polyline = new BMap.Polygon(arr, obj); // 创建多边形对象
            if (true == map_need_right_click) {
                mouse_right_click(mapObj, polyline, item.data); // 右键
            }
            var myIcon = new BMap.Icon("/", new BMap.Size(0, 0));
            var marker = new BMap.Marker(polyline.getBounds().getCenter(), {"icon": myIcon}); // 获取覆盖物中心点 生成marker
            markers.push(marker); // 获取覆盖物的中心点去做点聚合
            mapObj.addOverlay(polyline); // 创建多边形
        })(arrSpot[i]);
    }

    //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可
    var markerClusterer = new BMapLib.MarkerClusterer(mapObj, {"markers": markers});

    // 返回成功
    return true;
}


/**
 * 鼠标右键方法
 *
 * @param mapObj 百度地图map对象，必传
 * @param polyline 覆盖物的对象，必传
 * @param areaInfo 覆盖物的对象
 */
function mouse_right_click(mapObj, polyline, areaInfo) {
    // 验证参数，为 null 或者 数组为空 方法不执行
    if (!mapObj || !polyline) {
        console.log("空数据");
        return false;
    }

    var path = polyline.getPath(); // 多边形的点数组

    //创建右键菜单
    var polygonMenu = new BMap.ContextMenu(); // 创建存放右键的功能的菜单
    // 编辑操作
    polygonMenu.addItem(new BMap.MenuItem('编辑', function () {
        polyline.enableEditing(); // 开启编辑
    }));
    // 保存操作
    polygonMenu.addItem(new BMap.MenuItem('保存', function () {
        path = polyline.getPath();
        var reqUrl = map_util_base_path + "manage/area/jump_area_edit";
        reqUrl += "?areaPoint=" + path.length;
        reqUrl += "&areaPointArr=" + encodeURIComponent(JSON.stringify(path));
        if (areaInfo && areaInfo.areaId) {
            reqUrl += "&areaId=" + areaInfo.areaId;
        }
        layer.open({
            type: 2 //此处以iframe举例
            , title: '编辑区域'
            , area: ['60%', '60%']
            , content: reqUrl
            , end: function () {
                select_area_list(get_map_bounds(mapObj));
            }
        });
        polyline.disableEditing(); // 关闭编辑
    }));
    // 取消编辑操作
    polygonMenu.addItem(new BMap.MenuItem('取消', function () {
        polyline.setPath(path);
        polyline.disableEditing(); // 关闭编辑
    }));
    // 删除操作
    polygonMenu.addItem(new BMap.MenuItem('删除', function () {
        my_confirm({
            "msg": "确认删除区域？", "callback": function () {
                var url = map_util_base_path + "manage/area/del_area?areaId=" + areaInfo.areaId;
                $.ajax({
                    "type": "get", "url": url,
                    "success": function (result) {
                        if (result.code == '200') {
                            alert("删除成功");
                            select_area_list(get_map_bounds(mapObj));
                        } else {
                            alert(result.message);
                        }
                    }
                });
            }
        });
    }));

    polyline.addContextMenu(polygonMenu);
}

/**
 * 监听地图级别
 *
 * @param mapObj 监听地图对象
 */
function listen_map_zoom(mapObj) {
    if (!mapObj) {
        return false;
    }
    mapObj.addEventListener("zoomend", function () {
        var zoom = mapObj.getZoom(); // 获取地图级别
        if (zoom == map_util_zoom) { // 执行查询
            select_area_list(get_map_bounds(mapObj));
        } else if (zoom < map_util_zoom) {
            mapObj.clearOverlays();
        }
    });
}

/**
 * 获取地图的可视化区域
 *
 * @param mapObj
 * 返回 可视化区域经纬度，分别为中心点、西南角和东北角
 */
function get_map_bounds(mapObj) {
    if (!mapObj) {
        return null;
    }
    var bounds = mapObj.getBounds(); // 可视化区域
    return {
        "center": mapObj.getCenter() // 中心点
        , "southWest": bounds.getSouthWest() // 获取西南角的经纬度(左下端点)
        , "northEast": bounds.getNorthEast() // 获取东北角的经纬度(右上端点)
    }
}

/**
 * 二次确认
 *
 * @param obj
 * 参数介绍
 * msg  提示文本
 * callback  回调方法
 */
function my_confirm(obj) {
    layer.confirm(obj.msg, {offset: 't'}, function (index) {
        obj.callback();
    });
}


/**
 * 查询指定区域的覆盖物信息
 *
 * @param obj 查询条件
 */
function select_area_list(obj) {
    var url = map_util_base_path + "manage/area/list";
    var data = null;
    if (obj) {
        data = JSON.stringify({
            "southWestLon": obj.southWest.lng
            , "southWestLat": obj.southWest.lat
            , "northEastLon": obj.northEast.lng
            , "northEastLat": obj.northEast.lat
        });
    }
    var loading = layer.msg('正在查询数据', {icon: 16, shade: 0.2, time: 0, offset: 't'});
    $.ajax({
        "type": "post", "url": url, "traditional": true, "data": data,
        "contentType": "application/json; charset=utf-8", "dataType": "json",
        "success": function (result) {
            layer.close(loading); // 关闭loading
            if (result.code == '200') {
                var retData = result.data;
                if (!retData || retData.length == 0) {
                    return false;
                }
                var arr = [];
                for (var i = 0; i < retData.length; i++) {
                    var item = retData[i];
                    var temp = {};
                    var areaPointArr = JSON.parse(item.areaPointArr);
                    for (var j = 0; j < areaPointArr.length; j++) {
                        if (temp.arr) {
                            temp.arr.push({"lon": areaPointArr[j].lng, "lat": areaPointArr[j].lat});
                        } else {
                            temp.arr = [{"lon": areaPointArr[j].lng, "lat": areaPointArr[j].lat}];
                        }
                        temp.data = item;
                    }
                    arr.push(temp);
                }
                map_util_obj.clearOverlays();
                map_draw_region(map_util_obj, arr);
            } else {
                alert(result.message);
            }
        }
    });
}
