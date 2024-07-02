package com.oauthtest.demo;

import com.oauthtest.demo.dto.ConcurrentBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ThreadLocalController {
  
    private final ThreadLocal<ConcurrentBean> threadLocal;//멀티스레딩 환경에서 공유될 객체(ConcurrentBean)를 ThreadLocal로 감쌈
  
    @GetMapping("/threadLocal/{value}/{name}")
    public void tl(@PathVariable String value,@PathVariable String name) throws InterruptedException {
        ConcurrentBean concurrentBean = threadLocal.get();
        if (concurrentBean == null) {
            concurrentBean = new ConcurrentBean();
            threadLocal.set(concurrentBean);
        }
        log.info(name+"번째 스레드 스레드로컬에 넣을 값 : {}", value);
        // 스레드 내에서 공유할 데이터를 설정
        concurrentBean.setS(value);

        //시간차를 주어야 공유객체에 여러스레드가 동시에 접근함
        Thread.sleep(2000);
      
       // 스레드 내에서 데이터에 접근
        log.info(name+"번째 스레드 스레드로컬 값 : {}", concurrentBean.getS());

    }

  //5개의 스레드 생성후 실행 
    @GetMapping("/thread")
    public void createThread() {
        for (int i = 0; i < 5; i++) {
            final int index = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    request((Math.random() * 100) + "",index);
                }
            });

            thread.start();
        }
    }

    static void request(String s,int i) {
        try {
            URL url = new URL("http://localhost:8080/threadLocal/" + s+"/"+i);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/*
[결과값]
2024-07-01T20:17:34.753+09:00  INFO 18520 --- [demo] [nio-8080-exec-4] c.oauthtest.demo.ThreadLocalController   : 1번째 스레드 스레드로컬에 넣을 값 : 75.70031404325383
2024-07-01T20:17:34.753+09:00  INFO 18520 --- [demo] [nio-8080-exec-6] c.oauthtest.demo.ThreadLocalController   : 0번째 스레드 스레드로컬에 넣을 값 : 26.7265774567083
2024-07-01T20:17:34.753+09:00  INFO 18520 --- [demo] [nio-8080-exec-5] c.oauthtest.demo.ThreadLocalController   : 3번째 스레드 스레드로컬에 넣을 값 : 81.48481231089781
2024-07-01T20:17:34.753+09:00  INFO 18520 --- [demo] [nio-8080-exec-7] c.oauthtest.demo.ThreadLocalController   : 2번째 스레드 스레드로컬에 넣을 값 : 52.446000547351446
2024-07-01T20:17:34.753+09:00  INFO 18520 --- [demo] [nio-8080-exec-3] c.oauthtest.demo.ThreadLocalController   : 4번째 스레드 스레드로컬에 넣을 값 : 7.756853369087413
2024-07-01T20:17:36.761+09:00  INFO 18520 --- [demo] [nio-8080-exec-4] c.oauthtest.demo.ThreadLocalController   : 1번째 스레드 스레드로컬 값 : 75.70031404325383
2024-07-01T20:17:36.761+09:00  INFO 18520 --- [demo] [nio-8080-exec-6] c.oauthtest.demo.ThreadLocalController   : 0번째 스레드 스레드로컬 값 : 26.7265774567083
2024-07-01T20:17:36.768+09:00  INFO 18520 --- [demo] [nio-8080-exec-7] c.oauthtest.demo.ThreadLocalController   : 2번째 스레드 스레드로컬 값 : 52.446000547351446
2024-07-01T20:17:36.768+09:00  INFO 18520 --- [demo] [nio-8080-exec-3] c.oauthtest.demo.ThreadLocalController   : 4번째 스레드 스레드로컬 값 : 7.756853369087413
2024-07-01T20:17:36.768+09:00  INFO 18520 --- [demo] [nio-8080-exec-5] c.oauthtest.demo.ThreadLocalController   : 3번째 스레드 스레드로컬 값 : 81.48481231089781

스레드 값이 서로 중복되지 않고 고유하게 유지됨
*/
