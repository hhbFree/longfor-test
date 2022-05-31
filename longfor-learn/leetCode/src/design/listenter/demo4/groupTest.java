package design.listenter.demo4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: <b>描述: </b>这个类是控制一组{@link Future} 的帮助类，每一个{@link Future}
 * 可以代表一个线程，它可以同时等待多个线程处理完毕后，在将每个线程的处理结果收集， 又可以等待某一个{@link Future}得到结果后就进行分析，
 * 最后进行综合全部{@link Future}的结果后得到最终值， 可以想象成是一种分布式计算
 * <p>
 * <b>功能: </b>实现当前主线程与一组{@link Future}的同步或异步处理，主线程可以将计算任务分布给多个线程，每一个线程由
 * {@link Future}来传递结果,{@link FutureGroup}可以选择在全部的{@link Future}
 * 都运行结束后再统一处理结果，还是某一个{@link Future}运行结束后就处理结果
 * <p>
 * <b>用法: </b>
 *
 * <pre>
 *     以分布式计算为例，通过将任务划分成多份，交由不同的线程并行处理，并将多个结果进行组合，最终得出综合结果，使用此方法经本机测试，耗时仅为常规计算的30-40%
 *
 *
 *
 * @Author: hbHao
 * @Date: 2020/8/12 10:58
 */
public class groupTest {


    public static void main(String[] args) throws Exception {
        int cupNumber = Runtime.getRuntime().availableProcessors(); // 获得cpu数量
        int length = Integer.MAX_VALUE / 2; // 一半的Integer最大值的等差数列进行求和(当然可以使用公式进行快速计算，但此处是为了演示效率，因此没
        int average = (length + cupNumber - 1) / cupNumber; // 每一份任务的平均需要处理的数量
        long sum = 0; // 计算结果
        long startTime = System.nanoTime(); // 耗时检测
        List<Future<Long>> futures = new ArrayList<Future<Long>>(); // 每一份任务都会有一个Future
        for (int i = 0; i < cupNumber; i++) { // 根据Cpu核心数量，创建对应的计算任务
            int start = i * average;
            Future<Long> futureTemp = new Future<Long>();
            futures.add(futureTemp);
            // 开启计算任务
            TaskThread taskThread = new TaskThread(futureTemp, start, start + average > length ? length : start
                    + average);
            taskThread.start();
        }
        FutureGroup<Future<Long>> futureGroup = new FutureGroup<Future<Long>>(futures); // 将多个任务同时放
        Future<Long> future = null;
        while ((future = futureGroup.awaitAny()) != null) { // 遍历每一个完成的任务，得到任务结果后计算和值
            sum += future.getValue();
        }
        long endTime = System.nanoTime(); // 结束时间
        long distributedTime = endTime - startTime;// 分布式计算耗时
        System.out.println("计算结果:" + sum + "  分布式计算耗时:"
                + TimeUnit.MILLISECONDS.convert(distributedTime, TimeUnit.NANOSECONDS) + "ms.");

        // 以下为传统的for循环方式计算结果
        startTime = System.nanoTime(); // 传统计算的耗时检测开始
        sum = 0;
        for (int i = 0; i < length; i++) { // 计算同样数量的等差数列的值
            sum += i;
        }
        endTime = System.nanoTime(); // 传统计算结束
        long commonTime = endTime - startTime; // 传统计算耗时
        DecimalFormat decimalFormat = new DecimalFormat("00%");
        System.out.println("计算结果:" + sum + "  普通计算耗时:"
                + TimeUnit.MILLISECONDS.convert(commonTime, TimeUnit.NANOSECONDS) + "ms.");
        System.out.println("分布式计算的耗时是传统计算的 "
                + (decimalFormat.format(Double.valueOf(distributedTime) / Double.valueOf(commonTime))));
    }


}
