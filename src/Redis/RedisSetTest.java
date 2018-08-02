package Redis;

import java.util.Iterator;

import redis.clients.jedis.Jedis;

public class RedisSetTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		// 向集合添加一个或多个成员(元素不能重复)
		// scard获取集合的成员数
		// 返回集合中的所有成员
		System.out.println(redis.sadd("set", "first", "second"));
		System.out.println(redis.sadd("set", "first", "third"));
		System.out.println(redis.scard("set"));
		System.out.println(redis.smembers("set"));
		// 2 1 3 [second, first, third]
		
		// 判断 member 元素是否是集合 key 的成员
		System.out.println(redis.sismember("set", "second"));
		// true;
		// 移除集合中一个或多个成员
		System.out.println(redis.srem("set", "first", "third"));
		System.out.println(redis.smembers("set"));;
		// 2 [second]
		
		redis.sadd("random", "one", "two","three");
		// 移除随机的一个元素，并返回集合中的该元素
		System.out.println(redis.spop("random"));
		System.out.println(redis.smembers("random"));
		// one [two, three](其中one [two, three]，是随机的，也可能是two [one, three])
		
		// 返回集合中一个或多个随机数
		System.out.println(redis.srandmember("random"));
		// ---
		
		redis.sadd("A", "1","2","3");
		redis.sadd("B", "1","3","5");
		// 返回给定所有集合的差集
		// 返回给定所有集合的交集
		// 返回所有给定集合的并集
		System.out.println(redis.sdiff("A","B"));
		System.out.println(redis.sinter("A","B"));
		System.out.println(redis.sunion("A","B"));
		// [2] [3, 1] [3, 2, 1, 5]
		
		// 返回给定所有集合的差集并存储在 new  中
		// 返回给定所有集合的交集并存储在 new1   中
		// 返回所有给定集合的并集并存储在 new2   中
		System.out.println(redis.sdiffstore("new", "A","B"));
		System.out.println(redis.sinterstore("new1", "A","B"));
		System.out.println(redis.smembers("new"));
		System.out.println(redis.smembers("new1"));
		// 1 2 [2] [3, 1]
		// 如果将new1替换成new，新的new将会覆盖旧的new，而不是叠加
		System.out.println(redis.sunionstore("new2", "A","B"));
		System.out.println(redis.smembers("new2"));
		// 4 [3, 2, 1, 5]
		
		// 拓展
		redis.sadd("C", "2","3");
		System.out.println(redis.sdiff("A","B","C"));
		// []      说明sdiff(A,B,C,D,...)的差集是A-B-C-D-...
		System.out.println(redis.sinter("A","B","C"));
		// [3]
		System.out.println(redis.sunion("A","B","C"));
		// [3, 2, 1, 5]
		
		redis.del("new");
		// 将 元素从 old 集合移动到 new 集合 ,其中old中的元素将被移除
		redis.sadd("old", "one","three");
		redis.sadd("new", "1","2");
		System.out.println(redis.smove("old", "new", "one"));
		System.out.println(redis.smembers("old"));
		System.out.println(redis.smembers("new"));
		// 1 [three] [2, 1, one]
		
		// sscan迭代集合中的元素  ???
		// System.out.println(redis.);
		Iterator<String> itor = redis.keys("*").iterator();
		while (itor.hasNext()) {
			redis.del(itor.next());
		}
		
	}

}
