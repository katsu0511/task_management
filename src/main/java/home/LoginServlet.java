package home;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBManager;
import db.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
			Cookie[] cookies = request.getCookies();
			String cookieUserName = null;
			String cookiePassword = null;
			
			if (cookies != null) {
				for  (Cookie cookie : cookies) {
					if (cookie.getName().equals("user_name")) {
						cookieUserName = cookie.getValue();
					} else if (cookie.getName().equals("password")) {
						cookiePassword = cookie.getValue();
					}
				}
			}
			
			if (cookieUserName != null) {
				cookieUserName = URLDecoder.decode(cookieUserName, "UTF-8");
			}
			
			if (cookiePassword != null) {
				cookiePassword = URLDecoder.decode(cookiePassword, "UTF-8");
			}
			
			request.setAttribute("user_name", cookieUserName);
			request.setAttribute("password", cookiePassword);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/top");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");

		// エラーメッセージ
		String errorMessage = null;
		
		// データベース接続管理クラスの変数宣言
		DBManager dbm = new DBManager();
		
		// ログインユーザー情報取得
		UserDTO user = dbm.getLoginUser(user_name, password);
			
		if (user_name.equals("") || password.equals("")) {
			errorMessage = "ユーザー名とパスワードを入力してください";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("user_name", user_name);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);
		} else if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user_name", user_name);
			session.setAttribute("password", password);
			
			Cookie[] cookies = request.getCookies();
			Cookie cookieUserName = null;
			Cookie cookiePassword = null;
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
			response.sendRedirect(request.getContextPath() + "/top");
		} else {
			errorMessage = "ユーザー名かパスワードが間違っています";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("user_name", user_name);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/app/home/login.jsp").forward(request, response);
		}
	}

}
