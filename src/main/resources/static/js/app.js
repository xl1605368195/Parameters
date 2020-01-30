// ajax 请求数据补全输入框
$("#parameter").typeahead({
    source: function (query, process) {
        //query是输入的值
        $.ajax({
            url: "/complemente",
            type: "GET",
            dataType: "JSON",
            data: {"parameter": query},
            success: function (response) {
                if (response != null) {
                    process(response);
                }
            }
        })
    },
    minLength: 0,//键入字数多少开始补全
    showHintOnFocus: "true",//将显示所有匹配项
    fitToElement: true,//选项框宽度与输入框一致
    items: 5,//下拉选项中出现条目的最大数量。也可以设置为“all”
    autoSelect: true,//允许你决定是否自动选择第一个建议
    //这里一定要return，否则选中不显示，外加调用display的时候null reference错误。
    updater: function (item) {
        return item;
    },
    //返回选中的字符串
    displayText: function (item) {
        return item;
    },
    //选择项之后的事件，item是当前选中的选项
    afterSelect: function (item) {
        send(item);
    },
    delay: 10//在查找之间添加延迟
});

function send(value) {
    $.ajax({
        type: "GET", //请求方式
        contentType: "application/json;charset=UTF-8",  //请求的媒体类型
        url: "/query",  //请求地址
        data: {"parameter": value},    //参数值
        success: function (result) {  //请求成功
            $("#result").empty();
            var body =appendToTable(result);
            $("#result").append($(body));
             showSuccessNotify("success","参数["+value+"]查询成功");
        },
        error: function (e) {//请求失败，包含具体的错误信息
            alert("err");
        }
    });
}

function appendToTable(tmp) {
    var name = tmp.name;
    var versions = tmp.versions;
    var examples = tmp.examples;
    var type = tmp.type;
    var os = tmp.os;
    var meaning = tmp.meaning;
    var hanyi = tmp.hanyi;
    var use = tmp.use;
    var defaultValue = tmp.defaultValue;
    var valueType = tmp.valueType;
    var extend = tmp.extend;
    var body=$('<div class="panel-heading">'+name+'&nbsp;&nbsp;<span class="badge badge-primary">'+versions+'</span>&nbsp;&nbsp;<span class="badge label-info">'+defaultValue+'</span></div>\n' +
        '<div class="panel-body">\n' +
        '    <p><span class="label label-danger">例子</span>&nbsp;&nbsp;'+examples+'</p>\n' +
        '</div>\n' +
        '<ul class="list-group">\n' +
        '    <li class="list-group-item"><span class="label label-success">含义</span>&nbsp;&nbsp;'+hanyi+'</li>\n' +
        '    <li class="list-group-item"><span class="label label-warning">扩展</span>&nbsp;&nbsp;'+extend+'</li>\n' +
        '    <li class="list-group-item"><span class="label label-primary">使用</span>&nbsp;&nbsp;'+use+'</li>\n' +
        '</ul>'
    );
    return body;
}

// 参数诊断
$("#parameters").change(function(){
    var values=$("#parameters").val();
    $.ajax({
        type: "POST", //请求方式
        contentType: "application/json;charset=UTF-8",  //请求的媒体类型
        url: "/evaluate",  //请求地址
        data: {"parameters": values},    //参数值
        success: function (result) {  //请求成功
            showSuccessNotify("success","参数参数诊断完成");

        },
        error: function (e) {//请求失败，包含具体的错误信息
            alert("err");
        }
    });
})