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
        '<div class="panel-body">\n' +
        '    <p><span class="label label-danger">例子</span>&nbsp;&nbsp;' + examples + '</p>\n' +
        '</div>\n' +
        '<ul class="list-group">\n' +
        '    <li class="list-group-item"><span class="label label-success">含义</span>&nbsp;&nbsp;' + hanyi + '</li>\n' +
        '    <li class="list-group-item"><span class="label label-warning">扩展</span>&nbsp;&nbsp;' + extend + '</li>\n' +
        '    <li class="list-group-item"><span class="label label-primary">使用</span>&nbsp;&nbsp;' + use + '</li>\n' +
        '</ul>'
    );
    return body;
}

// 参数诊断
$("#parameters").change(function () {
    let values = $("#parameters").val();
    let jdkVersion = $("input[name='jdkVersion']:checked").val();
    let totalMem = $("input[name='totalMem']:checked").val();
    $.ajax({
        type: "POST", //请求方式
        url: "/evaluate",  //请求地址
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",  //请求的媒体类型
        data: {"parameters": values, "jdkVersion": jdkVersion, "totalMem": totalMem},    //参数值
        success: function (result) {  //请求成功
            showSuccessNotify("success", "参数诊断完成");
            $("#result2").show();
            $("#result2-table > tbody").empty();
            let errcount=0,warncount=0,okcount=0;
            for (let i = 0; i < result.length; i++) {
                if (result[i].level==="OK"){
                    okcount++;
                } else if (result[i].level==="WARN"){
                    warncount++;
                }else if (result[i].level==="ERROR"){
                    errcount++;
                }
                let tr = appendToTable2(result[i], i);
                $("#result2-table > tbody").append($(tr));
            }
            $("#totalResult").text("发现ERROR级别参数有"+errcount+"个,发现WARN级别的参数有"+warncount+"个,配置正确的参数有"+okcount+"个");
        },
        error: function (e) {//请求失败，包含具体的错误信息
            alert("err");
        }
    });
});

function appendToTable2(tmp, indedx) {
    let level = tmp.level;
    let category = tmp.category;
    let title = tmp.title;
    let conent = tmp.conent;
    let tips = tmp.tips;
    let tr = $('<tr>\n' +
        '      <th scope="row">' + indedx + '</th>\n' +
        '      <td>' + level + '</td>\n' +
        '      <td>' + category + '</td>\n' +
        '      <td>' + title + '</td>\n' +
        '      <td>' +
        '          <div class="btn-group">' +
        '             <button class="btn btn-xs btn-info center" onclick="showDetail(this)"> >>> </button>' +
        '         <div>' +
        '      </td>' +
        '      <td class="center hidden">' + conent + '</td>' +  // 该列隐藏，由模态框触发
        '      <td class="center hidden">' + tips + '</td>' +  // 该列隐藏，由模态框触发
        '   </tr>\n');
    return tr;
}

var modelText1;
var modelText2;

function showDetail(obj) {
    var thisObj = $(obj);//js对象转jquery对象 关键
    var td = thisObj.closest("td");
    modelText1 = td.next().text();
    modelText2 = td.next().next().text();
    $('#qrcode').modal('show');
}

$('#qrcode').on('show.bs.modal', function (event) {
    var modal = $(this);  //get modal itself
    modal.find('.modal-body #modelText1').text(modelText1);
    modal.find('.modal-body #modelText2').text(modelText2);
});