/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.zhangmz.pickles.modules.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhangmz.pickles.modules.test.log.LogbackListAppender;

public class ThreadsTest {

	@Test
	public void gracefulShutdown() throws InterruptedException {

		Logger logger = LoggerFactory.getLogger("test");
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger("test");

		// time enough to shutdown
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Runnable task = new Task(logger, 500, 0);
		pool.execute(task);
		Threads.gracefulShutdown(pool, 1000, TimeUnit.MILLISECONDS);
		Assert.assertTrue(pool.isTerminated());
		Assert.assertNull(appender.getFirstLog());

		// time not enough to shutdown,call shutdownNow
		appender.clearLogs();
		pool = Executors.newSingleThreadExecutor();
		task = new Task(logger, 1000, 0);
		pool.execute(task);
		Threads.gracefulShutdown(pool, 500, TimeUnit.MILLISECONDS);
		Assert.assertTrue(pool.isTerminated());
		Assert.assertEquals("InterruptedException", appender.getFirstLog().getMessage());

		// self thread interrupt while calling gracefulShutdown
		appender.clearLogs();

		final ExecutorService self = Executors.newSingleThreadExecutor();
		task = new Task(logger, 100000, 0);
		self.execute(task);

		final CountDownLatch lock = new CountDownLatch(1);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				lock.countDown();
				Threads.gracefulShutdown(self, 200000, TimeUnit.MILLISECONDS);
			}
		});
		thread.start();
		lock.await();
		thread.interrupt();
		Threads.sleep(500);
		Assert.assertEquals("InterruptedException", appender.getFirstLog().getMessage());
	}

	static class Task implements Runnable {
		private final Logger logger;

		private int runTime = 0;

		private final int sleepTime;

		Task(Logger logger, int sleepTime, int runTime) {
			this.logger = logger;
			this.sleepTime = sleepTime;
			this.runTime = runTime;
		}

		@Override
		public void run() {
			System.out.println("start task");
			if (runTime > 0) {
				long start = System.currentTimeMillis();
				while ((System.currentTimeMillis() - start) < runTime) {
				}
			}

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				logger.warn("InterruptedException");
			}
		}
	}
}
