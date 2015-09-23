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

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		if (request.getParameter("id") == null || request.getParameter("id").trim().length() == 0) {
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		Book book = ((BookDaoJpa) JpaMap.get(JpaMap.Type.BOOK)).find(id);

		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Lab 4</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<pre>");
		out.println("<h5><a href=\"../\">Список книг</a></h5>");
		out.println(String.format("<h3>%s</h3>", book.getName()));
		out.println(String.format("<p><b>Автор</b>: %s</p>", book.getAuthor()));
		out.println(String.format("<p><b>Жанр</b>: %s</p>", book.getGenre()));
		out.println(String.format("<p><b>Издательство</b>: %s</p>", book.getPublishing()));
		out.println(String.format("<p><b>Год издания</b>: %s</p>", book.getYearOfPublishing()));
		out.println("</pre>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
