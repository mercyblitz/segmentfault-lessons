package com.segmentfault.lesson5;

public class Main {

	public static void main(String[] args) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		while (true) {

			System.out.println(classLoader.getClass().getName());

			classLoader = classLoader.getParent();

			if (classLoader == null) {
				break;
			}

		}

		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

		System.out.println(systemClassLoader.getClass().getName());

	}

}
