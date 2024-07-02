package concurrent;

public class VolatileTest  extends Thread{
      //아래처럼 변수를 선언해버리면 cpu가 flag값을 캐시메모리에서 가져오므로 22번쨰 줄에서 flag값을 변경해도 적용되지 않아 무한루프가 돈다  
//    boolean flag=true;
   volatile boolean flag=true;

    @Override
    public void run() {
        int i=0;
        while (flag){
            i++;
        }
        System.out.println(this.getName()+"무한 루프 종료");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest thread = new VolatileTest();

        thread.start();
        Thread.sleep(1000);
        thread.flag=false;
        System.out.println("MyVolatileTestThread.main");

    }

}


