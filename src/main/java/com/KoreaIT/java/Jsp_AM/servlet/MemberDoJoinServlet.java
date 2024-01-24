package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/DoJoin")
public class MemberDoJoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// DB연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/JSP_AM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			
			String loginId = request.getParameter("loginId");
			String name = request.getParameter("name");
			String loginPw = request.getParameter("loginPw");
			
			String loginPwConfirm = request.getParameter("loginPwConfirm");
			
			if (!loginPw.equals(loginPwConfirm)) {
//				response.getWriter().append("비밀번호가 일치하지 않습니다");
				response.getWriter()
				.append(String.format("<script>alert('비밀번호가 일치하지 않습니다'); location.replace('../member/join');</script>"));
	
			}
			
			if (loginId.length() == 0 || loginPw.length() == 0 || name.length() == 0) {
//				response.getWriter().append("아이디 또는 비밀번호 또는 이름에 공백으로 입력할 수 없습니다");
				response.getWriter()
				.append(String.format("<script>alert('아이디 또는 비밀번호 또는 이름에 공백으로 입력할 수 없습니다'); location.replace('../member/join');</script>"));
				
			}
			
			
			SecSql sql = new SecSql();
			sql = SecSql.from("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE loginID = ?;", loginId);
			
			Map<String, Object> selectRow = DBUtil.selectRow(conn, sql);
			
			if (selectRow.equals(null)) {
//				response.getWriter().append("이미 있는 아이디 입니다");
				response.getWriter()
				.append(String.format("<script>alert('이미 있는 아이디 입니다'); location.replace('../member/join');</script>"));
				
				
				
			}
			
			
			
			
			
			sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);

			int id = DBUtil.insert(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 회원이 가입되었습니다.'); location.replace('../home/main');</script>", id));

		} catch (SQLException e) {
			System.out.println("에러 : " + e);
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