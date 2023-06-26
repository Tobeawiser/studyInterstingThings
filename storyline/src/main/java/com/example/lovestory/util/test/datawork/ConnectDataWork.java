package com.example.lovestory.util.test.datawork;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dataworks_public.model.v20200518.RunTriggerNodeRequest;
import com.aliyuncs.dataworks_public.model.v20200518.RunTriggerNodeResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

public class ConnectDataWork {

    public static void main(String[] args) {
// 设置RegionId、访问密钥和访问密码。
// 其中：cn-hangzhou表示任务所在的地域，即RegionId。
// <accessKeyId>表示访问密钥。
// <accessSecret>表示访问密码
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");

        IAcsClient client = new DefaultAcsClient(profile);

        RunTriggerNodeRequest request = new RunTriggerNodeRequest();

// 设置NodeId，表示触发式节点的节点ID，节点ID可通过ListNodes API查询获取
        request.setNodeId(700003742092L);


// 设置CycleTime，表示触发式节点的任务的运行时间戳，需将HTTP触发节点的调度配置中，节点指定的运行时间，换算为时间戳
// 如果HTTP触发节点所在的地域与调度系统所在的地域不在同个时区，存在时差，这里需配置为触发节点所在时区的时间。
// 例如，HTTP触发节点在北京地域且Cyctime为北京时间18：00，而调度系统在美西地域，此时调度系统配置时，需配置为北京时间18：00的时间戳。
        request.setCycleTime(1605629820000L);


// 设置BizDate，表示触发式节点实例所在的业务日期时间戳，需将业务日期换算为时间戳。
// 业务日期为运行时间的前一天，且时间精确到日，时分秒均为00000000。以运行日期为2020年11月25日为例，业务时间为2020112400000000，需将这个时间换算为业务日期的时间戳
// 如果HTTP触发节点所在的地域与调度系统所在的地域不在同个时区，存在时差，这里需配置为触发节点所在时区的时间。
        request.setBizDate(1605542400000L);

// 设置AppId，表示触发式节点所属的Dataworks工作空间ID，工作空间ID可通过ListProjects获取
        request.setAppId(123L);

        try {

            RunTriggerNodeResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}
