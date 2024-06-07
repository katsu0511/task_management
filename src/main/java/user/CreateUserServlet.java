package user;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.TaskManagementDAO;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/create/user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
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
			request.getRequestDispatcher("/WEB-INF/app/user/create_user.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/top");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user_name") == null || session.getAttribute("password") == null) {
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
			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password2");
			boolean userInDB = false;
			
			try {
				// データベース接続情報取得
				conn = db.getConnection();
				
				// user_nameが既に登録されているか確認
				String sql1 = "SELECT * FROM USER WHERE USERNM=?";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1, user_name);
				rset1 = pstmt1.executeQuery();
				if (rset1.next()) {
					userInDB = true;
				}
				
				if (userInDB) {
					// user_nameが既に登録されている場合
					String errorMessage = "ユーザー名がすでに登録されています";
					request.setAttribute("errorMessage", errorMessage);
					request.setAttribute("user_name", user_name);
					request.setAttribute("password", password);
				} else {
					// USERに保存
					String sql2 = "INSERT INTO USER(USERNM,PASSWD) VALUES(?,?)";
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setString(1, user_name);
					pstmt2.setString(2, password);
					pstmt2.executeUpdate();
					
					session.setAttribute("user_name", user_name);
					session.setAttribute("password", password);
					
					Cookie[] cookies = request.getCookies();
					Cookie cookieUserName = null;
					Cookie cookiePassword = null;
					
					// エンコード
					user_name = URLEncoder.encode(user_name, "UTF-8");
					password = URLEncoder.encode(password, "UTF-8");
					
					if (cookies != null) {
						for  (Cookie cookie : cookies) {
							if (cookie.getName().equals("user_name")) {
								cookieUserName = cookie;
							} else if (cookie.getName().equals("password")) {
								cookiePassword = cookie;
							}
						}
					}
					
					if (cookieUserName != null) {
						cookieUserName.setValue(user_name);
					} else {
						cookieUserName = new Cookie("user_name", user_name);
					}
					
					if (cookiePassword != null) {
						cookiePassword.setValue(password);
					} else {
						cookiePassword = new Cookie("password", password);
					}
					
					response.addCookie(cookieUserName);
					response.addCookie(cookiePassword);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt1.close();
				} catch (SQLException e) { }
				
				try {
					if (!userInDB) {
						pstmt2.close();
					}
				} catch (SQLException e) { }
				
				try {
					conn.close();
				} catch (SQLException e) {  }
			}
			
			if (userInDB) {
				request.getRequestDispatcher("/WEB-INF/app/user/create_user.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/top");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/top");
		}
	}

}
