<%-- 
    Document   : math
    Author     : Basem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VM Page</title>
        <script type="text/javascript" src="media/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="media/ajaxcontrol.js" ></script>
    </head>
    <body>
        <h1>VM Controls</h1>
        <div id ="part1" style=" width: 300px; float: left;" >
            <form id="controls" name="Submit" action="vmcontrol"  >
                <label for="startVm">Start vm</label>
                <input type="text" name="startVm" />
                <br />
                <label for="stopVm">Stop vm</label>
                <input type="text" name="stopVm" />
                <br />
                <label for="startProcess">start a process</label>
                <input type="text" name="startProcess" />
                <input type="submit"/>
            </form>
            <button id="reset" >Reset VM</button>
            <button id="connect" >Connect VM</button>
        </div>
        <div id="part2" style="border: 10px; height: 1000px; float: left; background-color: gainsboro" >
            <div id="content"> </div>
        </div>
    </body>
</html>


