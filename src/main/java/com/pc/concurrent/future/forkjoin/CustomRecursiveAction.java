package com.pc.concurrent.future.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * 小写转大写的任务
 */
public class CustomRecursiveAction extends RecursiveAction {

    private String workload;//需要处理的字符串

    private int THRESHOLD = 8;//每个子任务处理的字符串个数

    public CustomRecursiveAction(String workLoad) {
        this.workload = workLoad;
    }


    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) {
            List<CustomRecursiveAction> subTasks = createSubTasks();
            ForkJoinTask.invokeAll(subTasks);
        } else {
            processing(workload);
        }
    }

    private List<CustomRecursiveAction> createSubTasks() {
        List<CustomRecursiveAction> subTasks  = new ArrayList<>();
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
        subTasks.add(new CustomRecursiveAction(partOne));
        subTasks.add(new CustomRecursiveAction(partTwo));
        return subTasks;
    }

    private void processing(String workLoad) {
        String result = workLoad.toUpperCase();
        System.out.println(Thread.currentThread().getName() + ": " + result);
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
//        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();

        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction(str);


        forkJoinPool.invoke(customRecursiveAction);

//        forkJoinPool.submit(customRecursiveAction);
//        customRecursiveAction.join();



    }

    static String str = "abcfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfsfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfsfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfsfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfsfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfsfdasfdsafdsafdsafdsafdsafdsafgfdsgfdsgfdgfs";

}
