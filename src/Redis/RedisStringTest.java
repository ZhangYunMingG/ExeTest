package Redis;

import java.util.Iterator;

import redis.clients.jedis.Jedis;
public class RedisStringTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());	// PONG
		
		// 设置key的值
		System.out.println(redis.set("first","this is first!"));	
		// OK
		// 获取key的值
		System.out.println(redis.get("first"));
		// this is first!
		// 获取key的值，从start到end 注意：包括第end个字符
		System.out.println(redis.getrange("first", 0, 8));
		// this is f
		// 设置key的新的值，并且返回key旧的值
		System.out.println(redis.getSet("first", "this is changed"));
		// this is first!
		// GETBIT key offset ???
		// SETBIT key offset value ???
		// SETRANGE key offset value ???
		
		// MGET key1 [key2..] 获取所有(一个或多个)给定 key 的值。
		redis.set("name", "123");
		System.out.println(redis.mget(new String[]{"first","name"}));
		// [this is changed, 123]
		// key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值
		System.out.println(redis.setex("name", 120, "changed"));
		// ok
		
		// setnx 只有在 key 不存在时设置 key 的值。
		System.out.println(redis.setnx("setnx", "succ"));
		System.out.println(redis.get("setnx"));
		System.out.println(redis.setnx("setnx", "fail"));
		System.out.println(redis.get("setnx"));
		// 1 succ 0 succ
		
		// strlen返回 key 所储存的字符串值的长度
		System.out.println(redis.strlen("name"));
		// 7
		// mset同时设置一个或多个 key-value 对。
		System.out.println(redis.mset(new String[]{"key1 value1","key2 v2","k1","v1"}));
		// ok
		// redis.get("key1 value1")  -- key2 value2
		// redis.get("k1") -- v1
		// msetnx 同时设置一个或多个 key-value 对    当且仅当所有给定 key 都不存在
		redis.msetnx("msetnx1","v1","msetnx2","v2");
		System.out.println(redis.get("msetnx1") + "and" + redis.get("msetnx2"));
		redis.msetnx("msetnx1","f1","msetnx2","f2");
		System.out.println(redis.get("msetnx1") + "and" + redis.get("msetnx2"));
		// v1andv2 v1andv2
		
		redis.set("incr", "10");
		// incr 将 key 中储存的数字值增一
		redis.incr("incr");
		System.out.println(redis.get("incr"));
		// 11
		// 将 key 所储存的值加上给定的增量值（increment）
		redis.incrBy("incr", 10);
		System.out.println(redis.get("incr"));
		// 21
		// 将 key 所储存的值加上给定的浮点增量值（increment） 
		redis.decr("incr");
		System.out.println(redis.get("incr"));
		// 20
		redis.decrBy("incr", 9);
		System.out.println(redis.get("incr"));
		// 11
		
		// append如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾
		redis.append("append", "zha");
		System.out.println(redis.get("append"));
		redis.append("append", "ng");
		System.out.println(redis.get("append"));
		// zha zhang
		
		// 删除所有的key中的值
		Iterator<String> itor = redis.keys("*").iterator();
		while (itor.hasNext()) {
			redis.del(itor.next());
		}
		
	}
	

}
