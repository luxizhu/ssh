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