/*
* 同步
* 链接
* success
*/
function loadAjaxRequest(async, url, success) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: url,
        success: success,
        complete: function (request, textStatus) {
        },
        error: function () {
        }
    });
}
/*
* 链接
* 参数
* success
*/
function AjaxRequestByData(async, url, data, success) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: url,
        data: data,
        success: success,
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

/*默认加载格式为[{text:'',value:''},{text:'',value:''},{text:'',value:''}...]
* controlId     控件对象
* url           查询地址
* async         ajax请求同步或异步
*/
function SelectAppendByOther(controlObj, url, async, append) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: url,
        success: function (data) {
            controlObj.find("option").remove();
            if (append == undefined) {
                controlObj.append("<option value=''>---请选择---</option>");
            }
            if (data != '') {
                var obj = eval("(" + data + ")");
                $.each(obj, function (key, val) {
                    if (val != undefined) {
                        controlObj.append("<option value='" + obj[key]["value"] + "'>" + obj[key]["text"] + "</option>");
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function SelectAppendByRoot(controlObj, url, async, append) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: url,
        success: function (data) {
            controlObj.find("option").remove();
            if (append == undefined) {
                controlObj.append("<option value='0'>根节点</option>");
            }
            if (data != '') {
                var obj = eval("(" + data + ")");
                $.each(obj, function (key, val) {
                    if (val != undefined) {
                        controlObj.append("<option value='" + obj[key]["value"] + "'>" + obj[key]["text"] + "</option>");
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function SelectAppendByTextValue(controlObj, url, async, text, value, append) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: url,
        success: function (data) {
            controlObj.find("option").remove();
            if (append == undefined) {
                controlObj.append("<option value=''>---请选择---</option>");
            }
            if (data != '') {
                var obj = eval("(" + data + ")");
                $.each(obj, function (key, val) {
                    if (val != undefined) {
                        controlObj.append("<option value='" + obj[key][value] + "'>" + obj[key][text] + "</option>");
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

function SelectAppendByDictionary(controlObj, url, async, append) {
    $.ajax({
        type: "post",
        async: async, //设置同步
        url: "../../ashx/DictionaryHandler.ashx?action=type&type=" + url,
        success: function (data) {
            controlObj.find("option").remove();
            if (append == undefined) {
                controlObj.append("<option value=''>---请选择---</option>");
            }
            if (data != '') {
                var obj = eval("(" + data + ")");
                $.each(obj, function (key, val) {
                    if (val != undefined) {
                        controlObj.append("<option value='" + obj[key]["Value"] + "'>" + obj[key]["Text"] + "</option>");
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}
/***************/
/* 辅助方法 jqGrid
*  状态值 转化
*/
function stateFormate(cellvalue, options, rowObject) {
    var showValue;
    switch (cellvalue) {
        case "1":
            showValue = "<div style='color:green'>激活</div>";
            break;
        case "0":
            showValue = "删除";
            break;
        case "2":
            showValue = "置顶";
            break;
        default:
            showValue = "未定义";
            break;
    }
    return showValue;
}