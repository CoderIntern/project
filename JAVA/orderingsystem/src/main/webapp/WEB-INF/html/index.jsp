<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

    <!-- Latest compiled and minified Locales -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h1>订餐系统用户列表</h1>
        </div>
        <div class="panel-body" id="my_table">
            <table id="userList" class="table table-hover table-bordered">
                <thead>
                    <tr>
                        <%--<td id="td">多选</td>--%>
                        <td>#</td>
                        <td>name</td>
                        <td>订餐否</td>
                    </tr>
                </thead>
                <tbody id="user_list">
                    <tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <%--<td><input type="checkbox" id="${user.id}"/></td>--%>
                                <td>${user.id}</td>
                                <td id="username">${user.name}</td>
                                <%--<td><a id="isOrder" class="btn btn-info" onclick="js_method(${user.id})">订餐</a></td>--%>
                                <td><a id="isOrder" class="btn btn-info" onclick="js_method(${user.id})">订餐</a></td>
                            </tr>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>

        </div>
        <p>
            <%--<button class="btn btn-large btn-primary" type="button" style="margin-left: 1%" onclick="orderingSubmit()">提交</button>--%>
            <%--<button class="btn btn-large btn-primary" type="button" onclick="queryAllOrdered()">查看已订餐人</button>--%>
        </p>
        <form action="/orderedLists" style="margin-left: 40%">
            <input class="btn btn-info  btn-lg" type="submit" value="查看已定餐人数">
        </form>
    </div>
</div>


<script>
    function queryAllOrdered() {

    }
</script>
<script type="text/javascript">
    function js_method(id) {
        $.ajax({
            url:"/test?name="+id,
            type: "GET",
//            dataType: "json",
//            data: "",
//            dataType: "json",
            error: function(data) {
//                window.location.href="http://127.0.0.1:8080/orderedLists"
                alert("~~~订餐失败~~~");
                alert(data.issuccess);
            },
            success: function(data) {
                if(data.issuccess){
                    alert("success" + " " + data.issuccess + "订餐成功~~~");
//                  window.location.href="http://127.0.0.1:8080/orderedLists"
                }
                else{
                    alert("else" + data.successed + "已定餐");
                }
            }
        });
    }
</script>
<script>
    function orderingSubmit() {
        var selectedUser = "";//需要订餐的用户
        var userTable = document.getElementById("user_list");
        var user_list = userTable.getElementsByTagName("input");
        for(var i = 0;i<user_list.length;i++ ){
           if(user_list[i].checked){
               selectedUser += user_list[i].getAttribute("id")+",";
           }
        }
        console.log(selectedUser)
       if(selectedUser.length > 0){
           $.ajax({
               cache: true,
               type: "GET",
               url:"/ordering",
               data:selectedUser,// 你的formid
               async: false,
               error: function(request) {
                   alert("Connection error");
               },
               success: function(data) {
                   alert(data)
               }
           });
       }
      else{
           alert("请选择....")
       }
    }
</script>

</body>
</html>
