/**
 * 作者：张孟志
 * Email：104446930@qq.com
 * 日期：2016-01-29
 * 
 * ajax无刷新方式对form表单进行赋值
 * 调用方式：
 * $json = { "usrname":"张三", "address":"湖北钟祥", "sex": "1", "hobby":["sing", "trance"]};
 * $('#editForm').formEdit($json);
 * 调用代码解释说明：
 * $(form表单).formEdit(json数据);
 * json数据说明:基本格式{inputname: value}, 具体类型{"text":"aaa", "checkbox":[1,2,3], "radio":"10"}
 * 注意：表单查找控件是name属性，非id属性。
 */
$.fn.formEdit = function(data){
    return this.each(function(){
        var input, name;
        if(data == null){this.reset(); return; }
        for(var i = 0; i < this.length; i++){  
            input = this.elements[i];
            //checkbox的name可能是name[]数组形式
            name = (input.type == "checkbox")? input.name.replace(/(.+)\[\]$/, "$1") : input.name;
            if(data[name] == undefined) continue;
            switch(input.type){
                case "checkbox":
                    if(data[name] == ""){
                        input.checked = false;
                    }else{
                        //数组查找元素
                        if(data[name].indexOf(input.value) > -1){
                            input.checked = true;
                        }else{
                            input.checked = false;
                        }
                    }
                break;
                case "radio":
                    if(data[name] == ""){
                        input.checked = false;
                    }else if(input.value == data[name]){
                        input.checked = true;
                    }
                break;
                case "button": break;
                default: input.value = data[name];
            }
        }
    });
};