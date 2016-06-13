package demo.grid.action;

import java.io.IOException;

import javax.servlet.ServletException;

import com.fins.gt.action.BaseAction;

public class Index extends BaseAction {

	public void service() throws ServletException, IOException {
		forward("/main");
	}
}
