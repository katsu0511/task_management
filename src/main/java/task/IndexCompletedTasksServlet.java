package task;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TaskManagementDAO;

/**
 * Servlet implementation class IndexCompletedTasksServlet
 */
@WebServlet("/index/completed_tasks")
public class IndexCompletedTasksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexCompletedTasksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user_name") == null || session.getAttribute("password") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			// 文字化け対策
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			// 接続情報
			TaskManagementDAO db = new TaskManagementDAO();
			Connection conn = null;
			
			// SQL情報管理
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset1 = null;
			ResultSet rset2 = null;
			
			// ログイン情報の取得
			String user_name = (String) session.getAttribute("user_name");

			try {
				// データベース接続情報取得
				conn = db.getConnection();
				
				// USERIDの取得
				String sql1 = "SELECT USERID FROM USER WHERE USERNM=?";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, user_name);
				rset1 = pstmt1.executeQuery();
				String USERID = null;
				
				if (rset1.next()) {
					USERID = rset1.getString(1);
				}
				
				// 完了したTaskの取得
				String sql2 = "SELECT * FROM TASK WHERE USERID=? AND TSKSTT=? ORDER BY TASKID DESC";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, USERID);
				pstmt2.setString(2, "completed");
				rset2 = pstmt2.executeQuery();
				ArrayList<Map<String, String>> tasks = new ArrayList<Map<String, String>>();
				
				while (rset2.next()) {
					Map<String, String> task = new HashMap<>();
					task.put("TASKID", rset2.getString(1));
					task.put("TASKNM", rset2.getString(3));
					task.put("TASKCT", rset2.getString(4));
					tasks.add(task);
				}
				
				request.setAttribute("tasks", tasks);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt1.close();
				} catch (SQLException e) { }
				
				try {
					pstmt2.close();
				} catch (SQLException e) { }
				
				try {
					conn.close();
				} catch (SQLException e) {  }
			}
			
			request.getRequestDispatcher("/WEB-INF/app/task/index_completed_task.jsp").forward(request, response);
		}
	}

}
