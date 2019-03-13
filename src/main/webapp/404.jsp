<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9" >
    <title>徐州市住房保障管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>themes/gray/easyui.css">
    <link rel="stylesheet" href="<%=basePath %>src/css/icon/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/common.css">
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/jquery.easyui.min.js"></script>
    <script src="<%=basePath %>src/js/easyui-lang-zh_CN.js"></script>
    <!--[if lte IE 9]>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>src/css/ie.css">
    <script type="text/javascript" src="<%=basePath %>src/js/ie/html5shiv.js"></script>
    <script type="text/javascript" src="<%=basePath %>src/js/ie/respond.min.js"></script>
    <![endif]-->
    <script src="<%=basePath %>src/js/common.js"></script>
    <link href="<%=basePath %>src/img/public/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>
<body class="easyui-layout">
<div  data-options="region:'center',border:false" class="welcenter">
    <div class="easyui-panel welpanel" style="width: 100%">
        <h1 style="width: 100%">404,页面走丢了~</h1>
    </div>
</div>
</body>
</html>