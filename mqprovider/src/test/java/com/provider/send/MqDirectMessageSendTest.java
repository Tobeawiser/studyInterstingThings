package com.provider.send;


import com.entity.DeadInfo;
import com.entity.MqUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MqDirectMessageSendTest {

    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    private MqDirectMessageSend mqDirectMessageSend;
    @Autowired
    private MqFanoutMessageSend fanoutMessageSend;
    @Autowired
    private MqTopicMessageSend mqTopicMessageSend;
    @Autowired
    private MqDeadMessageSend mqDeadMessageSend;

    @Test
    void sendMsg() throws Exception {
        String helloworld = "helloworld";
        log.info("Slf4j");
        String s = objectMapper.writeValueAsString(helloworld);
        //mqDirectMessageSend.sendStringMsg(s);

        MqUser mqUser = new MqUser();
        mqUser.setAge("22");
        mqUser.setId(22);
        mqUser.setName("mc");
        mqUser.setRelation(0.2d);
        DeadInfo deadInfo = new DeadInfo();
        deadInfo.setId(22);
        deadInfo.setMessage("i am message from deadInfo");
        mqDeadMessageSend.sendMsg(deadInfo);

        for (int i = 0; i < 1000; i++) {
            mqDirectMessageSend.sendObjectMsg(mqUser);
            mqDirectMessageSend.sendStringMsg(s);
            mqDirectMessageSend.sendObjectMsg(mqUser);
            //fanount message send
            fanoutMessageSend.sendObjectMsg(mqUser);
            //topic message send
            mqTopicMessageSend.sendObjectMsg(mqUser);
            //todo jemeter压力测试
        }

    }
}