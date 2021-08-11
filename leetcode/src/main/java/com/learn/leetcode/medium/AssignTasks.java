package com.learn.leetcode.medium;

import java.util.*;

/**
 * 5774. 使用服务器处理任务
 *
 * @author hzliuzhujie
 * @date 2021-05-30
 **/
public class AssignTasks {

    public int[] assignTasks2(int[] servers, int[] tasks) {
        //定义优先队列sq1（存入long[]{服务器标号 ， 权重}），并重写Comparator（权重从小到大，标号从小到大）
        PriorityQueue<long[]> sq1 = new PriorityQueue<long[]>(new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                // 权重相同
                if (a[1] == b[1]) {
                    return (int) (a[0] - b[0]);
                }
                // 权重不同
                return (int) (a[1] - b[1]);
            }
        });
        //定义优先队列sq2（存入long[]{服务器标号 ， 权重 ， 服务器完成工作时间}），并重写Comparator（完成工作时间从小到大）
        PriorityQueue<long[]> sq2 = new PriorityQueue<long[]>(new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                return (int) (a[2] - b[2]);
            }
        });
        for (int i = 0; i < servers.length; i++) {
            sq1.offer(new long[]{i, servers[i]});
        }
        int n = tasks.length;
        int[] res = new int[n];
        int r = 0;
        Deque<Integer> lst = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //检测sq2中是否有 完成时间小于<=当前时间的服务器，若有，则从sq2中取出该服务器并加入sq1中
            while (!sq2.isEmpty() && sq2.peek()[2] <= i) {
                long[] tas = sq2.poll();
                sq1.offer(new long[]{tas[0], tas[1]});
            }
            //任务从尾部进入队列
            lst.offerLast(tasks[i]);
            //当sq1和lst都不为空时，说明有任务可以加到服务器中，此时从lst头部取出任务，并从sq1中取出服务器，两者结合后添加到sq2中
            //题目原话：如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。（很重要）
            while (!sq1.isEmpty() && !lst.isEmpty()) {
                long[] ser = sq1.poll();
                res[r++] = (int) ser[0];
                sq2.offer(new long[]{ser[0], ser[1], i + lst.pollFirst()});
            }
        }
        long t = n;
        //若是lst依旧不为空，说明服务器资源已满，需要等待
        while (!lst.isEmpty()) {
            //因此我们取出完成时间最小的所有服务器（多个服务器可能会同时解放）
            if (!sq2.isEmpty()) {
                //需把时间t置为服务器解放时间（很多超时是因为t逐一累加）
                t = sq2.peek()[2];
                while (!sq2.isEmpty() && sq2.peek()[2] == t) {
                    long[] tas = sq2.poll();
                    sq1.offer(new long[]{tas[0], tas[1]});
                }
            }
            //仿照上面把任务添加进空闲服务器
            while (!sq1.isEmpty() && !lst.isEmpty()) {
                long[] ser = sq1.poll();
                res[r++] = (int) ser[0];
                sq2.offer(new long[]{ser[0], ser[1], t + lst.pollFirst()});
            }
        }
        return res;
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        System.out.println(servers[36]);
        System.out.println(servers[56]);
        System.out.println(tasks[147]);
        System.out.println(tasks[148]);
        int[] result = new int[tasks.length];
        // 权重排序
        List<Tuple> list = new ArrayList<>();
        for (int j = 0; j < servers.length; j++) {
            list.add(new Tuple(servers[j], j));
        }
        list.sort((o1, o2) -> {
            if (o1.key != o2.key) {
                return o1.key - o2.key;
            } else {
                return o1.index - o2.index;
            }
        });

        // 完成时间排序
        List<Tuple> timeList = new ArrayList<>();
        for (int j = 0; j < servers.length; j++) {
            list.add(new Tuple(servers[j], j));
        }
        list.sort(Comparator.comparingInt(o -> o.time));


        int time = 0;
        for (int j = 0; j < tasks.length; j++) {
            boolean isbreak = false;
            while (!isbreak) {
                for (int i = 0; i < list.size(); i++) {
                    if (time >= list.get(i).time) {
                        if (list.get(i).index == 36) {
                            System.out.println("36:" + tasks[j] + " " + (tasks[j] + time));
                        }
                        if (list.get(i).index == 56) {
                            System.out.println("56:" + tasks[j] + " " + (tasks[j] + time));
                        }

                        list.get(i).time = tasks[j] + time;
                        result[j] = list.get(i).index;
                        isbreak = true;
                        break;
                    }
                }
                time++;
            }


        }
        return result;
    }

    public class Tuple {

        // 权重
        public int key;

        // 下标
        public int index;

        // 处理时间
        public int time = 0;

        public Tuple(int key, int index) {
            this.key = key;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "key=" + key +
                    ", index=" + index +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) {
   /*     // [2,2,0,2,1,2]
        System.out.println(Arrays.toString(new AssignTasks().assignTasks(
                new int[]{3, 3, 2},
                new int[]{1, 2, 3, 2, 1, 2})));

        // [1,4,1,4,1,3,2]
        System.out.println(Arrays.toString(new AssignTasks().assignTasks(
                new int[]{5, 1, 4, 3, 2},
                new int[]{2, 1, 2, 4, 5, 2, 1})));
*/
        // [1,4,1,4,1,3,2]
        System.out.println(Arrays.toString(new AssignTasks().assignTasks(
                new int[]{338, 890, 301, 532, 284, 930, 426, 616, 919, 267, 571, 140, 716, 859, 980, 469, 628, 490, 195, 664, 925, 652, 503, 301, 917, 563, 82, 947, 910, 451, 366, 190, 253, 516, 503, 721, 889, 964, 506, 914, 986, 718, 520, 328, 341, 765, 922, 139, 911, 578, 86, 435, 824, 321, 942, 215, 147, 985, 619, 865},
                new int[]{773, 537, 46, 317, 233, 34, 712, 625, 336, 221, 145, 227, 194, 693, 981, 861, 317, 308, 400, 2, 391, 12, 626, 265, 710, 792, 620, 416, 267, 611, 875, 361, 494, 128, 133, 157, 638, 632, 2, 158, 428, 284, 847, 431, 94, 782, 888, 44, 117, 489, 222, 932, 494, 948, 405, 44, 185, 587, 738, 164, 356, 783, 276, 547, 605, 609, 930, 847, 39, 579, 768, 59, 976, 790, 612, 196, 865, 149, 975, 28, 653, 417, 539, 131, 220, 325, 252, 160, 761, 226, 629, 317, 185, 42, 713, 142, 130, 695, 944, 40, 700, 122, 992, 33, 30, 136, 773, 124, 203, 384, 910, 214, 536, 767, 859, 478, 96, 172, 398, 146, 713, 80, 235, 176, 876, 983, 363, 646, 166, 928, 232, 699, 504, 612, 918, 406, 42, 931, 647, 795, 139, 933, 746, 51, 63, 359, 303, 752, 799, 836, 50, 854, 161, 87, 346, 507, 468, 651, 32, 717, 279, 139, 851, 178, 934, 233, 876, 797, 701, 505, 878, 731, 468, 884, 87, 921, 782, 788, 803, 994, 67, 905, 309, 2, 85, 200, 368, 672, 995, 128, 734, 157, 157, 814, 327, 31, 556, 394, 47, 53, 755, 721, 159, 843})));


    }
}
