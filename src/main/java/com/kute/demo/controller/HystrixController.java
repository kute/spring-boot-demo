package com.kute.demo.controller;

import com.google.common.collect.Lists;
import com.kute.demo.hystrix.BaseHystrixCommand;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by kute on 2017/12/10.
 */
@Api(value = "Hystrix测试controller")
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String url = "https://api.cdnjs.com/libraries/1140";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 同步调用
     * @return
     * @throws Exception
     */
    @GetMapping("/")
    public String get() throws Exception{
        BaseHystrixCommand command = new BaseHystrixCommand(restTemplate, url, "mykey", "GET");
        String result = command.execute();
        return result;
    }

    /**
     * 异步调用
     * @return
     * @throws Exception
     */
    @GetMapping("/hystrix1")
    public String get1() throws Exception{
        BaseHystrixCommand command = new BaseHystrixCommand(restTemplate, url, "mykey", "GET");
        Future<String > future = command.queue();
        String result = future.get(10, TimeUnit.MILLISECONDS);
        return result;
    }

    /**
     * 注册异步事件回调执行 RxJava
     * @return
     * @throws Exception
     */
    @GetMapping("/hystrix2")
    public String get2() throws Exception{
        BaseHystrixCommand command = new BaseHystrixCommand(restTemplate, url, "mykey", "GET");

        Observable<String > observable = command.observe();

        String result = null;

        //注册结果回调事件
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                //执行结果处理,result 为HelloWorldCommand返回的结果
                //用户对结果做二次处理.
            }
        });

        //注册完整执行生命周期事件
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String result) {
                // i get result
            }
        });

        return command.execute();
    }

    @GetMapping("/hystrix3")
    public String get3() throws Exception{
        Observable<String > observable1 = new BaseHystrixCommand(restTemplate, url, "mykey", "GET").observe();
        Observable<String > observable2 = new BaseHystrixCommand(restTemplate, url, "mykey", "GET").observe();
        Observable<String > observable3 = new BaseHystrixCommand(restTemplate, url, "mykey", "GET").observe();

        List<Observable<String>> observableList = Lists.newArrayList(observable1, observable2, observable3);

        Observable.merge(observableList).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {

            }
        });

        return "done";
    }

}
