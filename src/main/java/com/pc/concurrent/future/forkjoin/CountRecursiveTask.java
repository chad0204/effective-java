package com.pc.concurrent.future.forkjoin;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 1 + 2 + 3 + ... + n
 */
public class CountRecursiveTask extends RecursiveTask<Long> {

    private Long threshold = 100000L;//每个子任务处理的数字个数

    private Long startNum;
    private Long endNum;

    //父线程是否参与任务处理
    private Boolean self;

    public CountRecursiveTask(Long startNum, Long endNum, Boolean self) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.self = self;
    }

    @Override
    protected Long compute() {
        if (endNum - startNum > threshold) {
            if (self) {
                //写法1:  当前线程会将将n-1个任务分出去，自己留一个任务 耗时：30221ms.
                return ForkJoinTask.invokeAll(createSubTasks())
                        .stream().mapToLong(CountRecursiveTask::join).sum();
            } else {
                //写法2: 当前线程将任务全部分出去，自己成为监工 耗时：2946ms.
                List<CountRecursiveTask> subTasks = createSubTasks();
                subTasks.forEach(CountRecursiveTask::fork);
                return subTasks.stream().mapToLong(CountRecursiveTask::join).sum();
            }
        } else {
            return processing(startNum, endNum);
        }
    }

    private List<CountRecursiveTask> createSubTasks() {
        List<CountRecursiveTask> subTasks = new ArrayList<>();
        subTasks.add(new CountRecursiveTask(startNum, (endNum + startNum)/2, self));
        subTasks.add(new CountRecursiveTask((endNum + startNum)/2 + 1, endNum, self));
        return subTasks;
    }

    private Long processing(Long startNum, Long endNum) {
        long sum = 0L;
        for (; startNum <= endNum; startNum++) {
            sum += startNum;
        }
//        System.out.println(Thread.currentThread().getName() + ": " + sum);
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        StopWatch sw = StopWatch.createStarted();
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(8);
        CountRecursiveTask countRecursiveTask1 = new CountRecursiveTask(1L, 1000000000L, false);
        ForkJoinTask<Long> result = forkJoinPool1.submit(countRecursiveTask1);
        System.out.println(result.get());
        sw.stop();
        System.out.printf("任务1耗时：%d%s.\n", sw.getTime(), "ms");


        sw.reset();
        sw.start();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(2);
        CountRecursiveTask countRecursiveTask2 = new CountRecursiveTask(1L, 1000000000L, true);
        ForkJoinTask<Long> submit = forkJoinPool2.submit(countRecursiveTask2);
        System.out.println(submit.get());
        System.out.printf("任务2耗时：%dms.\n", sw.getTime());


//        ForkJoinPool forkJoinPool2 = new ForkJoinPool(2);
//        Long invoke = forkJoinPool2.invoke(countRecursiveTask);
//        System.out.println(invoke);

    }

}
