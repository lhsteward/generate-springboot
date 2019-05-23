layui.use(['table','layer','form'], function(){
    var table = layui.table,
        layer = layui.layer,
        form  = layui.form;

    var getPath=getRootPath();
    function getRootPath(){
        var local=window.location;
        return local.protocol+"//"+local.host+"/";
    };

    var list = table.render({
        elem: '#list'
        ,url: getPath+'test/selectAll' //数据接口
        ,limits: [10,20,50]
        ,cellMinWidth: 100
        ,page: true //开启分 页
        ,loading:true
        ,cols: [[ //表头
            {field: 'name', title: '名字',minWidth:100}
            ,{field: 'phone', title: '手机号',minWidth:150}
            ,{field: 'create_time', title: '创建时间',minWidth:180,templet:function(d){
                    if(d.create_time == null || d.create_time == '' || d.create_time == undefined){
                        return "--";
                    }
                    return d.create_time;
                }}
            ,{field: 'is_show', title: '展示',templet:'#is_show',minWidth:100,unresize: true}
            ,{fixed: 'right', title: '操作',minWidth:200,align:'center', toolbar: '#tools'}
        ]]
    });


    $('#search').click(function(){
        table.reload('list',{
            url: getPath+'teacher/selectTeacherByTable' //数据接口
            ,where:{name:$('#searchText').val()}
        })
    });

    $(document).keydown(function(event) {
        if (event.keyCode == 13&&$('#searchText').is(':focus')) {
            $('#search').click();
        }
    });


    //监听工具条
    table.on('tool(list)', function(obj){
        var data = obj.data;
        if(obj.event === 'edit'){
            location.href = getPath + "/teacher/edit.html?id="+data.id
        } else if(obj.event === 'del'){
            layer.confirm('是否删除该信息?', function(index){
                var loadIndex = parent.layer.msg("正在执行...", {icon:16,shade: 0.3,time:false});
                $.ajax({
                    method:"post",
                    url:getPath+"teacher/update?id="+data.id+"&is_del=1",
                    dataType:'json',
                    success:function(data){
                        parent.layer.closeAll();
                        layer.closeAll();
                        if(data.code == 0){
                            layer.msg(data.msg,{icon:1,time:500},function(){
                                list.reload();
                            });
                        }else{
                            layer.msg(data.msg,{icon:5,time:2000,shift:6});
                            return false;
                        }
                    },
                    error:function(e){
                        parent.layer.closeAll();
                        layer.closeAll();
                        layer.msg("请求失败",{icon:5,time:2000,shift:6});
                    }
                });
            });
        }
    });


    //添加教练信息
    $("#add").click(function(){
        location.href = getPath + "/teacher/add.html";
    });

});