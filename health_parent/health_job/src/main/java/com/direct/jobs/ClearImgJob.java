package com.direct.jobs;

import com.direct.constant.RedisConstant;
import com.direct.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author ：bfury
 * @date ：24/7/2021 10:42 上午
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //1 从redis中取出两个key的差值
        Set<String> fileNames = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        //2 循环遍历差值
        for (String fileName : fileNames) {
            System.out.println("开始删除垃圾图片：" + fileName);
            //3 调用QiuniuUtis中的删除方法
            QiniuUtils.deleteFileFromQiniu(fileName);
            //4 将redis中存放所有存储到七牛云上图片名称的key对应的value中去掉已经删除的垃圾图片名称
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            System.out.println("删除垃圾图片：" + fileName + "成功");
        }

    }
}