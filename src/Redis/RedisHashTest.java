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
		
		// hset将哈希表 key 中的字段 field 的值设为 value
		// hgetAll 获取在哈希表中指定 key 的所有字段和值
		// hexsits 查看哈希表 key 中，指定的字段是否存在
		// hget 获取存储在哈希表中指定字段的值
		System.out.println(redis.hset("hash", "key1", "v1"));
		System.out.println(redis.hset("hash", "key2", "v2"));
		System.out.println(redis.hgetAll("hash"));
		System.out.println(redis.hexists("hash", "key1"));
		System.out.println(redis.hexists("hash", "key5"));
		System.out.println(redis.hget("hash", "key1"));
		System.out.println(redis.hget("hash", "key5"));
		// 1 1 {key2=v2, key1=v1}
		// true false v1 null
		
		//hdel删除一个或多个哈希表字段
		System.out.println(redis.hdel("hash", "key1","key2"));
		System.out.println(redis.hgetAll("hash"));
		
		// hsetnx只有在字段 field 不存在时，设置哈希表字段的值
		System.out.println(redis.hsetnx("hsetnx", "hsetnx", "1"));
		System.out.println(redis.hsetnx("hsetnx", "hsetnx", "1"));
		// 1 0
		
		redis.hset("incr", "incr", "10");
		// 为哈希表 key 中的指定字段的整数值加上增量 increment 
		System.out.println(redis.hincrBy("incr", "incr", 11));
		System.out.println(redis.hget("incr", "incr"));
		// 21 21
		// 获取所有哈希表中的字段
		// 获取哈希表中字段的数量
		// 获取所有给定字段的值
		redis.hset("1", "k1", "v1");
		redis.hset("1", "k2", "v2");
		System.out.println(redis.hkeys("1"));
		System.out.println(redis.hlen("1"));
		System.out.println(redis.hmget("1", "k1","k2"));
		// [k1, k2] 2 [v1, v2]
		
		// 同时将多个 field-value (域-值)对设置到哈希表 key 中
		Map<String, String> map = new  HashMap<String, String>();
		map.put("nk1", "nv1");
		map.put("nk2", "nv2");
		System.out.println(redis.hmset("new", map));
		// ok
		
		// 获取哈希表中所有值
		System.out.println(redis.hvals("new"));
		// [nv1, nv2]
		
		
		
	}

}
