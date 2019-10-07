// PosixThread_HelloWorld.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include <pthread.h>

using namespace std;


int main()
{
	pthread_t t1;

	// 申明 POSIX Thread 属性变量
	pthread_attr_t attr;
	// 申请线程栈大小（无符号整型） 512 K
	size_t stack_size = 512 * 1000;

	// 初始化 pthread_attr_t
	pthread_attr_init(&attr);
	// 设置 pthread_attr_t 栈大小 -> 512 K
	pthread_attr_setstacksize(&attr, stack_size);

	// 创建 t1 线程，并且将执行对象指向 void* helloWorld(void* ptr);
	int result = pthread_create(&t1, &attr, helloWorld, NULL);

	pthread_join(t1, NULL);
	// 销毁 pthread_attr_t attr
	pthread_attr_destroy(&attr);
	//线程退出
	pthread_exit(NULL);

	return EXIT_SUCCESS;
}

void* helloWorld(void* ptr) {
	pthread_t t = pthread_self();
	//printf("Thread - Hello World \n");
	cout << "Hello World" << endl;
	return NULL;
}

