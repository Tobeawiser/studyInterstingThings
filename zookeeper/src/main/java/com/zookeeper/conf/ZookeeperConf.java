package com.zookeeper.conf;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ZookeeperConf {

    @Autowired
    private Environment environment;

    /**
     * 自定义高度封装过的客户端Curator实例
     *
     * @return CuratorFramework
     */
    @Bean
    public CuratorFramework curatorFramework() {
        String zkHost = environment.getProperty("zk.host");
        String zkNameSpace = environment.getProperty("zk.namespace");
        CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString(zkHost)
                .namespace(zkNameSpace)
                .retryPolicy(new RetryNTimes(5, 1000))
                .build();

        curatorFramework.start();
        return curatorFramework;
    }

}
