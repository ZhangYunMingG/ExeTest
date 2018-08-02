package Redis;

import redis.clients.jedis.Jedis;

public class RedisSortedSetTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		System.out.println(redis.zadd("sorted", 12 , "score"));
		
		
	}

}
