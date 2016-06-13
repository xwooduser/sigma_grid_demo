<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*"%>
<%@ page import="com.fins.gt.server.*"%>
<%!
	Connection getConnection(){
		
		String url="jdbc:mysql://localhost:3306/sigma_grid_server";
		Connection conn= null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url,"root","");
		}catch(Exception e){
			
		}
		return conn;
	}

	void closeConnection(Connection conn){
		try{
			conn.close();
		}catch(Exception e){
			
		}
	}


	List listOrders(){
	   Connection conn = getConnection();
	   if(conn==null) 
	 		return new ArrayList();
	   Statement stmt = null;
	   
	   ResultSet rs = null;
	   List list = new ArrayList();
	   
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery("select * from orders");
		   while(rs.next()){
				Map map = new HashMap();
				map.put("order_no",new Long(rs.getLong("order_no")));
				map.put("employee",rs.getString("employee"));
				map.put("country",rs.getString("country"));
				map.put("customer",rs.getString("customer"));
				map.put("order2005",new Float(rs.getFloat("order2005")));
				map.put("order2006",new Float(rs.getFloat("order2006")));
				map.put("order2007",new Float(rs.getFloat("order2007")));
				map.put("order2008",new Float(rs.getFloat("order2008")));
				map.put("delivery_date",rs.getString("delivery_date"));
				list.add(map);
		   }
		   rs.close();
		   stmt.close();
	   }catch(Exception e){
	   }
	   closeConnection(conn);
	   return list;
	}
	
	int[] insertOrders( List updatedList){
		
		int[] opresults=null;
		String sql="INSERT INTO  orders (employee,country,customer,order2005,order2006,order2007,order2008,delivery_date) VALUES (?,?,?,?,?,?,?,?)";
			Connection conn=null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0;i<updatedList.size();i++){
					Map record= (Map)updatedList.get(i);
					pstmt.setString(1,String.valueOf(record.get("employee")));
					pstmt.setString(2,String.valueOf(record.get("country")));
					pstmt.setString(3,String.valueOf(record.get("customer")));
					pstmt.setString(4,String.valueOf(record.get("order2005")));
					pstmt.setString(5,String.valueOf(record.get("order2006")));
					pstmt.setString(6,String.valueOf(record.get("order2007")));
					pstmt.setString(7,String.valueOf(record.get("order2008")));
					pstmt.setString(8,String.valueOf(record.get("delivery_date")));
					pstmt.addBatch();
				}
				opresults = pstmt.executeBatch();
			} catch (SQLException e) {
				opresults=null;
			}finally{
				closeConnection(conn);
			}
	return opresults;
}
	
	int[] updateOrders( List updatedList){
			
			int[] opresults=null;
			String sql="UPDATE orders SET customer=?, order2008=?, delivery_date=? WHERE order_no= ? ";
				Connection conn=null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					for (int i=0;i<updatedList.size();i++){
						Map record= (Map)updatedList.get(i);
						pstmt.setString(1,String.valueOf(record.get("customer")));
						pstmt.setString(2,String.valueOf(record.get("order2008")));
						pstmt.setString(3,String.valueOf(record.get("delivery_date")));
						pstmt.setString(4,String.valueOf(record.get("order_no")));
						pstmt.addBatch();
					}
					opresults = pstmt.executeBatch();
				} catch (SQLException e) {
					opresults=null;
				}finally{
					closeConnection(conn);
				}
		return opresults;
	}
	
	
	int[] deleteOrders( List updatedList){
			
			int[] opresults=null;
			String sql="DELETE FROM orders WHERE order_no= ? ";
				Connection conn=null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					for (int i=0;i<updatedList.size();i++){
						Map record= (Map)updatedList.get(i);
						pstmt.setString(1,String.valueOf(record.get("order_no")));
						pstmt.addBatch();
					}
					opresults = pstmt.executeBatch();
				} catch (SQLException e) {
					opresults=null;
				}finally{
					closeConnection(conn);
				}
		return opresults;
	}
	
	boolean saveOrders(List insertedRecords , List updatedList, List deletedRecords){
		//you can control transaction, commit, rollback here
		int[] insertCodes = insertOrders(insertedRecords);
		int[] updateCodes = updateOrders(updatedList);
		int[] deleteCodes = deleteOrders(deletedRecords);
		boolean success=insertCodes!=null && updateCodes!=null && deleteCodes!=null;
		return success;
	}

%>
<%

	// GridServerHandler is server side wrapper, you can get all the info posted to server in your Java way instead of JavaScript
	GridServerHandler gridServerHandler=new GridServerHandler(request,response);
	
	String operation = request.getParameter("actionMethod");
	if("save".equals(operation)){

		boolean success=true;
			
		//to get the appended records here. Every record is in a map 
		List insertedRecords = gridServerHandler.getInsertedRecords();
		//to get the updated records here. Every record is in a map 
		List updatedList = gridServerHandler.getUpdatedRecords();
		//to get the deleted records here. Every record is in a map 
		List deletedRecords = gridServerHandler.getDeletedRecords();

	
		// if you are using beanm, you could get records with : xxx.getXXXXXXRecords(Class beanClass);
		//example - List updateList = gridServerHandler.getUpdatedRecords(OrderVO.class);
		
		//to do update delete insert on real database
		success = saveOrders(insertedRecords , updatedList,  deletedRecords );

		
		//set result
		gridServerHandler.setSuccess(success);
		
		//if failure, you could send some message to client 
    //gridServerHandler.setSuccess(false);
    //gridServerHandler.setException("... exception info ...");

		//to print out JSON string to client
		out.print(gridServerHandler.getSaveResponseText());
		
	}else { //client is retrieving data
		List list = listOrders();
		
    //get how many records we are sending
		int totalRowNum= list.size();
		gridServerHandler.setTotalRowNum(totalRowNum);
		
		//if you would like paginal output on server side, you may interested in the following 4 methods
		// gridServerHandler.getStartRowNum() first record no of current page
		// gridServerHandler.getEndRowNum() last record no of current page
		// gridServerHandler.getPageSize() how many records per page holds
		// gridServerHandler.getTotalRowNum() how many records in total
		
		
		// we take map as this sample, you need to use gridServerHelp.setData(list,BeanClass.class); to deal with bean
		gridServerHandler.setData(list);
	  // gridServerHandler.setException("your exception message");
		
		//print out JSON string to client
		out.print(gridServerHandler.getLoadResponseText());
		
		//you could get the posted data by calling gridServerHandler.getLoadResponseText() and obtain more flexibility, such as chaning contentType or encoding of response.
	
		

	}
%>