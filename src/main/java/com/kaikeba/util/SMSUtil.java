package com.kaikeba.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.util.HashMap;

public class SMSUtil {

    public static boolean send(String phoneNumber,String code) {
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("code = " + code);
        return true;
    }

    public static boolean login(String phoneNumber,String code) {
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("code = " + code);
        return true;
    }

    public static boolean sendBySMS(String phoneNumber,String code) {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "秘钥id", "秘钥值");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GJVun7NhPS8FMgSqqS4", "TgTLk8KVJjESFu6iC1jTHsVMK53z5Q");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", "手机号");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
//        request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("SignName", "快递e栈");
//        request.putQueryParameter("TemplateCode", "短信模板code");
        request.putQueryParameter("TemplateCode", "SMS_196641606");
//        request.putQueryParameter("TemplateParam", "填充的参数（JSON对象格式）");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if("OK".equals(result.get("Message"))) {
                return true;
            }else{
                System.out.println("短信发送失败，原因："+result.get("Message"));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginBySMS(String phoneNumber,String code) {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "秘钥id", "秘钥值");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GJVun7NhPS8FMgSqqS4", "TgTLk8KVJjESFu6iC1jTHsVMK53z5Q");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", "手机号");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
//        request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("SignName", "快递e栈");
//        request.putQueryParameter("TemplateCode", "短信模板code");
        request.putQueryParameter("TemplateCode", "SMS_197625930");         // 只需要更新这个TemplateCode的值
//        request.putQueryParameter("TemplateParam", "填充的参数（JSON对象格式）");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if("OK".equals(result.get("Message"))) {
                return true;
            }else{
                System.out.println("短信发送失败，原因："+result.get("Message"));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
