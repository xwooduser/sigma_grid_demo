This jsp/java sample has passed test with
1.Apache Tomcat 6.0 (Windows) 
2.MySQL5.0.27-standard (Windows)

INSTALLATION
1. Unzip this package a dir that your tomcat can access,
say D:\sigma_sample\ for Windows or /usr/sigma_sample for Unix. Then you will see the following structure.
sigma_sample/.settings
sigma_sample/build/
sigma_sample/src/
sigma_sample/WebContent/
sigma_sample/work/
sigma_sample/CreatTable.sql
...

2. You need MYSQL installed on your box and run CreatTable.sql to set up a database. If you are using some other database, you have to modify CreatTable.sql a little bit before running it.

3. Deploy the app into your tomcat.
a. create dir named conf/Catalina/localhost under your tomcat base dir.
The tomcat dir looks like
Tomcat 6.0/bin/
Tomcat 6.0/common/
Tomcat 6.0/conf/
Tomcat 6.0/logs/
Tomcat 6.0/webapps/
Tomcat 6.0/conf/Catalina/localhost/
...

b. Copy sigma_sample.xml to conf/Catalina/localhost/. It looks like
Tomcat 6.0/conf/Catalina/localhost/gtdemo.xml

c. Open sigma_sample.xml and change docBase and workDir to the dir in step one.
<Context path="sigma_sample" reloadable="true" docBase="D:\sigma_sample\WebContent" workDir="D:\sigma_sample\work" />

d. Please change WebContent\views\controller.jsp to make sure it points to your database.

3. View the sample list1.jsp in your browser. Generally, the url could be 
http://localhost:8080/sigma_sample/views/list1.jsp

ABOUT SIGMASOFT
Sigmasoft Technologies LLC is a software company providing cross-browser javascript GUI components and tools & services involved. Our aim is to make AJAX simple and easy. 
Sigmasoft also provides end-to-end solutions in web development (Web 2.0, PHP, ASP.NET, ASP, JSP, XML, Flash), application development and IT consulting services. Please send email to sales@sigmawidgets.com for further infomation.

