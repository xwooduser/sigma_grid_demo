<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" -->
<!-- DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd" -->
<%
String contextPath=request.getContextPath();
%>
<html>
<head>
<jsp:include  page="/views/head.jsp" />
<title>GT.Grid JSON 版 Demo -- 基础示例</title>

<script type="text/javascript" >


///////////////////////////////////////
var APP_PATH='<%=contextPath%>';

///////////////////////////////////////

var grid_demo_id = "myGrid1" ;

var dsOption= {

	fields :[
		{name : 'no'  },
		{name : 'name'  },
		{name : 'birthday' ,type:'date'  },
		{name : 'gender' ,type: 'int' },
		{name : 'department' ,type: 'string'  },
		{name : 'memo' ,type: 'string' }
	],

	uniqueField : 'no'
}

var colsOption = [
		
		{id: 'no' , header: "学号" , width :55 },

		{id: 'name' , header: "姓名" , width :100 ,editor: { type :"text",validRule : ['required']} },

		{id: 'birthday' , header: "生日" , width :100, editor: { type :"date",validRule : ['date']}	},

		{id: 'gender' , header: "性别" , width :100, 
			editor : { type :"select" ,options : {'0': '未知' ,'1':'男', '2':'女'} ,defaultText : '' },
			renderer : 'by editor',
			filterField :　GT.Utils.createSelectHTML( {'0': '未知' ,'1':'男', '2':'女'} )
		},

		{id: 'department' , header: "院系" , width :100, 
			editor: { type :"text",validRule : ['required','int']}
		}
 ];

var gridOption={

	//autoSaveOnNav : true ,
	//reloadAfterSave : false, 
	id : grid_demo_id,

	/* loadURL 支持函数, 该函数返回值是response对象 */
	loadURL : APP_PATH+'/studentAction.do?doMethod=getList',

	saveURL : APP_PATH+'/studentAction.do?doMethod=doSave' ,

	width: "700",  //"100%", // 700,
	height: "330",  //"100%", // 330,

	container : 'mygrid_container',

	toolbarPosition : 'bottom', // 'top',  工具条显示的位置, 暂时不支持上下都显示.
	toolbarContent : 'nav | pagesize | reload | add del save | filter | print | state',

	pageSizeList : [10,20,30,50,100,200],

	dataset : dsOption ,

	columns : colsOption ,
	
	recountAfterSave : true ,
	
	onComplete : function(){  /*  列表创建后,并且数据载入完成 时触发的函数  */  },

		/*  新加记录时,默认的数据(类似于一个新记录的模板) */
	defaultRecord : {
			no : 0,
			name : '(请输入姓名)',
			birthday : '1910-01-01',
			gender : 0,
			department : 0,
			memo : ''
	},
	parameters : { a:123,b:222, c :[8,9] }

};



var mygrid=new GT.Grid( gridOption );

GT.Utils.onLoad( function(){
	mygrid.render();
} );

//////////////////////////////////////////////////////////


</script>
</head>

<body style="padding:0px;margin:10px;">
<span style="font-size:14px; padding:3px;text-align:center;width:200px">
<a href="<%=contextPath%>/index.do">&#160;&#160;返回&#160;demo列表&#160;&#160;</a>
</span>

<div id="mygrid_container" style="border:0px solid #cccccc;background-color:#f3f3f3;padding:5px;width:100%;" >
</div>

 </body>
</html>