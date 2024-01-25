package com.KoreaIT.java.Jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.KoreaIT.java.Jsp_AM.config.Config;
import com.KoreaIT.java.Jsp_AM.exception.SQLErrorException;
import com.KoreaIT.java.Jsp_AM.util.DBUtil;
import com.KoreaIT.java.Jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {

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
			String name = request.getParameter("name");

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?;", loginId);

			boolean isJoinableLoginId = DBUtil.selectRowIntValue(conn, sql) == 0;
			//DB에 있는 회원들의 로그인 아이디와 login.jsp에서 form 데이터로 받아온 loginId를 비교
			//일치하는게 있다면 중복된 아이디로 회원가입을 원하는 상황
			//boolean 값이 true라면 회원가입을 할수 있도록
			// false 라면 회원가입을 할수 없도록
			//DB에서는 1이 true 0이 false 

			if (isJoinableLoginId == false) {
				response.getWriter().append(String.format(
						"<script>alert('%s는 이미 사용중입니다'); location.replace('../member/join');</script>", loginId));
				return;
			}

			sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);

			int id = DBUtil.insert(conn, sql);

			response.getWriter().append(String.format(
					"<script>alert('%s님, 회원가입이 완료되었습니다.'); location.replace('../article/list');</script>", name));

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