package com.gnahz.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.gnahz.pojo.dto.OssCallbackParam;
import com.gnahz.pojo.dto.OssCallbackResult;
import com.gnahz.pojo.dto.OssPolicyResult;
import com.gnahz.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-12-10:02
 * @create 忆项目(小白)
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    @Autowired
    private OSSClient ossClient;

    /**
     * 签名生成
     * @return
     */
    @Override
    public OssPolicyResult policy() {
        //创建一个OssPolicyResult对象，用于存储生成的策略结果
        OssPolicyResult result = new OssPolicyResult();
        //存储目录
        //根据当前日期生成一个存储目录名，格式为"yyyyMMdd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + sdf.format(new Date());
        //签名有效期
        //计算签名的有效期，即从当前时间开始，加上预设的有效期（ALIYUN_OSS_EXPIRE）
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        //文件大小
        //设置文件的最大大小（ALIYUN_OSS_MAX_SIZE），单位为MB
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        //回调地址
        OssCallbackParam callback = new OssCallbackParam();
        callback.setCallbackUrl(ALIYUN_OSS_CALLBACK);
        callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCallbackBody("application/x-www-form-urlencoded");
        //提交节点
        //https://gnahz-yi.oss-cn-hangzhou.aliyuncs.com/
        String action = "https://"+ALIYUN_OSS_BUCKET_NAME + "."+ALIYUN_OSS_ENDPOINT;
        try{
            //创建一个PolicyConditions对象，并添加一个条件项，要求上传的文件以指定的目录名开头
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE,0,maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith,PolicyConditions.COND_KEY,dir);
            //使用ossClient.generatePostPolicy()方法生成POST策略，传入过期时间和条件项
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            //将postPolicy字符串以UTF-8编码方式转换为字节数组，并将结果存储在名为binaryData的字节数组变量中
            byte[] binaryData = postPolicy.getBytes("utf-8");//StandardCharsets.UTF_8
            //将生成的策略转换为Base64编码的字符串
            String policy = BinaryUtil.toBase64String(binaryData);
            //使用ossClient.calculatePostSignature()方法计算策略的签名
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("UTF-8"));
            //返回结果
            //将生成的签名、策略、存储目录名等信息设置到OssPolicyResult对象中
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(action);
        }catch (Exception e){
            //如果在生成过程中发生异常，记录错误日志。
            LOGGER.error("签名生成失败",e);
        }
        //返回包含生成策略结果的OssPolicyResult对象
        return result;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setHeight(request.getParameter("height"));
        result.setWidth(request.getParameter("width"));
        return result;
    }
}
