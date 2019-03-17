package com.future.teamwork.utils.jedisProxy;


import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
 
/**
 * jedis代理，自动关闭jedis连接
 */
public class JedisProxy {
 
    private JedisPool jedisPool;
 
    public JedisProxy(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
 
    public Jedis createProxy() throws Exception{
        Enhancer enhancer = new Enhancer();
        //设置代理的父类，就设置需要代理的类
        enhancer.setSuperclass(Jedis.class);
        //设置自定义的代理方法
        Callback callback = new JedisHandler(jedisPool);
        enhancer.setCallback(callback);
 
        Object o = enhancer.create();
        Jedis jedis = null;
        if (o instanceof Jedis){
            jedis = (Jedis) o;
        }
        return jedis;
    }
 
}
