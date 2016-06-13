package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!-- DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\" -->\r\n");
      out.write("<!-- DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\" -->\r\n");

String contextPath=request.getContextPath();

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "head.jsp", out, false);
      out.write("\r\n");
      out.write("<title>Sigma Grid JSON Demo</title>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" >\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("///////////////////////////////////////\r\n");
      out.write("var APP_PATH='");
      out.print(contextPath);
      out.write("';\r\n");
      out.write("\r\n");
      out.write("///////////////////////////////////////\r\n");
      out.write("\r\n");
      out.write("var grid_demo_id = \"myGrid1\" ;\r\n");
      out.write("\r\n");
      out.write("var dsOption= {\r\n");
      out.write("\r\n");
      out.write("\tfields :[\r\n");
      out.write("\t\t{name : 'order_no' , type: 'int' },\r\n");
      out.write("\t\t{name : 'employee'  },\r\n");
      out.write("\t\t{name : 'country'   },\r\n");
      out.write("\t\t{name : 'customer'  },\r\n");
      out.write("\t\t{name : 'order2005' ,type: 'float' },\r\n");
      out.write("\t\t{name : 'order2006' ,type: 'float' },\r\n");
      out.write("\t\t{name : 'order2007' ,type: 'float'  },\r\n");
      out.write("\t\t{name : 'order2008' ,type: 'float'  },\r\n");
      out.write("\t\t{name : 'delivery_date' ,type: 'date' }\r\n");
      out.write("\t],\r\n");
      out.write("\tuniqueField : 'no'\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var colsOption = [\r\n");
      out.write("\t\t{id: 'order_no' , header: \"order_no\" , width :55 },\r\n");
      out.write("\t\t{id: 'employee' , header: \"employee\" , width :100 , editable : false ,editor: { type :\"text\",validRule : ['required']} },\r\n");
      out.write("\t\t{id: 'country' , header: \"country\" , width :100,  editable : false ,editor: { type :\"text\" }\t},\r\n");
      out.write("\t\t{id: 'customer' , header: \"customer\" , width :100, editor: { type :\"text\",validRule : ['required']}\t},\r\n");
      out.write("\t\t{id: 'order2005' , header: \"order2005\" , width :100,  editable : false ,editor: { type :\"text\",validRule : ['float']}\t},\r\n");
      out.write("\t\t{id: 'order2006' , header: \"order2006\" , width :100,  editable : false ,editor: { type :\"text\",validRule : ['float']}\t},\r\n");
      out.write("\t\t{id: 'order2007' , header: \"order2007\" , width :100,  editable : false ,editor: { type :\"text\",validRule : ['float']}\t},\r\n");
      out.write("\t\t{id: 'order2008' , header: \"order2008\" , width :100,  editor: { type :\"text\",validRule : ['float']}\t},\r\n");
      out.write("\t\t{id: 'delivery_date' , header: \"delivery_date\" , width :100,   editor: { type :\"date\", format:'yyyy-MM-dd HH:mm:ss',\tvalidRule : ['datetime']}\t}\r\n");
      out.write(" ];\r\n");
      out.write("\r\n");
      out.write("var gridOption={\r\n");
      out.write("\tid : grid_demo_id,\r\n");
      out.write("\r\n");
      out.write("\t/* loadURL need to point to data feed url*/\r\n");
      out.write("\tloadURL : APP_PATH+'/views/controller.jsp?actionMethod=list',\r\n");
      out.write("\tsaveURL : APP_PATH+'/views/controller.jsp?actionMethod=save' ,\r\n");
      out.write("\tremotePaging : false ,\r\n");
      out.write("\twidth: \"700\",  \r\n");
      out.write("\theight: \"330\",  \r\n");
      out.write("\tcontainer : 'mygrid_container',\r\n");
      out.write("\ttoolbarPosition : 'bottom', \r\n");
      out.write("\ttoolbarContent : 'nav | pagesize | reload | add del save | filter | print | state',\r\n");
      out.write("\tpageSizeList : [10,20,30,50,100,200],\r\n");
      out.write("\tpageSize : 10,\r\n");
      out.write("\tdataset : dsOption ,\r\n");
      out.write("\tcolumns : colsOption ,\r\n");
      out.write("\trecountAfterSave : true ,\r\n");
      out.write("\tdefaultRecord : {\r\n");
      out.write("\t\t\torder_no : '-',\r\n");
      out.write("\t\t\temployee : '',\r\n");
      out.write("\t\t\tcountry : '',\r\n");
      out.write("\t\t\tcustomer : '(name)',\r\n");
      out.write("\t\t\torder2005 : 0.1,\r\n");
      out.write("\t\t\torder2006 : 0.1,\r\n");
      out.write("\t\t\torder2007 : 0.1,\r\n");
      out.write("\t\t\torder2008 : 0.1,\r\n");
      out.write("\t\t\tdelivery_date : '1910-01-01'\r\n");
      out.write("\t},\r\n");
      out.write("\tparameters : { a:123,b:222, c :[8,9] }\r\n");
      out.write("\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("var mygrid=new Sigma.Grid( gridOption );\r\n");
      out.write("\r\n");
      out.write("Sigma.Utils.onLoad( function(){\r\n");
      out.write("\tmygrid.render();\r\n");
      out.write("} );\r\n");
      out.write("\r\n");
      out.write("//////////////////////////////////////////////////////////\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body style=\"padding:0px;margin:10px;\">\r\n");
      out.write("<div id=\"page-container\">\r\n");
      out.write("   \r\n");
      out.write("  <div id=\"header\">\r\n");
      out.write("  <h1>Product - Sigma Grid</h1>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("  <div id=\"content\">\r\n");
      out.write("    \r\n");
      out.write("\t  <h2>Sigma Grid CRUD Sample</h2>\r\n");
      out.write("    <div id=\"mygrid_container\" style=\"border:0px solid #cccccc;background-color:#f3f3f3;padding:5px;width:100%;\" ></div>\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  <h2>ABOUT SIGMASOFT</h2>\r\n");
      out.write("Sigmasoft Technologies LLC is a software company providing cross-browser javascript GUI components and tools & services involved. Our aim is to make AJAX simple and easy.<br> \r\n");
      out.write("Sigmasoft also provides end-to-end solutions in web development (Web 2.0, PHP, ASP.NET, ASP, JSP, XML, Flash), application development and IT consulting services. Please send email to sales@sigmawidgets.com for further infomation.<br>\r\n");
      out.write("  <br><br>\r\n");
      out.write("  <div style=\"text-align:center;\" id=\"footer\">All contents are (c) Copyright 2005 - 2008, Sigma Software Inc. All rights Reserved</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
