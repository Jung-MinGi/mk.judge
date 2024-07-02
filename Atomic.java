package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/*
Volatile vs Atomic
Volatile는 행위의 타겟에 대한 동기화 즉 어떤 변수를 가지고 핸들링할지를 동기화해주는것
Atomic 행위에 대한 동기화 즉 어떤 단일 변수에 연산을 할때 원자성을 부여
더 복잡한 연산은 synchronized를 사용해야함
 */
public class AtomicTest {
    //volatile를 붙여도 변수값이 정상적으로 누적되지 않음
//        volatile static int publicObj = 0;
    static AtomicInteger publicObj = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        MyThread th1 = new MyThread();
        MyThread th2 = new MyThread();
        MyThread th3 = new MyThread();
        MyThread th4 = new MyThread();
        MyThread th5 = new MyThread();
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        Thread.sleep(2000);
        System.out.println("publicObj = " + publicObj);
    }
}

class MyThread extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {
//            AtomicTest.publicObj += 1;
            AtomicTest.publicObj.getAndAdd(1);
        }
        System.out.println(this.getName() + " 종료");
    }
}
