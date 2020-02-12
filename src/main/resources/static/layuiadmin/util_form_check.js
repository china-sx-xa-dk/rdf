/**
 @Name：全局自定义表单校验
 */
var util_from_check = {
    chk_null: function(value, item) {//校验input或textarea不为空  //value：表单的值、item：表单的DOM对象
        if(value.trim() == ''){
            var msg = $(item).attr("placeholder");
            return msg;
        }
    },
    chk_all_number: function (value, item) {//不能全为数字  //value：表单的值、item：表单的DOM对象
        if (/^\d+\d+\d$/.test(value)) {
            return '不能全为数字';
        }
    },
    chk_special_char: function (value, item) {//特殊字符校验
        if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
            return '不能含有特殊字符';
        }
        if (/(^\_)|(\__)|(\_+$)/.test(value)) {
            return '首尾不能出现下划线\'_\'';
        }
    },
    chk_len_6_16: function (value, item) {//长度6-16位校验
        if (value.length < 6 || value.length > 16) {
            return '必须为6-16位';
        }
    },
    chk_number_0_1: function (value, item) {//数字0-1之间验证校验
        if (value < 0 || value > 1) {
            return '必须为0-1之间';
        }
    },
    chk_number_0_9999: function (value, item) {//大于0小于等于9999
        if (value < 0 || value > 9999) {
            return '必须为0-9999之间';
        }
    },
    chk_just_number: function (value, item) {
        if (!/^[1-9]\d*$/.test(value)) {
            return '数值必须为正整数';
        }
    },
    chk_number_0_9999999999: function (value, item) {//大于等于0小于等于9999999999
        if (value < 0 || value > 9999999999) {
            return '必须为0-9999999999之间';
        }
    },
    chk_phone_number: function (value, item) {
        if (!/^(0|86|17951)?(13[0-9]|15[012356789]|17[01678]|18[0-9]|14[57])[0-9]{8}$/.test(value) && !/^(0[0-9]{2,3}\-)([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value)) {
            return "电话号码格式错误";
        }
    },
    chk_tel_phone_number: function (value, item) {
        if (!/^1[3,4,5,7,8]\d{9}$/.test(value)) {
            return "手机号码格式不正确";
        }
    },
    chk_just_number_0_9: function (value, item) {
        if (!/^[0-9]\d*$/.test(value)) {
            return '数值必须为正整数';
        }
    },
    chk_number_1_9999999999:function (value, item) {//大于等于1小于等于9999999999
        if (value < 1 || value > 9999999999) {
            return '必须为1-9999999999之间';
        }
    },
    chk_id_card:function(value,item){
        if(value != null && value.trim() != ''){
            if (!/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value)){
                return '身份证格式错误';
            }
        }
    },
    chk_number_or_null:function (value, item) { // 验证正整数，可为空
        if( value && (!/^[1-9]\d*$/.test(value)) ){
            return '数值必须为正整数';
        }
    },
    chk_number_0_999999999: function (value, item) {//大于等于0小于等于999999999
        if (value < 0 || value > 999999999) {
            return '必须为0-999999999之间';
        }
    },
    chk_number_integer:function(value,item){
        if (value && (!/^[+]{0,1}(\d+)$/.test(value)) ) {
            return '数值必须为正整数';
        }
    }
}
