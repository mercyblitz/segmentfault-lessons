// PosixThread_Sync.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include <pthread.h>

int counter = 0;

// 定义互斥对象
pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;

// 定义条件变量
pthread_cond_t condition_var = PTHREAD_COND_INITIALIZER;

void* addCounter(void* ptr);

void* minusCounter(void* ptr);

int main()
{
	pthread_t t1;
	pthread_t t2;

	pthread_create(&t1, NULL, addCounter, NULL);
	pthread_create(&t2, NULL, minusCounter, NULL);
	

	pthread_join(t1, NULL);
	pthread_join(t2, NULL);

    std::cout << "Counter : " << counter << std::endl;

	return EXIT_SUCCESS;
}

void* minusCounter(void* ptr) {
	for (int i=0;i<100;i++) {

		// lock 加锁
		pthread_mutex_lock(&mutex1);
		std::cout << "minusCounter - Before Counter : " << counter << std::endl;

		if (counter < 9 && counter > 1) { // 消费数据，唤起生产者线程
			counter--;
			pthread_cond_signal(&condition_var);
		}

		if (counter < 1) { // 当数据不足时，阻塞当前消费者线程
			pthread_cond_wait(&condition_var, &mutex1);
		}


		std::cout << "minusCounter - After Counter : " << counter << std::endl;
		// unlock 解锁
		pthread_mutex_unlock(&mutex1);
	}
	return NULL;
}

void* addCounter(void* ptr) {
	for (int i = 0; i < 100; i++) {
		// lock 加锁
		pthread_mutex_lock(&mutex1);
		std::cout << "addCounter - Before Counter : " << counter << std::endl;

		if (counter < 9) {  // 当数据不到阈值时，唤起当前消费者线程
			counter++;
			pthread_cond_signal(&condition_var);
		}

		if (counter > 9) {  // 当数据达到阈值时，阻塞当前生产者线程
			pthread_cond_wait(&condition_var, &mutex1);
		}

		std::cout << "addCounter - After Counter : " << counter << std::endl;
		// unlock 解锁
		pthread_mutex_unlock(&mutex1);
	}
	return NULL;
}

