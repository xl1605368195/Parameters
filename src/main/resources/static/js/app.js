// 查询补全
//监听文本框
// $('#parameter').on('input propertychange', function () {
//     let value = $('#parameter').val();
//     $.ajax({
//         type: "GET", //请求方式
//         contentType: "application/json;charset=UTF-8",  //请求的媒体类型
//         url: "/query",  //请求地址
//         data: {"parameter": value},    //参数值
//         success: function (result) {  //请求成功
//             alert("success:" + result.examples.length);
//         },
//         error: function (e) {//请求失败，包含具体的错误信息
//             alert("err");
//         }
//     });
// });

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
    items: 5,//下拉选项中出现条目的最大数量。也可以设置为“all”
    autoSelect: true,//允许你决定是否自动选择第一个建议
    afterSelect: function (item) {
        $("#parameter").val(item);
    },
    delay: 10//在查找之间添加延迟
});
