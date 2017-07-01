package com.segmentfault.lesson5.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContext sc = sce.getServletContext();
		
		ClassLoader classLoader = sc.getClassLoader();

		while (true) {

			System.out.println(classLoader.getClass().getName());

			classLoader = classLoader.getParent();

			if (classLoader == null) {
				break;
			}

		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
