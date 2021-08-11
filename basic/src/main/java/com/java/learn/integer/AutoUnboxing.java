package com.java.learn.integer;

import java.lang.reflect.Field;

/**
 * 自动装箱拆箱
 *
 * @author hzliuzhujie
 * @date 2021-07-06
 **/
public class AutoUnboxing {

    public static void main(String[] args) throws Exception {
        // java se5之前手动装箱
        Integer a = new Integer(3);

        // 将3自动装箱成Integer类型，通过 Integer.valueOf(3);
        Integer b = 3;

        int c = 3;

        // 拆箱，通过Integer.intValue();
        int d = a;

        Integer e = 3;

        // false 两个引用没有引用同一对象
        System.out.println(a == b);

        // true a自动拆箱成int类型再和c比较
        System.out.println(a == c);

        // true
        System.out.println(d == b);

        // true 适用于整数值区间-128 至 +127。自动装箱。使用构造函数创建对象不适用。整型对象通过使用相同的对象引用实现了缓存和重用。
        System.out.println(e == b);

        Integer f = 300;
        Integer g = 300;

        // false
        System.out.println(f == g);

        // true
        System.out.println(f.equals(g));


        Integer x = 1, y = 2;
        System.out.println("交换前：x = " + x + ", y = " + y);
        swap(x, y);
        System.out.println("交换后：x = " + x + ", y = " + y);
    }

    /**
     * 错误的，原因是integer缓存
     * @param i
     * @param j
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void swap2(Integer i, Integer j) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        int temp = i.intValue();
        // 此处 我们使用 j.intValue 返回结果是个 int 类型数据
        // 而 value.set()方法需要的是一个 Object 对象 此处就涉及到了装箱
        // 所以 i 值的实际变化过程为：i = Integer.valueOf(j.intValue()).intValue()
        value.set(i, j.intValue());
        // 同理 j 值得实际变化过程为：j = Integer.valueOf(temp).intValue()
        // 因为 valueOf() 要从缓存获取值 也就是此时需要根据 temp 的下标来获取值
        // 可是在上一步中 i 的值已经被自动装箱之后变成了 2
        // 所以此处会把 j 的值设置成 2
        value.set(j, temp);
    }

    // https://blog.csdn.net/sinat_30160727/article/details/83790554
    public static void swap(Integer i, Integer j) throws Exception {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        //这一步很重要，决定是不是从缓存中取，一定不能Integer val = value1.intValue();这么写
        Integer temp = new Integer(i.intValue());
        value.set(i, j);
        value.set(j, temp);
    }
}
