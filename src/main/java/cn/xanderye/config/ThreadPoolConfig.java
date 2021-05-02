package cn.xanderye.config;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author XanderYe
 * @description:
 * @date 2021/5/2 9:50
 */
public class ThreadPoolConfig {
    private static final ThreadPoolConfig THREAD_POOL_CONFIG = new ThreadPoolConfig();

    private ScheduledExecutorService scheduledExecutorService;

    public static ThreadPoolConfig getInstance() {
        return THREAD_POOL_CONFIG;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }
}
