function attachSelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();
		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].value;
			try{
				oInputField.add(option,null);
			}catch(ex){
				oInputField.add(option);
			}
			if (option.value==value){
				option.selected=true;
			}
		}
	});
}

//checkbox框只让其提交0或1
function attachCheckBox(oCheckField,oHiddenField){
	if (jQuery(oHiddenField).val()==1){
		oCheckField.checked = true;
	}else{
		oCheckField.checked = false;
	}
	jQuery(oCheckField).click(function(){
		if (oCheckField.checked)
			jQuery(oHiddenField).val("1");
		else
			jQuery(oHiddenField).val("0");
	});
}

/*
 * 包含新增页面的框架
 * title：标题
 * url：包含页面的地址
 * formId：包含页面的form的ID
 * width：包含页面的宽度
 * height：包含页面的高度
 */
function addrow(title, url, datagrid, width, height) {
	title = title ? title : '';
	datagrid = datagrid ? datagrid : 'datagrid';
	width = width ? width : 400;
	height = height ? height : 300;
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url,
		width : width,
		height : height,
		buttons : [ {
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			},{
			text : '添加',
			iconCls:'icon-ok',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog,$("#"+datagrid), parent.$);
			}
		} ]
	});
}






function updaterow(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	width = width ? width : 600;
	height = height ? height : 400;
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要编辑的数据",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一行数据进行编辑",'info');
		return;
	}
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?"+pk+"="+rows[0][pk],
		width : width,
		height : height,
		buttons : [ {
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			},{
			text : '编辑',
			iconCls:'icon-ok',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog,$("#"+datagrid), parent.$);
			}
		} ]
	});
}


function updaterows(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	width = width ? width : 600;
	height = height ? height : 400;
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要操作的数据",'info');
		return;
	}
	var params = "";
	$.each(rows,function(i,obj){
 		var pkValue = obj[pk];
 		if(i==0) 
 			params += pkValue;
 		else
 			params += ","+pkValue;
 	});
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?ids="+params,
		width : width,
		height : height,
		buttons : [ {
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			},{
			text : '编辑',
			iconCls:'icon-ok',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog,$("#"+datagrid), parent.$);
			}
		} ]
	});
}


/*
 * 删除操作
 * url：删除操作的地址
 * datagrid：新增页面父列表框的ID，用于新增后涮新父列表
 */
function deleterow(url,datagrid,pkName){
		var rows = $('#'+datagrid).datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert('提示','请选择要删除的项！','info');
			return;
		}
		$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	var ps = "";
        	$.each(rows,function(i,obj){
        		var pkValue = obj[pkName];
        		/*$.each(n,function(key,value){
        			if(key==pkName){
        				pkValue = value;
        			}
        		});*/
        		if(i==0) 
        			ps += "?PK="+pkValue;
        		else
        			ps += "&PK="+pkValue;
        	});
        	//alert(ps);
        	$.post(url+ps,function(data){
	        	$('#'+datagrid).datagrid('reload'); 
        		$.messager.alert('提示','数据删除成功','info');
        	});
        }
    });
	}


function show(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要查看的项",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一项",'info');
		return;
	}
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?"+pk+"="+rows[0][pk],
		width : width,
		height : height,
		buttons : [ {
			text:'关闭',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			} ]
	});
}



function show(title,url,width,height){
	width = width ? width : 600;
	height = height ? height : 400;
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url,
		width : width,
		height : height,
		buttons : [ {
			text:'关闭',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			} ]
	});
}

// 获取页面的高度、宽度
function getPageSize() {
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else {
        if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac    
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari    
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer    
        if (document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else {
        if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode    
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else {
            if (document.body) { // other Explorers    
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }
        }
    }       
    // for small pages with total height less then height of the viewport    
    if (yScroll < windowHeight) {
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }    
    // for small pages with total width less then width of the viewport    
    if (xScroll < windowWidth) {
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }
    arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
    //alert(windowWidth+" "+windowHeight);
    return arrayPageSize;
}


/**  
 * StringBuffer Class, to join two string is the most use  
 *   
 */  
function StringBuffer()   
{   
    this._strings = [];   
    if(arguments.length==1)   
    {   
        this._strings.push(arguments[0]);   
    }   
}   
  
StringBuffer.prototype.append = function(str)   
{   
    this._strings.push(str);   
    return this;   
}; 
  
StringBuffer.prototype.toString = function()   
{   
    return this._strings.join("");   
};
  
/* 返回长度 */  
StringBuffer.prototype.length = function()   
{   
    var str = this._strings.join("");   
    return str.length;   
};   
  
/* 删除后几位字符 */  
StringBuffer.prototype.del = function(num)   
{   
    var len = this.length();   
    var str = this.toString();   
    str = str.slice(0,len-num);   
    this._strings = [];   
    this._strings.push(str);   
};  


