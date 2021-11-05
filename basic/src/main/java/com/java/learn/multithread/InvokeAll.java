package com.java.learn.multithread;

import io.netty.util.concurrent.DefaultThreadFactory;

import javax.xml.bind.DatatypeConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hzliuzhujie
 * @date 2021-10-20
 **/
public class InvokeAll {
    private static ExecutorService exec =
            new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5),
                    new DefaultThreadFactory("url-sim-pool"), new ThreadPoolExecutor.CallerRunsPolicy());
    static class C1 implements Callable<String> {
        private List<String> test;

        public C1(List<String> test) {
            this.test = test;
        }

        public void add(int i) {
            this.test.add("AA" + i);
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "::正在执行计算");
            Thread.sleep(1);
            for (int i = 0; i < 100; i++) {
                add(i);
                Thread.sleep(1);
            }
            return "AA";
        }
    }

    static class C2 implements Callable<String> {
        private List<String> test;

        public C2(List<String> test) {
            this.test = test;
        }

        public void add(int i) {
            this.test.add("BB" + i);
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "::正在执行计算");
            for (int i = 0; i < 100; i++) {
                add(i);
            }
            Thread.sleep(3000);
            return "BB";
        }
    }

    static class C3 implements Callable<String> {
        private List<String> test;

        public C3(List<String> test) {
            this.test = test;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "::正在执行计算");
            for (String s : test) {
                System.out.println(test.size());
                System.out.println(s);
            }
            return "CC";
        }
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        InetAddress vipAddress = InetAddress.getByAddress(DatatypeConverter.parseHexBinary("710af2fb"));
        System.out.println(vipAddress);
        System.out.println(Integer.parseInt("a",16));

        List<String> test =  new ArrayList<>() ;
        // CopyOnWriteArrayList

        List<Callable<String>> list = new ArrayList<Callable<String>>();
        list.add(new C3(test));
        list.add(new C2(test));
        list.add(new C1(test));
        long start = System.currentTimeMillis();
        exec.invokeAll(list, 2000, TimeUnit.MILLISECONDS); // 阻塞方法，当所有任务执行完毕，中断或超时时返回。
        long end = System.currentTimeMillis();
        System.out.println("res:" + test.size() + " " + (end - start));

        /*       for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                System.out.println("异常");
            } catch (CancellationException e) {
                System.out.println("超时");
            }
        }*/

        exec.shutdown();
    }

}
