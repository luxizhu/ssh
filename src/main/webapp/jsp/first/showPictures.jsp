<%--
  Created by IntelliJ IDEA.
  User: caopeihe
  Date: 2016-8-16
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        /*table tr td{*/
            /*width:100px;*/
            /*border:2px solid;*/
        /*}*/

    </style>
</head>
<body onload="getDrive()">
<form action="/" method="post" enctype="multipart/form-data" name="form1">
    <table>
        <tr>
            <td rowspan="2">请选择驱动器</td>
            <td rowspan="2">
                <select name="drivers" onchange="getFolder(this.value)">

                </select>
                <div id="listDir" style="border: solid #666666 1px; width: 232px;height: 500px;overflow:auto;float:left;"></div>
            </td>
            <td>源文件</td>
            <td>
                <div id="listFile" style="border: solid #666666 1px;width: 680px;height:220px;overflow:scroll;"></div>
                <div style="width:680px;padding:5px">
                    <input name="put" type="button" class="btn_bg" onclick="putFun()" value="移入"/>
                    &nbsp;&nbsp;
                    <input name="out" type="button" class="btn_bg" onclick="outFun()" value="移除"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>选择的文件</td>
            <td>
                <div id="newFile" style="width:680px;height:200px;overflow:scroll;border:solid #666666 1px"></div>
                <div style="width:680px;padding:5px;text-align:center;">
                    <input name="btn_upload" type="submit" disabled="disabled" class="btn_grey" value="上传图片"/>
                </div>
            </td>
        </tr>
    </table>

</form>
<script type="text/javascript">
    var num_global=1;
    var arrFileURL=new Array();
    var form1 = document.getElementsByName("form1").item(0);

    function putFun(){
        var selectImg = "";
        var newFile = document.getElementById("newFile").innerHTML;
        var num = 1;
        if(document.getElementById("newFlie")!=null){
            num = num + form1.newImg.length - 1;
        }else{
            var selectImg = "<input name='newImg' type='checkbox' id='newImg' value='0' style='display:none;'>";
        }
        var j=0;
        var flag_isCheck = 0;
        for(i = 0;i<form1.sourceImg[i].length;i++){
            if(form1.sourceImg[i].checked){
                if(newFile.indexOf(form1.sourceImg[i].value==-1)){
                    var objFile = fso.GetFile(form1.sourceImg[i].value);
                    fileName=objFile.name;
                    selectImg = selectImg + "<div style='width: 160px;float: left;'>"+
                            "<input name='newImg' class='noborder' type='checkbox' id='newImg' value='"+
                            form1.sourceImg[i].value+"'>&nbsp;<img src='"+form1.sourceImg[i].value+"' alt='"+
                            fileName +"'width='128' height='94' border='1'><br/><input name='fileField"+(num + j) +
                            "'type='file' style='width: 0px;height:0px;display:block;border-width: 0px;'></div>";
                }
                flag_isCheck=1;
            }
        }
        if(flag_isCheck==1){
            document.getElementById("newFile").innerHTML=newFile + selectImg;
            num_global=j+num;
            for(i=1;i<form1.newImg.length;i++){
                arrFileURL[i]=form1.newImg[i].value;
            }
            if(document.getElementById("newImg")!=null){
                window.setTimeout("setFileFieldValue(1)",100);
            }
        }
    }

    function outFun(){
        if(document.getElementById("newImg")!=null){
            var selectImg = "<input name='newImg' type='checkbox' id='newImg' value='0' style='display:none;'>";
            var j = 1;
            for(i=1;i<form1.newImg.length;i++){
                if(!form1.newImg[i].checked){
                    var objFile=fso.GetFile(form1.newImg[i].value);
                    fileName = objFile.name;
                    selectImg = selectImg + "<div style='width: 160px;float: left;'>"+
                            "<input name='newImg' class='noborder' type='checkbox' id='newImg' value='" +
                            form1.newImg[i].value+"'>"+
                            "&nbsp;<img src='"+form1.newImg[i].value+"'alt='"+fileName+"' width='128' height='94' border='1'>"+
                            "<br/><input name='fileField"+j+"' type='file' size='1' readonly "+
                            "style='width: 0px;height: 0px;display:block; border-width: 0px;'></div>";
                    arrFileURL[j]=form1.newImg[i].value;
                    j++;
                }
            }
            num_global=j;
            document.getElementById("newFile").innerHTML = selectImg;
            if(num_global>1){
                var timer=setTimeout("setFileFieldValue(1)",100)
            }else{
                document.getElementById("btn_upload").disabled=true;
            }
        }
    }

    function setFileFieldValue(i){
        var s="fileField"+i;
        var WshShell=new ActiveXObject("WScript.Shell");
        window.clipboardData.setDate('text',arrFileURL[i]);
        document.getElementById(s).focus();
        WshShell.sendKeys("^v");
        i++;
        var timer=window.setTimeout("setFileFieldValue("+i+")",100);
        if(i>=num_global){
            window.clearTimeout(timer);
            document.getElementById("btn_upload").disabled=false;
        }
    }
    function getDrive(){
        var fso=new ActiveXObject("Scripting.FileSystemObject");
        var eDrives = new Enumerator(fso.Drives);
        var driveLetter = "";
        var i=0;
        for(;!eDrives.atEnd();eDrives.moveNext()){
            var itemDrive = eDrives.item();
            if(itemDrive.DriveType==2){
                driveLetter = driveLetter + itemDrive.DriveLetter;
                form1.drivers.options[i] = new Option(itemDrive.DriveLetter+":\\");
                form1.drivers.options[i].value = itemDrive.DriveLetter+":\\";
                i++;
            }
        }
        getFolder(form1.drivers.options[0].value);
    }

    function getFolder(driver){
        fso = new ActiveXObject("Scripting.FileSystemObject");
        var eSubFolders = new Enumerator(fso.GetFolder(driver).SubFolders);
        var eFiles = new Enumerator(fso.GetFolder(driver).files);
        var itemSubFolder = "";
        var i=0;
        var table="<table border='1' width='97%'>";
        if(!fso.GetFolder(driver.replace(new RegExp("\\\\","g"),"\\\\")).IsRootFolder){
            var fatherDriver1 = new String(fso.GetFolder(driver).ParentFolder);
            var fatherDriver = fatherDriver1.replace(new RegExp("\\\\","g"),"\\\\");
            var tr = "<tr><td height='27px'><a href='#' onclick='getFolder(\""+fatherDriver+"\")'>返回上一级</a></td></tr>";
        }else{
            var tr="";
        }
        for(;!eSubFolders.atEnd();eSubFolders.moveNext()){
            itemSubFolder = eSubFolders.item();
            var eSubFolder_son = new Enumerator(fso.GetFolder(itemSubFolder).SubFolders);
            var eSubFolder_file = new Enumerator(fso.GetFolder(itemSubFolder).files);
            for(;!eSubFolder_file.atEnd();eSubFolder_file.moveNext()){
                var itemSubFolder_file=eSubFolder_file.item();
                var flag = false;
                if(itemSubFolder_file.Type.indexOf("JPG")!=-1 || itemSubFolder_file.Type.indexOf("JPEG")!=-1
                        ||itemSubFolder_file.Type.indexOf("GIF")!=-1){
                    flag=true;
                    break;
                }
            }
            if(!eSubFolder_son.atEnd()||flag){
                var str_itemSubFolder=new String(itemSubFolder);
                itemSubFolder_=str_itemSubFolder.replace(new RegExp("\\\\","g"),"\\\\");
                tr = tr+"<tr><td><a href='#' onclick='getFolder(\""+itemSubFolder_+"\")'>"+itemSubFolder +"</a></td></tr>";
            }else{
                tr=tr+"<tr><td>"+itemSubFolder+"</td></tr>";
            }
            i++;
        }
        table = table + tr + "</table>";
        document.getElementById("listDir").innerHTML=table;
        var j=0;
        imglist="";
        for(;!eFiles.atEnd();eFiles.moveNext()){
            var itemFile= eFiles.item();
            if(itemFile.Type.indexOf("JPG")!=-1||itemFile.Type.indexOf("JPEG")!=-1
                    ||itemFile.Type.indexOf("GIF")!=-1){
                imglist = imglist+"<div style='width:160px;float:left;'>"+
                        "<input name='sourceImg' type='checkbox' id='sourceImg' class='noborder' value='"+itemFile+
                        "'><img src='"+itemFile+"' alt='"+itemFile.name+"' width='128' height='94' border='1'></div>";
                j++;
            }
        }
        document.getElementById("listFile").innerHTML=imglist;
    }
</script>
</body>
</html>
