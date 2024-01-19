package com.KoreaIT.java.Jsp_AM;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/printDan")
public class printDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//printDan?dan=8&limit=2
		//8단출력 * 2
		//printDan?dan=8&limit=2&color=red
		//색상은 레드로
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String inputedDan = request.getParameter("dan");
		String inputedLimit = request.getParameter("limit");
		String inputedColor = request.getParameter("color");
		

		if (inputedDan == null) {
			inputedDan = "1";
		}
		if (inputedLimit == null) {
			inputedLimit = "1";
		}
		if(inputedColor == null) {
			inputedColor = "black";
		}
		
		String color = inputedColor;
//		<div style="color:red;">asd</div>
//		<div style=\"color:red;\">asd</div>
		out.print("<html>");
		out.print("<body>");
		out.print("<div style=\"color:"+color+";\">");
		
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);
		
		int startNumber = 1;
		while (startNumber <= limit) {
			response.getWriter().append(String.format("==%d단==<br>", dan));
			for (int i = 1; i <= 9; i++) {
				response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
			}
			startNumber++;
		}
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");		
		
	}

}