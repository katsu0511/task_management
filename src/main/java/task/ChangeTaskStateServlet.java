package task;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TaskManagementDAO;

/**
 * Servlet implementation class ChangeTaskStateServlet
 */
@WebServlet("/change/task_state")
public class ChangeTaskStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTaskStateServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			
			try {
				// データベース接続情報取得
				conn = db.getConnection();
				
				// 送信情報の取得
				String user_name = (String) session.getAttribute("user_name");
				String TASKID = request.getParameter("id");
				
				// USERIDの取得
				String sql1 = "SELECT USERID FROM USER WHERE USERNM=?";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, user_name);
				rset1 = pstmt1.executeQuery();
				String USERID = null;
				
				if (rset1.next()) {
					USERID = rset1.getString(1);
				}
				
				// SQL実行準備
				String sql2 = "UPDATE TASK SET TSKSTT=? WHERE TASKID=? AND USERID=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, "completed");
				pstmt2.setString(2, TASKID);
				pstmt2.setString(3, USERID);
				pstmt2.executeUpdate();
				
				// エンコード
				user_name = URLEncoder.encode(user_name, "UTF-8");
				TASKID = URLEncoder.encode(TASKID, "UTF-8");

				// レスポンス処理
				response.setContentType("text/plain");
				response.setCharacterEncoding("utf8");
				
			} catch (Exception err) {
				((HttpServletResponse) request).sendError(HttpServletResponse.SC_BAD_REQUEST);
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
		}
	}

}
