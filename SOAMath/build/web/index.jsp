<%-- 
    Document   : index
    Author     : Basem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="media/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="media/ajaxcontrol.js" ></script>
        <link rel="stylesheet" type="text/css" href="media/style.css" />
        <title>Arithmatic through SOA</title>
    </head>
    <body>

        <div class="inputs">

            <h1>Please input a mathematical expression to evaluate:</h1>
            <form id="expression_form" action="math">
                <input name="math_expression" class="large_field" type="text" ></input>
                <input type="submit" />
            </form>
        </div>
        <div id="content" class="outputs"></div>
    </body>
</html>
