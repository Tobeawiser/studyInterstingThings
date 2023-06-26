package com.example.lovestory.distributelock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DistributeLock {

    /**
     * 数据库分布式锁
     * 一定要添加事务@Transactional，否则for update执行完毕后会自动释放锁
     */
//    @RequestMapping("dbLock")
//    @Transactional(rollbackFor = Exception.class)
//    public void dbLock() throws Exception {
//        log.info("我进入了dbLock方法！");
//        // 调用for update语句
//        DistributeLock distributeLock = distributeLockMapper.selectDistributeLock("order");
//        // 如果不为空则进入锁，为空代表已经有其它请求占住了锁
//        if (distributeLock == null) {
//            throw new Exception("分布式锁找不到");
//        }
//        log.info("我进入了锁！");
//        try {
//            // 模拟业务用时
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("执行完毕，释放锁！");
//    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void redisLock() {
        String key = "userName" + "-lock";
        ValueOperations<String, String> valueOpt = stringRedisTemplate.opsForValue();
        final String value = System.nanoTime() + "" + UUID.randomUUID().toString();
        //获取锁
        Boolean result = valueOpt.setIfAbsent(key, value);
        if (result) {
            try {
                //防止死锁设置过期时间
                stringRedisTemplate.expire(key, 20L, TimeUnit.SECONDS);
                //进行业务操作
            } catch (Exception e) {

            } finally {
                if (value.equals(valueOpt.get(key))) {
                    //对key进行删除
                    stringRedisTemplate.delete(key);
                }
            }
        }

    }

    //临时节点和Watcher机制
    public void ZooKeeperLock() {
        String key = "userName" + "-lock";
        ValueOperations<String, String> valueOpt = stringRedisTemplate.opsForValue();
        final String value = System.nanoTime() + "" + UUID.randomUUID().toString();
        //获取锁
        Boolean result = valueOpt.setIfAbsent(key, value);
        if (result) {
            try {
                //防止死锁设置过期时间
                stringRedisTemplate.expire(key, 20L, TimeUnit.SECONDS);
                //进行业务操作
            } catch (Exception e) {

            } finally {
                if (value.equals(valueOpt.get(key))) {
                    //对key进行删除
                    stringRedisTemplate.delete(key);
                }
            }
        }

    }

}
