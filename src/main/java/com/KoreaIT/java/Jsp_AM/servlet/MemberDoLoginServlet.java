package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName(Config.getDbDriverClassName());
		} catch (ClassNotFoundException e) {
			
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPw());
			

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);


			
			boolean isLoginableId = DBUtil.selectRowIntValue(conn, sql) == 1;
			//1을 가져오면 있다는 뜻 1과 1은 같으니까 true 그런 아이디가 존재하면 트루
			
		
			if (isLoginableId != true) {
				response.getWriter().append(String.format(
						"<script>alert('로그인 할수 없는 아이디 입니다'); location.replace('../member/login');</script>", loginId));
				return;
			}
			

			sql = SecSql.from("SELECT loginPw");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);
			
			Map<String ,Object> selectRow = DBUtil.selectRow(conn, sql);
			
			if (!selectRow.get("loginPw").equals(loginPw)) {
				response.getWriter().append(String.format(
						"<script>alert('비밀번호가 일치하지 않습니다'); location.replace('../member/login');</script>", loginId));
				return;
			}
			
			response.getWriter().append(String.format(
					"<script>alert('로그인이 완료되었습니다.'); location.replace('../article/list');</script>"));

		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}