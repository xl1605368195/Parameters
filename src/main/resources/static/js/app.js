// logout
$("#logout").on(ace.click_event, function() {
    bootbox.confirm("确定退出?", function(result) {
        if(result) {$.ajax({url: "/user/logout/", type: "GET", success: function (response) {window.location.reload();}});}
    });
});

var showSuccessNotify = function showSuccessNotify(title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({
        title: title,
        text: message,
        hide: true,
        delay: 3000,
        sticker: false,
        addclass: 'pnotify-light',
        type: 'success'
    });
};

var showErrorNotiy = function (title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({title: title, text: message, delay: 3000, icon: "fa fa-exclamation-triangle", type: 'error'});
};

var showNoticeNotiy = function (title, message) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({title: title, text: message, delay: 3000, icon: "fa fa-exclamation-triangle", type: 'notice'});
};

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
    minLength: 1,//键入字数多少开始补全
    showHintOnFocus: "true",//将显示所有匹配项
    fitToElement: true,//选项框宽度与输入框一致
    items: 10,//下拉选项中出现条目的最大数量。也可以设置为“all”
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
            var body = appendToTable(result);
            $("#result").append($(body));
            showSuccessNotify("success", "参数[" + value + "]查询成功");
        },
        error: function (e) {//请求失败，包含具体的错误信息
            alert("err");
        }
    });
}

function appendToTable(tmp) {
    let name = tmp.name;
    let versions = tmp.versions;
    let examples = tmp.examples;
    let type = tmp.type;
    let os = tmp.os;
    let meaning = tmp.meaning;
    let hanyi = tmp.hanyi;
    let use = tmp.use;
    let defaultValue = tmp.defaultValue;
    let valueType = tmp.valueType;
    let extend = tmp.extend;
    let url = tmp.url;
    let body = $('<div class="panel-heading">' + name + '&nbsp;&nbsp;<span class="badge badge-primary">' + versions + '</span>&nbsp;&nbsp;<span class="badge label-info">' + defaultValue + '</span></div>\n' +
        '<ul class="list-group">\n' +
        '    <li class="list-group-item"><span class="label label-success">含义</span>&nbsp;&nbsp;' + hanyi + '</li>\n' +
        '    <li class="list-group-item"><span class="label label-primary">例子</span>&nbsp;&nbsp;' + examples + '</li>\n' +
        '    <li class="list-group-item"><span class="label label-success">使用</span>&nbsp;&nbsp;' + use + '</li>\n' +
        '    <li class="list-group-item"><span class="label label-primary">扩展</span>&nbsp;&nbsp;' + extend + '</li>\n' +
        '</ul>'
    );
    return body;
}