<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <title>文章添加</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/icon/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
    <link rel="stylesheet" href="<%=basePath %>src/css/webuploader.css" />
    <link rel="stylesheet" href="<%=basePath %>src/registMa/themes/default/css/umeditor.css" />
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/home/common.js"></script>
    <script type="text/javascript" src="<%=basePath %>srcApply/js/webuploader.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>src/registMa/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>src/registMa/umeditor.js"></script>
    <style>
        .webuploader-pick {
           padding: 5px 15px;
         }
        #thelist {
            height: auto;
        }
    </style>

</head>

<body class="easyui-layout">

<div class="over" style="display: none;">
    <div id="loading"></div>
</div>
<div data-options="region:'center',border:false">
    <div class="easyui-panel resPanel" style="width: 100%;height: 98%;overflow: auto">
        <div class="abtn-group btnInner">
            <a class="icon iconfont icon-icon- green" onclick="saveAdd()"><i>保存</i></a>
            <a class="icon iconfont icon-chuangkouhua" onclick="bank.openWindow()"><i>新窗口打开</i></a>
            <a class="icon iconfont icon-guanbi red" onclick="bank.hideWindow()"><i>关闭</i></a>
        </div>
        <form id="queryForm" method="post">
            <ul class="search-group">
                <li><input class="easyui-textbox" name="articleName" id="articleName" style="width: 92%" data-options="label:'文章标题:',required:true"></li>
                <li><input class="easyui-textbox" name="author" id="author" style="width: 92%" data-options="label:'作者:',required:true"></li>
                <li>
                    <select class="easyui-combogrid" name="columnId" id="columnId" style="width: 92%" data-options="url:'<%=basePath%>columnInfo/selectColumnInfo',label:'栏目名称:',editable:false,
                         panelWidth:'175',panelHeight:'auto',idField: 'id',textField: 'columnName',columns:[[{field:'columnName',title:'栏目名称',width:170,align:'center'}]],required:true">
                    </select>
                </li>
            </ul>
        </form>
        <div>
            <div style="float: left;height: 34px;line-height: 34px;margin-right: 15px">附件上传：</div>
            <div id="picker" style="display: inline-block">添加附件</div>
            <div id="thelist" class="uploader-list">附件展示：</div>
        </div>
       <!--style给定宽度可以影响编辑器的最终宽度-->
      <div id="myEditor" style="width: 100%;min-height: 600px">
            <p></p>
        </div>
        </div>
        </div>
        </body>

        </html>
<script>
    var id;var sendArray=[];var uploader;var currentIndex=0;
    //实例化编辑器
    var ue = UM.getEditor('myEditor');
    $(function(){
        initUP();
    });
    //修改保存
    function saveAdd() {
        var articleBody  = UM.getEditor('myEditor').getContent();
        $('#queryForm').form('submit', {
            url:'<%=basePath %>articleInfo/insertArticleInfo',
            onSubmit: function(param){
                param.articleBody = articleBody;
                var isValid = $(this).form('validate');
                if (!isValid){
                }
                return isValid;
            },
            success:function (data) {
               data = JSON.parse(data);
                if(data.flag == true){
                    $(".over").show();
                    send(data.msg);
                    currentIndex=0;
                }else{
                    bank.ajaxMessage(data);
                }
            }
        });
    }
    function initUP(){
        uploader = WebUploader.create({
            auto: false,
            swf: '<%=basePath %>srcApply/js/Uploader.swf',
            server: '<%=basePath %>articleInfo/fileUpload',
            pick:{
                id:'#picker',
                label:'添加附件'
            } ,
            threads:1,//并发最大数量限制
            duplicate: false, //同一文件是否可重复选择
            resize: false
        });
        uploader.on("error", function (type) {
            if (type == "Q_TYPE_DENIED") {
                uploader.refresh();
                bank.alertMessage("请上传JPG、PNG、GIF、BMP格式图片文件");
            }else if (type == "F_DUPLICATE") {
                uploader.refresh();
                bank.alertMessage("请不要重复选择文件！");
            }
        });
        // 当有文件被添加进队列的时候
        uploader.on( 'fileQueued', function( file ) {
            sendArray.push(file.id);
            $list = $("#thelist");
            $list.append( '<div id="' + file.id + '" class="item" style="display: inline-block">' +
                '<h4 class="info" style="margin-right: 10px;display: inline-block">' + file.name + '</h4>' +
                '<div class="info" onclick="del($(this))" style="display: inline-block;color: #0092DC;cursor: pointer;">' + "删除" + '</div>' +
                '</div>' +
                '</div>' );

        });
    }
    function send(id){
        if(sendArray.length==0){
            bank.ajaxMessage("上传成功");
            $(".over").hide();
            setTimeout(function () {
                parent.$('.bankWindow').dialog('close');
            },1000);
            parent.$('#dataTable').datagrid('reload');
        }else{
            //上传后才执行
            uploader.on('uploadBeforeSend', function (obj, data, headers){
                data.articleId = id;
                currentIndex++;
            });
            uploader.upload();
            //上传后才执行
            uploader.on( 'uploadError', function( file,ret ) {
                bank.ajaxMessage('上传失败');
                $(".over").hide();
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);

            });
            //上传后才执行
            uploader.on( 'uploadSuccess', function( file,ret ) {
                if(currentIndex==sendArray.length){
                    if(ret.flag == true){
                        $(".over").hide();
                        bank.ajaxMessage(ret.msg);
                        setTimeout(function () {
                            parent.$('.bankWindow').dialog('close');
                        },1000);
                        parent.$('#dataTable').datagrid('reload');
                    } else {
                        $(".over").hide();
                        bank.ajaxMessage(ret.msg);
                    }
                }
            });
            if (sendArray.length == 0){
                setTimeout(function () {
                    parent.$('.bankWindow').dialog('close');
                },1000);
                parent.$('#dataTable').datagrid('reload');
                bank.ajaxMessage('上传成功');
                $(".over").hide();
            }
        }
    }
    function del(obj) {
        //移除
        var id=obj.parent().attr("id");
        var index=bank.getArrayIndex(sendArray,id);
        sendArray.splice(index,1);
        obj.parent().remove();
        uploader.reset();
    }
</script>