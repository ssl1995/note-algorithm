package com.ssl.know.java.thread;

/**
 * 手写死锁：两个线程以相反顺序获取两把锁，形成循环等待
 *
 * <pre>
 * 死锁四条件：互斥 + 占有且等待 + 不可剥夺 + 循环等待
 *
 * Thread-a：持有 lockA，等待 lockB ──┐
 *                                    ├── 循环等待，互相死锁
 * Thread-b：持有 lockB，等待 lockA ──┘
 *
 * 排查验证：
 *   1. jps -l 找到进程号
 *   2. jstack <pid> | 输出末尾会打印 "Found one Java-level deadlock"
 *   3. 或 Arthas: thread -b 定位阻塞源
 *
 * 修复思路（破坏循环等待）：两个线程统一按 lockA → lockB 的顺序加锁
 * </pre>
 */
public class BlockedLockTest {

  private static final Object lockA = new Object();
  private static final Object lockB = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread a = new Thread(() -> {
      synchronized (lockA) {
        System.out.println("a 拿到 lockA，尝试获取 lockB...");

        sleep(100); // 停顿保证对方也拿到第一把锁，让死锁必然发生

        synchronized (lockB) {
          System.out.println("a 拿到 lockB（死锁时永远执行不到）");
        }
      }
    }, "thread-a");

    Thread b = new Thread(() -> {
      synchronized (lockB) {
        System.out.println("b 拿到 lockB，尝试获取 lockA...");

        sleep(100);

        synchronized (lockA) {
          System.out.println("b 拿到 lockA（死锁时永远执行不到）");
        }
      }
    }, "thread-b");

    a.start();
    b.start();

    // main 线程等 3 秒后确认死锁发生（a、b 均未结束）
    a.join(3000);
    b.join(3000);
    System.out.println("thread-a 是否存活: " + a.isAlive());
    System.out.println("thread-b 是否存活: " + b.isAlive());
    System.out.println("两个线程互相等待，已死锁。请用 jstack <pid> 验证");
  }

  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
