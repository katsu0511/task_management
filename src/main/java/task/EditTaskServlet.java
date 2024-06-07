package task;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/edit/task")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
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
			
			// SQL
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset1 = null;
			ResultSet rset2 = null;
			boolean data_exists = false;
			
			// TASKIDの取得
			String TASKID = request.getParameter("id");
			
			// user_nameの取得
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
				
				// Task情報を取得
				String sql2 = "SELECT * FROM TASK WHERE TASKID=? AND USERID=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, TASKID);
				pstmt2.setString(2, USERID);
				rset2 = pstmt2.executeQuery();
				Map<String, String> task = new HashMap<String, String>();
				
				if (rset2.next()) {
					data_exists = true;
					task.put("TASKID", rset2.getString(1));
					task.put("USERID", rset2.getString(2));
					task.put("TASKNM", rset2.getString(3));
					task.put("TASKDT", rset2.getString(4));
					task.put("TSKSTT", rset2.getString(5));
					task.put("RGDTTM", rset2.getString(6));
					task.put("UPDTTM", rset2.getString(7));
				}
				
				request.setAttribute("task", task);
				
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
			
			if (data_exists) {
				request.getRequestDispatcher("/WEB-INF/app/task/edit_task.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/top");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			// SQL
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			ResultSet rset1 = null;
			
			// 送信情報の取得
			String TASKID = request.getParameter("id");
			String user_name = (String) session.getAttribute("user_name");
			String task_name = request.getParameter("task_name");
			String task_detail = request.getParameter("task_detail");
			String task_state = request.getParameter("task_state");
			
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
				
				// タスク変更
				String sql2 = "UPDATE TASK SET TASKNM=?, TASKDT=?, TSKSTT=? WHERE TASKID=? AND USERID=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, task_name);
				pstmt2.setString(2, task_detail);
				pstmt2.setString(3, task_state);
				pstmt2.setString(4, TASKID);
				pstmt2.setString(5, USERID);
				pstmt2.executeUpdate();
				
				// エンコード
				user_name = URLEncoder.encode(user_name, "UTF-8");
				task_name = URLEncoder.encode(task_name, "UTF-8");
				task_detail = URLEncoder.encode(task_detail, "UTF-8");
				task_state = URLEncoder.encode(task_state, "UTF-8");
				
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
			
			response.sendRedirect(request.getContextPath() + "/show/task?id=" + TASKID);
		}
	}

}
