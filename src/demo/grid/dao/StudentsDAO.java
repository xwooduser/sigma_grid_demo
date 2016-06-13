package demo.grid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fins.gt.dataaccess.BaseDAO;
import com.fins.gt.dataaccess.TransactionWrapper;


/**
 * DAO 的编写不局限于任何一种形势 ,只要按照习惯的做法去做就可以.
 * 不过有一点一定要遵守: 查询相关的dao方法的返回值 一定要能够序列化成json字符串.
 * 示例中使用的是 json.org 提供的工具来做的这个工作.
 * 你可以寻找满足你需求的其他json第三方工具来做这些转换.
 */
public class StudentsDAO extends BaseDAO {
	
	
	public int countAllStudents(){
		Integer total=countTable("student_info");
		return total==null?-1:total.intValue();
	}
	
	public List getStudentsListByDep(Map param){
		List list=executeQuery(
				"SELECT * FROM student_info WHERE 1=0 " +
				"#{IF:depNo!=EMPTY} OR department = #{depNo} #{/IF} "
				,param);
		return list;
	}
	
	public List getStudentsByPage(int startRowNum , int pageSize){
		Map pageInfo=new HashMap();
		pageInfo.put("startRowNum", new Integer(startRowNum));
		pageInfo.put("pageSize", new Integer(pageSize));
		
		List list=executeQuery("SELECT * FROM student_info  "+
					"#{IF:pageSize!=NULL} LIMIT #{pageSize} #{/IF} "+
					"#{IF:startRowNum!=NULL} OFFSET #{startRowNum}-1 #{/IF}",
					pageInfo);
		return list;
	}
	
	public List getSortedStudentsByPage(Map sortInfo , int startRowNum , int pageSize){
		Map pageInfo=new HashMap();
		pageInfo.put("startRowNum", new Integer(startRowNum));
		pageInfo.put("pageSize", new Integer(pageSize));
		
		String sortCond = null;
		if (sortInfo!=null){
			String fieldName = (String)sortInfo.get("fieldName");
			String sortOrder = (String)sortInfo.get("sortOrder");
			if (!sortOrder.equalsIgnoreCase("defaultsort")){
				sortCond=" "+fieldName+" "+sortOrder+" ";
			}
		}
		
		List list=executeQuery("SELECT * FROM student_info  "+ 
					(sortCond==null?"":" order by "+sortCond)+
					"#{IF:pageSize!=NULL} LIMIT #{pageSize} #{/IF} "+
					"#{IF:startRowNum!=NULL} OFFSET #{startRowNum}-1 #{/IF}",
					pageInfo);
		return list;
	}
	
	public List getAllStudents(){
		List list=executeQuery("SELECT * FROM student_info order by no");
		return list;
	}
	public boolean updateStudents(List updatedList){
		if (updatedList!=null){
			executeBatchUpdate("UPDATE student_info SET name=#{name}, birthday=#{birthday}, gender=#{gender}, department=#{department} WHERE no=#{no} ",
						updatedList);
		}
		 return true;
	}
	public boolean deleteStudents(List deletedList){
		if (deletedList!=null){
				executeBatchUpdate("DELETE FROM student_info WHERE no=#{no} ",
						deletedList);
		}
		 return true;
	}
	public boolean insertStudents(List insertedList){
		if (insertedList!=null){
				executeBatchUpdate("INSERT INTO student_info (name,gender,birthday,department,memo) VALUES( #{name}, #{gender}, #{birthday}, #{department}, #{memo} ); ",
						insertedList);
		}
		 return true;
	}
	
	public boolean saveStudents(final List insertedRecords , final List updatedList, final List deletedRecords ){
		// 一个简单的事务机制, 将增删改编入一个事务.
		TransactionWrapper txWrapper=new TransactionWrapper(){
			public void transaction() throws Exception {
				insertStudents(insertedRecords);
				updateStudents(updatedList);
				deleteStudents(deletedRecords);
			}
		};
		
		return txWrapper.execute();
	}

}
