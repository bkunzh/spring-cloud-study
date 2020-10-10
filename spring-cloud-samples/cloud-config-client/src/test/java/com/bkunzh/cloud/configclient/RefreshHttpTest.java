package com.bkunzh.cloud.configclient;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bkunzh
 * @date 2020/10/10
 */
@Slf4j
public class RefreshHttpTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(36);

    @Test
    public void refreshManyTime() throws Exception {
        final int N = 200;
        CountDownLatch countDownLatch = new CountDownLatch(N);
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(600, TimeUnit.SECONDS)
                .build();
        log.info("timeout={}, {}", client.readTimeoutMillis(), client.connectTimeoutMillis());
        RequestBody body = RequestBody.create(null, "");
        long time = System.currentTimeMillis();
        for (int i=0; i<N; ++i) {
            executorService.execute(() -> {
                try {
                    Request request = new Request.Builder()
                            .url("http://localhost:3303/actuator/refresh")
                            .method("POST", body)
                            .build();

                    Response response = client.newCall(request).execute();
                    log.info(response.body().string());
                } catch (Exception e) {
                    log.error("error ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("结束, 花费时间={}", System.currentTimeMillis() - time);
    }

    @Test
    public void getServerConfigManyTime() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("http://testconf.bkzh.com").path("/master/config-single-client-dev.yml").build().toUri();
        final int N = 200;
        CountDownLatch countDownLatch = new CountDownLatch(N);
        long time = System.currentTimeMillis();
        for (int i=0; i<N; ++i) {
            executorService.execute(() -> {
                try {
                    String rs = restTemplate.getForObject(uri, String.class);
                    log.info(rs);
                }  catch (Exception e) {
                    log.error("error ", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        log.info("结束, 花费时间={}", System.currentTimeMillis() - time);
    }
}
