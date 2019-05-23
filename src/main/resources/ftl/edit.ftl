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

    layedit.set({
        uploadImage: {
            url: getPath+'common/layUpload' //接口url
            ,type: 'post' //默认post
        }
    });

    var editIndex = layedit.build('demo', {
        height: 400 //设置编辑器高度
    }); //建立编辑器

    reloadForm();

    function reloadForm(){
        var loadIndex = parent.layer.msg("正在加载数据...", {icon:16,shade: 0.3,time:false});
        $.ajax({
            method:"post",
            url:getPath+"teacher/selectAll?id="+$('body').attr("data-id"),
            dataType:'json',
            success:function(data){
                parent.layer.closeAll();
                layer.closeAll();
                if(data.code == 0){
                    if(data.data == null || data.data.length == 0){
                        //初始化
                        $("form input[name=id]").remove();
                        $("#content #imgList").siblings().show();
                        $("#imgList").html('');
                        $("form input[name=img_url]").remove();
                        $("#content").css("border","1px dashed #e2e2e2");
                        form.render();
                    }else if(data.data.length == 1){
                        //加载对应数据
                        $("form input[name=id]").remove();
                        $("form").append('<input type="hidden" name="id" value="'+data.data[0].id+'" readonly>');
                        $("#content #imgList").siblings().hide();
                        $("form input[name=photo]").remove();
                        $("form").append('<input type="hidden" name="photo" value="'+data.data[0].photo+'" readonly>');
                        $("#imgList").html('<img src="' + data.data[0].photo + '" style="width: 100%;height: 100%;cursor: pointer;" alt="点击更换图片">');
                        $("#content").css("border","1px dashed #1E9FFF");
                        $("form input[name=name]").val(data.data[0].name);
                        $("form input[name=phone]").val(data.data[0].phone);
                        form.render();
                        layedit.setContent(editIndex,data.data[0].info,editIndex);
                    }else{
                        layer.msg("数据有误,请检查",{icon:5,time:2000,shift:6});
                        return false;
                    }
                }else{
                    layer.msg(data.msg,{icon:5,time:2000,shift:6});
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
                $("#content").css("border","1px dashed #e2e2e2");
            });
        }
    });



    form.on("submit(edit)",function(data){
        var loadIndex = parent.layer.msg("正在执行...", {icon:16,shade: 0.3,time:false});
        if($(document).find("#imgList img").length == 0){
            parent.layer.closeAll();
            layer.msg("请选择头像",{icon:5,time:1500,shift:6});
            return false;
        }
        if($(document).find("form input[name=id]").length == 0|| $(document).find("form input[name=id]").val() == '' || $(document).find("form input[name=id]") == null || $(document).find("form input[name=id]") == 'undefined'){
            parent.layer.closeAll();
            layer.msg("数据加载有误",{icon:5,time:1500,shift:6});
            return false;
        }
        var content = layedit.getContent(editIndex);
        var formData = new FormData($("#form")[0]);
        formData.append("info",content);
        $.ajax({
            method:"post",
            url:getPath+"/teacher/update",
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