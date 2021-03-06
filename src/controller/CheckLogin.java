package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.User;



/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CheckLoginUser(request, response);
		
	}
	private void CheckLoginUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String account = request.getParameter("account");
		String pass = request.getParameter("pass");
		boolean errInput =false;
		String errUserLogin ="";
		String errUser ="";
		if (account.equals("") || account.equals(null) || pass.equals("") || account.equals(null)) {
			errInput = true;
			errUser ="Vui lòng điền tên đăng nhập và mật khẩu của bạn !";
			request.setAttribute("errInput", errInput);
			request.setAttribute("errUser", errUser);

			return;
		}
			
		if (errInput ==false) {
			User user = Login.Login(account, pass);
			if (user == null) {
				errUserLogin = "Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại !";
				request.setAttribute("errUserLogin", errUserLogin);
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				response.sendRedirect("index.jsp");
			}
			
		}

	}

	
	public static void main(String[] args) {
		System.out.println(Login.Login("nguyentanlucpro4", "12345"));
	}

}
