rabbitMq安装操作:
https://blog.csdn.net/legend818/article/details/118195207

rabbitMq基本操作:
https://blog.csdn.net/qq_35387940/article/details/100514134?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-100514134-blog-121138866.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-100514134-blog-121138866.pc_relevant_default&utm_relevant_index=5

https://blog.csdn.net/qq_35387940/article/details/100514134?utm_term=boot%20spring%20%E7%89%88%E6%9C%AC%E4%B8%8Erabbitmq&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-1-100514134-null-null&spm=3001.4430

.ignore文件生效 保证本地最新，git rm -r --cached . git status 查看

Mq 消息消费者实现了接收对象自动转换 MqUser mqUser

消息高可用和确认消费： 1、发送是否成功（交换机，路由，队列绑定出现问题） 2、服务宕机、崩溃 需要重启，消息丢失 3、监听消费处理消息失败而让消息重新进入队列，被重复消费

解决： 1 发送完成后进行发送确认 2 创建队列、交换机，设置持久化参数为true 3 消息ack确认机制

mq连接不上有时需要重启，我的电脑，设备，服务里面找到mq可以重启 queue里面存在消息，可以改名消费者名称将其消费掉（如果消息格式能对上）

死信队列/延迟队列: 正如名字，延迟是初衷 很多问题都来自于高并发和巨大用户量场景：系统的复杂度也与此有关 DLX DLK TTL 死信情况：1、消息被拒绝 2、超过存活时间 3、超过队列最长长度

重复名称的队列，在mq里一旦被使用过，需要换名称才能重新生效
