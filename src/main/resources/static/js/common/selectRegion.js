//选择行政区划公共方法

/**------------------------------------- 只选择省级 start ------------------------------------------*/

/**
 * 初始化省级下拉框(用于只选择省级行政区划时)
 * @param provinceValue 已选省级编码(可用于数据回显或设置默认值)
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 * @param inputId 当选择完成后要动态更新input值对应的元素ID
 */
function initProvince(provinceValue, provinceId, inputId) {
    layui.use(['form'], function () {
        var $ = layui.$, form = layui.form;
        selectProvince(form, provinceValue, provinceId);
        /*监听省select*/
        form.on('select(' + provinceId + ')', function (data) {
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                if (provinceSelectValue != "") {
                    var reginonInfo = $("#" + provinceId).find("option:selected").text();
                    $("#" + inputId).val(reginonInfo);
                }
            }
        });
    });
}

/**
 * 动态设置省级下拉框选项(用于只选择省级行政区划时)
 * @param form  layui内置form对象
 * @param provinceValue 已选省级编码
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectProvince(form, provinceValue, provinceId) {
    var ChineseDistricts = districts();
    var provinces = ChineseDistricts["86"];
    var province = $('#' + provinceId);
    province.empty();
    province.append('<option value="' + "" + '">' + "请选择省" + '</option>');
    for (key in provinces) {
        if (key == provinceValue) {
            province.append('<option selected value="' + key + '">' + provinces[key] + '</option>');
        } else {
            province.append('<option value="' + key + '">' + provinces[key] + '</option>');
        }
    }
    form.render('select');
}

/**------------------------------------- 只选择省级 end ------------------------------------------*/


/**------------------------------------- 省市两级联动选择 start ------------------------------------------*/

/**
 * 初始化市级下拉框(用于省市两级联动选择时)
 * @param provinceValue 已选省级编码(可用于数据回显或设置默认值)
 * @param cityValue 已选市级编码(可用于数据回显或设置默认值)
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 * @param inputId 当选择完成后要动态更新input值对应的元素ID
 */
function initCity(provinceValue, cityValue, provinceId, cityId, inputId) {
    layui.use(['form'], function () {
        var $ = layui.$, form = layui.form;
        selectProvinceForCity(form, provinceValue, cityValue, provinceId, cityId);
        /*监听省select*/
        form.on('select(' + provinceId + ')', function (data) {
            selectProvinceForCity(form, data.value, "", provinceId, cityId);
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                var citySelectValue = $("#" + cityId).find("option:selected").val();
                var reginonInfo = "";
                if (provinceSelectValue != "") {
                    reginonInfo = $("#" + provinceId).find("option:selected").text();
                    if (citySelectValue != "") {
                        reginonInfo = reginonInfo + $("#" + cityId).find("option:selected").text();
                    }
                }
                $("#" + inputId).val(reginonInfo);
            }
        });
        /*监听市select*/
        form.on('select(' + cityId + ')', function (data) {
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                var citySelectValue = $("#" + cityId).find("option:selected").val();
                var reginonInfo = "";
                if (provinceSelectValue != "") {
                    reginonInfo = $("#" + provinceId).find("option:selected").text();
                    if (citySelectValue != "") {
                        reginonInfo = reginonInfo + $("#" + cityId).find("option:selected").text();
                    }
                }
                $("#" + inputId).val(reginonInfo);
            }
        });
    });
}

/**
 * 动态设置省级下拉框选项(用于省市两级联动选择时)
 * @param form  layui内置form对象
 * @param provinceValue 已选省级编码
 * @param cityValue 已选市级编码
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectProvinceForCity(form, provinceValue, cityValue, provinceId, cityId) {
    var ChineseDistricts = districts();
    var provinces = ChineseDistricts["86"];
    var province = $('#' + provinceId);
    province.empty();
    province.append('<option value="' + "" + '">' + "请选择省" + '</option>');
    for (key in provinces) {
        if (key == provinceValue) {
            province.append('<option selected value="' + key + '">' + provinces[key] + '</option>');
        } else {
            province.append('<option value="' + key + '">' + provinces[key] + '</option>');
        }
    }
    form.render('select');
    selectCity(form, provinceValue, cityValue, cityId);
}

/**
 * 动态设置市级下拉框选项(用于省市两级联动选择时)
 * @param form  layui内置form对象
 * @param provinceValue 已选省级编码
 * @param cityValue 已选市级编码
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectCity(form, provinceValue, cityValue, cityId) {
    var ChineseDistricts = districts();
    var citys = ChineseDistricts[provinceValue];
    var city = $('#' + cityId);
    city.empty();
    city.append('<option value="' + "" + '">' + "请选择市" + '</option>');
    for (key in citys) {
        if (key == cityValue) {
            city.append('<option selected value="' + key + '">' + citys[key] + '</option>');
        } else {
            city.append('<option value="' + key + '">' + citys[key] + '</option>');
        }
    }
    form.render('select');
}

/**------------------------------------- 省市两级联动选择 end ------------------------------------------*/


/**------------------------------------- 省市县三级联动选择 start ------------------------------------------*/

/**
 * 初始化县级下拉框(用于省市县三级联动选择时)
 * @param provinceValue 已选省级编码(可用于数据回显或设置默认值)
 * @param cityValue 已选市级编码(可用于数据回显或设置默认值)
 * @param countyValue 已选县级编码(可用于数据回显或设置默认值)
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 * @param countyId 要设置初始化县级下拉框的元素ID(请保证id与lay-filter一致)
 * @param inputId 当选择完成后要动态更新input值对应的元素ID
 */
function initCounty(provinceValue, cityValue, countyValue, provinceId, cityId, countyId, inputId) {
    layui.use(['form'], function () {
        var $ = layui.$, form = layui.form;
        selectProvinceForCounty(form, provinceValue, cityValue, countyValue, provinceId, cityId, countyId);
        /*监听省select*/
        form.on('select(' + provinceId + ')', function (data) {
            selectCityForCounty(form, data.value, "", "", cityId, countyId);
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                var citySelectValue = $("#" + cityId).find("option:selected").val();
                var countySelectValue = $("#" + countyId).find("option:selected").val();
                var reginonInfo = "";
                if (provinceSelectValue != "") {
                    reginonInfo = $("#" + provinceId).find("option:selected").text();
                    if (citySelectValue != "") {
                        reginonInfo = reginonInfo + $("#" + cityId).find("option:selected").text();
                        if (countySelectValue != "") {
                            reginonInfo = reginonInfo + $("#" + countyId).find("option:selected").text();
                        }
                    }
                }
                $("#" + inputId).val(reginonInfo);
            }
        });
        /*监听市select*/
        form.on('select(' + cityId + ')', function (data) {
            selectCounty(form, data.value, "", countyId);
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                var citySelectValue = $("#" + cityId).find("option:selected").val();
                var countySelectValue = $("#" + countyId).find("option:selected").val();
                var reginonInfo = "";
                if (provinceSelectValue != "") {
                    reginonInfo = $("#" + provinceId).find("option:selected").text();
                    if (citySelectValue != "") {
                        reginonInfo = reginonInfo + $("#" + cityId).find("option:selected").text();
                        if (countySelectValue != "") {
                            reginonInfo = reginonInfo + $("#" + countyId).find("option:selected").text();
                        }
                    }
                }
                $("#" + inputId).val(reginonInfo);
            }
        });
        /*监听县select*/
        form.on('select(' + countyId + ')', function (data) {
            if (inputId != "") {
                var provinceSelectValue = $("#" + provinceId).find("option:selected").val();
                var citySelectValue = $("#" + cityId).find("option:selected").val();
                var countySelectValue = $("#" + countyId).find("option:selected").val();
                var reginonInfo = "";
                if (provinceSelectValue != "") {
                    reginonInfo = $("#" + provinceId).find("option:selected").text();
                    if (citySelectValue != "") {
                        reginonInfo = reginonInfo + $("#" + cityId).find("option:selected").text();
                        if (countySelectValue != "") {
                            reginonInfo = reginonInfo + $("#" + countyId).find("option:selected").text();
                        }
                    }
                }
                $("#" + inputId).val(reginonInfo);
            }
        });
    });
}

/**
 * 动态设置省级下拉框选项(用于省市县三级联动选择时)
 * @param form  layui内置form对象
 * @param provinceValue 已选省级编码
 * @param cityValue 已选市级编码
 * @param countyValue 已选县级编码
 * @param provinceId 要设置初始化省级下拉框的元素ID(请保证id与lay-filter一致)
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 * @param countyId 要设置初始化县级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectProvinceForCounty(form, provinceValue, cityValue, countyValue, provinceId, cityId, countyId) {
    var ChineseDistricts = districts();
    var provinces = ChineseDistricts["86"];
    var province = $('#' + provinceId);
    province.empty();
    province.append('<option value="' + "" + '">' + "请选择省" + '</option>');
    for (key in provinces) {
        if (key == provinceValue) {
            province.append('<option selected value="' + key + '">' + provinces[key] + '</option>');
        } else {
            province.append('<option value="' + key + '">' + provinces[key] + '</option>');
        }
    }
    form.render('select');
    selectCityForCounty(form, provinceValue, cityValue, countyValue, cityId, countyId);
}

/**
 * 动态设置市级下拉框选项(用于省市县三级联动选择时)
 * @param form  layui内置form对象
 * @param provinceValue 已选省级编码
 * @param cityValue 已选市级编码
 * @param countyValue 已选县级编码
 * @param cityId 要设置初始化市级下拉框的元素ID(请保证id与lay-filter一致)
 * @param countyId 要设置初始化县级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectCityForCounty(form, provinceValue, cityValue, countyValue, cityId, countyId) {
    var ChineseDistricts = districts();
    var citys = ChineseDistricts[provinceValue];
    var city = $('#' + cityId);
    city.empty();
    city.append('<option value="' + "" + '">' + "请选择市" + '</option>');
    for (key in citys) {
        if (key == cityValue) {
            city.append('<option selected value="' + key + '">' + citys[key] + '</option>');
        } else {
            city.append('<option value="' + key + '">' + citys[key] + '</option>');
        }
    }
    form.render('select');
    selectCounty(form, cityValue, countyValue, countyId);
}

/**
 * 动态设置县级下拉框选项(用于省市县三级联动选择时)
 * @param form  layui内置form对象
 * @param cityValue 已选市级编码
 * @param countyValue 已选县级编码
 * @param countyId 要设置初始化县级下拉框的元素ID(请保证id与lay-filter一致)
 */
function selectCounty(form, cityValue, countyValue, countyId) {
    var ChineseDistricts = districts();
    var countys = ChineseDistricts[cityValue];
    var county = $('#' + countyId);
    county.empty();
    county.append('<option value="' + "" + '">' + "请选择县/区" + '</option>');
    for (key in countys) {
        if (key == countyValue) {
            county.append('<option selected value="' + key + '">' + countys[key] + '</option>');
        } else {
            county.append('<option value="' + key + '">' + countys[key] + '</option>');
        }
    }
    form.render('select');
}

/**------------------------------------- 省市县三级联动选择 end ------------------------------------------*/
