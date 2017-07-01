package com.segmentfault.lesson5.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class JDBCTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917571910801949833L;

	private DataSource dataSource;

	public void init(ServletConfig servletConfig) throws ServletException {

		try {
			Context context = new InitialContext();
			Context evnContext = (Context) context.lookup("java:comp/env");
			dataSource = (DataSource) evnContext.lookup("jdbc/TestDB");

			String bean = (String) evnContext.lookup("Bean");
			
			System.out.println(bean);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Writer writer = response.getWriter();

		response.setContentType("text/html;charset=UTF-8");

		try {

			Connection connection = dataSource.getConnection();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SHOW DATABASES;");

			while (resultSet.next()) {

				String dataname = resultSet.getString(1);
				writer.write(dataname);
				writer.write("<br />");
				writer.flush();
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}

}
