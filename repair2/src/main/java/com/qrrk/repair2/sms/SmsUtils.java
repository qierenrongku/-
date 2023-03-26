package com.qrrk.repair2.sms;


import com.qrrk.repair2.configuration.SmsInfoConfig;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils
{
    @Autowired
    private SmsInfoConfig smsInfoConfig;
    public  void sendMessage(String[] phone,String[] params) {
        try {

            Credential cred = new Credential("AKIDWszrUryqUt3zg8KHzRebJlyvc4HExRgg", "nKceQwDE0WnpcBUZOyINCxd6KJ3XmmW0");

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setReqMethod("POST");

            httpProfile.setConnTimeout(60);

            httpProfile.setEndpoint("sms.tencentcloudapi.com");


            ClientProfile clientProfile = new ClientProfile();

            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);

            SendSmsRequest req = new SendSmsRequest();



            req.setSmsSdkAppId(smsInfoConfig.getAppId().toString());


            req.setSignName(smsInfoConfig.getSmsSign());

            /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
            String senderid = "";
            req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            String sessionContext = "";
            req.setSessionContext(sessionContext);

            /* 短信号码扩展号: 默认未开通，如需开通请联系 [sms helper] */
            String extendCode = "";
            req.setExtendCode(extendCode);


            req.setTemplateId(smsInfoConfig.getTemplateId().toString());

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */

            req.setPhoneNumberSet(phone);

            /* 模板参数: 若无模板参数，则设置为空 */

            req.setTemplateParamSet(params);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));

            // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }
}