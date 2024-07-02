package concurrent;

public class VolatileTest  extends Thread{
      //아래처럼 변수를 선언해버리면 cpu가 flag값을 캐시메모리에서 가져오므로 22번쨰 줄에서 flag값을 변경해도 적용되지 않아 무한루프가 돈다  
//boolean flag=true;

   //volatile는 읽기,쓰기에대한 동기화를 보장
   //하나의 변수를 여러스레드가 참조할 때 사용하는 키워드
   //volatile가 붙어있는 변수에 스레드가 접근하면 cpu cache에 접근하는것이 아니라 메인메모리에서 작업을 수행함
      
   
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


