



(function() {

    $(document).on('click', '.closeLayerOut', function () {
        // layer.closeAll();
    });
    $(document).on('click', '.closeLayerIframe', function () {
        // var index = parent.layer.getFrameIndex(window.name);
        // parent.layer.close(index);
        // // window.parent.location.reload();
        // // window.top.location.href = window.top.location.href;
    });

    //此种必须写在 (function() { 中
    // 此处调用的就是我们写好的引入的方法，会将带有name的input，全部转为JSON提交表单
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    //form表单参数序列化为json
    (function($){
        $.fn.serializeJson=function(){
            var serializeObj={};
            var array=this.serializeArray();
            var str=this.serialize();
            $(array).each(function(){
                if(serializeObj[this.name

                    ]){
                    if($.isArray(serializeObj[this.name

                        ])){
                        serializeObj[this.name

                            ].push(this.value);
                    }else{
                        serializeObj[this.name

                            ]=[serializeObj[this.name

                            ],this.value];
                    }
                }else{
                    serializeObj[this.name

                        ]=this.value;
                }
            });
            return serializeObj;
        };
    })(jQuery);





})

//https://www.jianshu.com/p/e3bb805fb537
//https://www.jianshu.com/p/34418c94a505 https://www.jianshu.com/p/a323c32a1904 https://www.jianshu.com/p/5dc83169ee4f
// 获取地址栏参数
function getQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  unescape(r[2]);
    return null;
}
//字符串是否是空
function  isStrIsNull(str){
    if(str==null||str==""||str==undefined){return true};
    return false;
}

//字符串json转数组json
function strToJson(str){
    return JSON.parse(str);
}


/**
 * 公用函数 时间戳转时间格式
 * @param timestamp
 * @returns {String}
 */
function timestampToTime(timestamp) {
    // var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear();
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    D = date.getDate();
    if (D < 10) {
        D = "0" + D
    }
    h = date.getHours();
    if (h < 10) {
        h = "0" + h
    }
    m = date.getMinutes();
    if (m < 10) {
        m = "0" + m
    }
    s = date.getSeconds();
    if (s < 10) {
        s = "0" + s
    }
    return Y + "-" + M + "-" + D + " " + h + ":" + m + ":" + s;
}


//去除空格  type 1-所有空格  2-前后空格  3-前空格 4-后空格
function trim(str,type){
    switch (type){
        case 1:return str.replace(/\s+/g,"");
        case 2:return str.replace(/(^\s*)|(\s*$)/g, "");
        case 3:return str.replace(/(^\s*)/g, "");
        case 4:return str.replace(/(\s*$)/g, "");
        default:return str;
    }
}

//数组去重复
function removeReapt(arrOld){
    var arr=[];
    for(var i=0,len=arrOld.length;i<len;i++){
        if(arr.indexOf(arrOld[i])==-1){
            arr.push(arrOld[i]);
        }
    }
    return arr;
}
//求和
function sumArr(arr){
    var sumText=0;
    for(var i=0,len=arr.length;i<len;i++){
        sumText+=arr[i];
    }
    return sumText
}
// 平均值
function covArr(arr){
    var sumText=sumArr(arr);
    var covText=sumText/arr.length;
    return covText
}