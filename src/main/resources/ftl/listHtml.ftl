<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Title</title>
    <!--layui 框架-->
    <link rel="stylesheet" href="../../static/layui/css/layui.css" th:href="@{~/static/layui/css/layui.css}">
    <style type="text/css">
        .layui-table, .layui-table-view{
            margin: 0;
        }
    </style>
</head>
<body>
<div style="padding:10px;">
    <div class="layui-form layui-form-item" style="margin-bottom:0px;">
        <div class="layui-inline" style="float:left;">
            <button class="layui-btn layui-btn-sm layui-btn-primary" id="add"><i class="layui-icon" style="color:#0086ed;">&#xe608;</i>添加</button>
        </div>
        <div class="layui-inline" style="float:right;right:-10px;">
            <div class="layui-input-inline">
                <input type="text" class="layui-input" placeholder="请输入搜索内容" id="searchText" style="height:30px; line-height:15px;">
            </div>
            <button class="layui-btn layui-btn-sm layui-btn-primary" id="search"><i class="layui-icon" style="color:#0086ed;">&#xe615;</i>搜索</button>
        </div>
    </div>
    <table class="layui-hide" id="list" lay-filter="list"></table>
</div>
</body>
<script src="../../static/layui/layui.js" th:src="@{~/static/layui/layui.js}"></script>
<script type="text/html" id="tools">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript" src="../../static/jquery/jquery.js" th:src="@{~/static/jquery/jquery.js}"></script>
<script type="text/javascript" src="../../static/teacher/list.js" th:src="@{~/static/teacher/list.ftl}"></script>
</html>