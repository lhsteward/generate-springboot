layui.use(['form','layer','element','layedit','upload'],function(){
    var form = layui.form,
        layer = layui.layer,
        element = layui.element,
        layedit = layui.layedit,
        upload = layui.upload;

    var getPath=getRootPath();
    function getRootPath(){
        var local=window.location;
        return local.protocol+"//"+local.host+"/";
    };

    upload.render({
        elem: '#content'
        , auto: false
        , size: 300
        , accept: 'images' //图片
        , choose: function (obj) {
            //读取本地文件
            obj.preview(function (index, file, result) {
                $("#content #imgList").siblings().hide();
                $("#imgList").html('<img src="' + result + '" style="width: 100%;height: 100%;cursor: pointer;" alt="点击更换图片">');
                $("form button.layui-btn-normal").removeClass("layui-btn-disabled").attr("disabled",false);
                $("#content").css("border","1px dashed #e2e2e2");
            });
        }
    });

    layedit.set({
        uploadImage: {
            url: getPath+'common/layUpload' //接口url
            ,type: 'post' //默认post
        }
    });



    var editIndex = layedit.build('demo', {
        height: 400 //设置编辑器高度
    }); //建立编辑器

    form.on("submit(add)",function(data){
        var loadIndex = parent.layer.msg("正在执行...", {icon:16,shade: 0.3,time:false});
        if($(document).find("#imgList img").length == 0){
            parent.layer.closeAll();
            layer.msg("请选择头像",{icon:5,time:1500,shift:6});
            return false;
        }
        var content = layedit.getContent(editIndex);
        var formData = new FormData($("#form")[0]);
        formData.append("info",content);
        $.ajax({
            method:"post",
            url:getPath+"/teacher/insert",
            data:formData,
            dataType: "json",
            contentType: false, // 注意这里应设为false
            processData: false,
            cache: false,
            field:"file",
            success:function(ret){
                parent.layer.closeAll();
                layer.closeAll();
                if(ret.code == 0){
                    layer.msg(ret.msg,{icon:1,time:500},function(){
                        location.href=getPath+"teacher/list.html";
                    });
                }else{
                    layer.msg(ret.msg,{icon:5,time:2000,shift:6});
                    return false;
                }
            },
            error:function(e){
                parent.layer.closeAll();
                layer.closeAll();
                layer.msg("请求失败",{icon:5,time:2000,shift:6});
                return false;
            }
        });
        return false;
    });

    $("#back,#cancel").click(function(){
        location.href = getPath+"/teacher/list.html";
    })

});