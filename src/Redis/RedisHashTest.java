package Redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisHashTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		// hset����ϣ�� key �е��ֶ� field ��ֵ��Ϊ value
		// hgetAll ��ȡ�ڹ�ϣ����ָ�� key �������ֶκ�ֵ
		// hexsits �鿴��ϣ�� key �У�ָ�����ֶ��Ƿ����
		// hget ��ȡ�洢�ڹ�ϣ����ָ���ֶε�ֵ
		System.out.println(redis.hset("hash", "key1", "v1"));
		System.out.println(redis.hset("hash", "key2", "v2"));
		System.out.println(redis.hgetAll("hash"));
		System.out.println(redis.hexists("hash", "key1"));
		System.out.println(redis.hexists("hash", "key5"));
		System.out.println(redis.hget("hash", "key1"));
		System.out.println(redis.hget("hash", "key5"));
		// 1 1 {key2=v2, key1=v1}
		// true false v1 null
		
		//hdelɾ��һ��������ϣ���ֶ�
		System.out.println(redis.hdel("hash", "key1","key2"));
		System.out.println(redis.hgetAll("hash"));
		
		// hsetnxֻ�����ֶ� field ������ʱ�����ù�ϣ���ֶε�ֵ
		System.out.println(redis.hsetnx("hsetnx", "hsetnx", "1"));
		System.out.println(redis.hsetnx("hsetnx", "hsetnx", "1"));
		// 1 0
		
		redis.hset("incr", "incr", "10");
		// Ϊ��ϣ�� key �е�ָ���ֶε�����ֵ�������� increment 
		System.out.println(redis.hincrBy("incr", "incr", 11));
		System.out.println(redis.hget("incr", "incr"));
		// 21 21
		// ��ȡ���й�ϣ���е��ֶ�
		// ��ȡ��ϣ�����ֶε�����
		// ��ȡ���и����ֶε�ֵ
		redis.hset("1", "k1", "v1");
		redis.hset("1", "k2", "v2");
		System.out.println(redis.hkeys("1"));
		System.out.println(redis.hlen("1"));
		System.out.println(redis.hmget("1", "k1","k2"));
		// [k1, k2] 2 [v1, v2]
		
		// ͬʱ����� field-value (��-ֵ)�����õ���ϣ�� key ��
		Map<String, String> map = new  HashMap<String, String>();
		map.put("nk1", "nv1");
		map.put("nk2", "nv2");
		System.out.println(redis.hmset("new", map));
		// ok
		
		// ��ȡ��ϣ��������ֵ
		System.out.println(redis.hvals("new"));
		// [nv1, nv2]
		
		
		
	}

}
