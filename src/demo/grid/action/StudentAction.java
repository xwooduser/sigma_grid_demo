package demo.grid.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.fins.gt.action.BaseAction;
import com.fins.gt.server.GridServerHandler;

import demo.grid.dao.StudentsDAO;


/**
 *  Action : "我不是单例的"
 */
public class StudentAction extends BaseAction {
	
	
	/**
	 * 根据院系信息 查询学生信息.
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getStudentsListByDep() throws ServletException, IOException {
		// 此方法内的代码 类似 DepartmentAction.getDepartmentList, 不再详述.
		
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);
	
		StudentsDAO dao=new StudentsDAO();
		Map param = getParameterSimpleMap() ;
		List list = dao.getStudentsListByDep(param);

		int totalRowNum = list.size();
		gridServerHandler.setTotalRowNum(totalRowNum);
		gridServerHandler.setData(list);

		print(gridServerHandler.getLoadResponseText());
	}
	
	/**
	 * 取得所有学生信息(分页)
	 */
	public void getList() throws ServletException, IOException {
		StudentsDAO dao=new StudentsDAO();
		List list=null;
		
		// GT-Grid一个很关键的服务端处理器, 可以用来实现很多功能, 以后还会继续扩充和完善.
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);
		
		// 取得总记录数
		int totalRowNum=gridServerHandler.getTotalRowNum();
		//如果总数不存在, 那么调用"相应的方法" 来取得总数
		if (totalRowNum<1){
			totalRowNum=dao.countAllStudents();
			//将取得的总数赋给gridServerHandler
			gridServerHandler.setTotalRowNum(totalRowNum);
		}
		
		//调用"相应的方法" 来取得数据.下面4个方法 通常对于进行分页查询很有帮助,根据需要使用即可.
		// gridServerHandler.getStartRowNum() 当前页起始行号
		// gridServerHandler.getEndRowNum() 当前页结束行号
		// gridServerHandler.getPageSize() 每页大小
		// gridServerHandler.getTotalRowNum() 记录总条数
		list=dao.getStudentsByPage(gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
		
		// 本例中list里的元素是 map, 
		// 如果元素是bean, 请使用 gridServerHelp.setData(list,BeanClass.class);
		gridServerHandler.setData(list);
//		gridServerHandler.setException("your exception message");
		
		//向客户端输出json字符串.
		print(gridServerHandler.getLoadResponseText());
		
		// 你也可以 使用 gridServerHandler.getLoadResponseText() 来取得字符串.
		// 然后自行向 客户端输出, 这样做的好处是 你可以自己来决定response的contentType和编码.
	}
	
	
	/**
	 * 取得所有学生信息(不分页)
	 */
	public void getAllList() throws ServletException, IOException {
		StudentsDAO dao=new StudentsDAO();
		List list=null;
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);

		list=dao.getAllStudents();
		
		gridServerHandler.setData(list);

		print(gridServerHandler.getLoadResponseText());

	}
	
	
	
	/**
	 * 增删改对应的action 方法
	 */
	public void doSave() throws ServletException, IOException {
		StudentsDAO dao=new StudentsDAO();
		boolean success=true;
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);
		
		//取得新增的数据集合, 每条数据记录在 map 里
		List insertedRecords = gridServerHandler.getInsertedRecords();
		//取得修改的数据集合, 每条数据记录在 map 里
		List updatedList = gridServerHandler.getUpdatedRecords();
		//取得删除的数据集合, 每条数据记录在 map 里
		List deletedRecords = gridServerHandler.getDeletedRecords();

		// 如果希望取得bean的集合 那么请使用有参方法: xxx.getXXXXXXRecords(Class beanClass);
		//例如: List updateList = gridServerHandler.getUpdatedRecords(StudentVO.class);
		
		//调用"相应的方法" 来update delete insert数据
		success = dao.saveStudents(insertedRecords , updatedList,  deletedRecords );

		
		//设置该次操作是否成功.
		gridServerHandler.setSuccess(success);
		
		//如果操作不成功 你也可以自定义一些异常信息发送给客户端.
//		gridServerHandler.setSuccess(false);
//		gridServerHandler.setException("... exception info ...");

		//向客户端输出json字符串.
		print(gridServerHandler.getSaveResponseText());
	}

}
