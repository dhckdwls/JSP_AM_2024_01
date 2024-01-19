package com.KoreaIT.java.Jsp_AM;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/printDan")
public class HomeMainServlet3 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		//한글이 깨져서 인터넷에서 찾아봄
		
		response.getWriter().append("==8단==<br>8 * 1 = 8<br>8 * 2 = 16<br>8 * 3 = 24<br>8 * 4 = 32<br>8 * 5 = 40<br>8 * 6 = 48<br>8 * 7 = 56<br>8 * 8 = 64<br>8 * 9 = 72<br>");
	
	
		
		
	
	}
	

}
