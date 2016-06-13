<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" -->
<!-- DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd" -->
<%
String contextPath=request.getContextPath();
%>
<html>
<head>
<jsp:include  page="head.jsp" />
<title>Sigma Grid JSON Demo</title>

<script type="text/javascript" >


///////////////////////////////////////
var APP_PATH='<%=contextPath%>';

///////////////////////////////////////

var grid_demo_id = "myGrid1" ;

var dsOption= {

	fields :[
		{name : 'order_no' , type: 'int' },
		{name : 'employee'  },
		{name : 'country'   },
		{name : 'customer'  },
		{name : 'order2005' ,type: 'float' },
		{name : 'order2006' ,type: 'float' },
		{name : 'order2007' ,type: 'float'  },
		{name : 'order2008' ,type: 'float'  },
		{name : 'delivery_date' ,type: 'date' }
	],
	uniqueField : 'no'
}

var colsOption = [
		{id: 'order_no' , header: "order_no" , width :55 },
		{id: 'employee' , header: "employee" , width :100 , editable : false ,editor: { type :"text",validRule : ['required']} },
		{id: 'country' , header: "country" , width :100,  editable : false ,editor: { type :"text" }	},
		{id: 'customer' , header: "customer" , width :100, editor: { type :"text",validRule : ['required']}	},
		{id: 'order2005' , header: "order2005" , width :100,  editable : false ,editor: { type :"text",validRule : ['float']}	},
		{id: 'order2006' , header: "order2006" , width :100,  editable : false ,editor: { type :"text",validRule : ['float']}	},
		{id: 'order2007' , header: "order2007" , width :100,  editable : false ,editor: { type :"text",validRule : ['float']}	},
		{id: 'order2008' , header: "order2008" , width :100,  editor: { type :"text",validRule : ['float']}	},
		{id: 'delivery_date' , header: "delivery_date" , width :100,   editor: { type :"date", format:'yyyy-MM-dd HH:mm:ss',	validRule : ['datetime']}	}
 ];

var gridOption={
	id : grid_demo_id,

	/* loadURL need to point to data feed url*/
	loadURL : APP_PATH+'/views/controller.jsp?actionMethod=list',
	saveURL : APP_PATH+'/views/controller.jsp?actionMethod=save' ,
	remotePaging : false ,
	width: "700",  
	height: "330",  
	container : 'mygrid_container',
	toolbarPosition : 'bottom', 
	toolbarContent : 'nav | pagesize | reload | add del save | filter | print | state',
	pageSizeList : [10,20,30,50,100,200],
	pageSize : 10,
	dataset : dsOption ,
	columns : colsOption ,
	recountAfterSave : true ,
	defaultRecord : {
			order_no : '-',
			employee : '',
			country : '',
			customer : '(name)',
			order2005 : 0.1,
			order2006 : 0.1,
			order2007 : 0.1,
			order2008 : 0.1,
			delivery_date : '1910-01-01'
	},
	parameters : { a:123,b:222, c :[8,9] }

};



var mygrid=new Sigma.Grid( gridOption );

Sigma.Utils.onLoad( function(){
	mygrid.render();
} );

//////////////////////////////////////////////////////////


</script>
</head>

<body style="padding:0px;margin:10px;">
<div id="page-container">
   
  <div id="header">
  <h1>Product - Sigma Grid</h1>
  </div>

  <div id="content">
    
	  <h2>Sigma Grid CRUD Sample</h2>
    <div id="mygrid_container" style="border:0px solid #cccccc;background-color:#f3f3f3;padding:5px;width:100%;" ></div>

  </div>
  
  
  <h2>ABOUT SIGMASOFT</h2>
Sigmasoft Technologies LLC is a software company providing cross-browser javascript GUI components and tools & services involved. Our aim is to make AJAX simple and easy.<br> 
Sigmasoft also provides end-to-end solutions in web development (Web 2.0, PHP, ASP.NET, ASP, JSP, XML, Flash), application development and IT consulting services. Please send email to sales@sigmawidgets.com for further infomation.<br>
  <br><br>
  <div style="text-align:center;" id="footer">All contents are (c) Copyright 2005 - 2008, Sigma Software Inc. All rights Reserved</div>
</div>



 </body>
</html>