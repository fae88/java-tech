package thread.concurrent;

import java.util.concurrent.Callable;

/**
 * 查询数据统一任务模型
 */
public abstract class Task  extends BaseTask implements Callable<Object>{

    @Override
    public Object call() {
        long beginTime = System.currentTimeMillis();

        preExec();

        Object object = doQuery();

        postExec();

        System.out.println(String.format("查询耗时：%s, 返回结果：%s", (System.currentTimeMillis() - beginTime)/1000, object.toString()));

        return object;

    }

    public abstract Object doQuery();

}
