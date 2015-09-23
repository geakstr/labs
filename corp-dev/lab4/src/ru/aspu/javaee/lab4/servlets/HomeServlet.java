package ru.aspu.javaee.lab4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.aspu.javaee.lab4.datamanager.dao.jpa.BookDaoJpa;
import ru.aspu.javaee.lab4.datamanager.dao.jpa.JpaMap;
import ru.aspu.javaee.lab4.entities.Book;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Lab 4</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<pre>");
		out.println("<h3>Список книг</h3>");
		out.println("<ol>");
		for (Book book : ((BookDaoJpa) JpaMap.get(JpaMap.Type.BOOK)).getAll()) {
			out.println(String.format("<li><a href=\"/lab4/book/?id=%s\">%s</a></li>", book.getId(), book.getName()));
		}
		out.println("</ol>");
		out.println("</pre>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
