package com.zookeeper.lock;

import cn.hutool.core.date.DateUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ZookeeperLock {

    private static final String pathPrefix = "/middleware/zkLock/";
    @Autowired
    private CuratorFramework curatorFramework;

    public void zkLock() throws Exception {
        String now = DateUtil.now();
        //创建ZooKeeper互斥锁组件实例
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, now);
        try {
            //尝试获取分布式锁，最大尝试时间10s
            if (mutex.acquire(10L, TimeUnit.SECONDS)) {
                //todo 处理核心逻辑
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //释放
            mutex.release();
        }

    }

}
