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
        #LAY_layedit_1 body img{
            width: 200px!important;
        }
    </style>
</head>
<body>
<div style="padding:10px;">
	<span class="layui-breadcrumb" lay-separator=">>" style="padding-left: 20px;padding-top: 20px;">
        <a href="#" style="font-size: 16px;" id="back">返回列表</a>
        <a style="font-size: 14px;"><cite>添加教练</cite></a>
    </span>
    <hr/>
    <form class="layui-form" id="form" action="" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: red; margin-right: 5px;">*</span>头像:</label>
            <div class="layui-input-block">
                <div class="layui-upload-drag" id="content" style="width:150px;height: 170px;padding: 15px;position: relative;">
                    <i class="layui-icon" style="font-size: 50px;"></i>
                    <p>点击上传图片</p>
                    <div id="imgList" style="width: calc(100% - 30px);height: calc(100% - 30px);position: absolute;top: 15px;">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color:red;padding-right: 10px;">*</span>姓名: </label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color:red;padding-right: 10px;">*</span>手机号: </label>
            <div class="layui-input-block">
                <input type="number" name="phone" lay-verify="required|phone" autocomplete="off" placeholder="请输入手机号" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color:red;padding-right: 10px;">*</span>简介: </label>
            <div class="layui-input-block">
                <textarea id="demo" name="info" style="display: none;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add">添加</button>
                <span class="layui-btn layui-btn-primary" id="cancel">返回</span>
            </div>
        </div>
    </form>
</div>
</body>
<script src="../../static/layui/layui.js" th:src="@{~/static/layui/layui.js}"></script>
<script type="text/javascript" src="../../static/jquery/jquery.js" th:src="@{~/static/jquery/jquery.js}"></script>
<script type="text/javascript" src="../../static/teacher/add.js" th:src="@{~/static/teacher/add.js}"></script>
</html>