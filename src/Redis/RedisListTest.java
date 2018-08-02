package Redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

public class RedisListTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		// lpush将一个或多个值插入到列表头部
		// lpushx将一个值插入到已存在的列表头部
		// llen	获取列表长度
		System.out.println(redis.lpush("list", "first","second"));
		System.out.println(redis.lpushx("list", "third"));
		System.out.println(redis.lpushx("test", "test"));
		System.out.println(redis.llen("list"));
		// 2 3 0 3
		
		// lrange(key, start, end)获取列表指定范围内的元素
		// rpush在列表中添加一个或多个值(插在尾部)
		// rpushx为已存在的列表添加值(插在尾部)
		System.out.println(redis.lrange("list", 0, 6));
		System.out.println(redis.rpush("list", "rpush", "r1"));
		System.out.println(redis.rpushx("list", "hello"));
		System.out.println(redis.lrange("list",0,10));
		// [third, second, first] 5 6 [third, second, first, rpush, r1, hello]
		
		// 通过索引获取列表中的元素
		// 通过索引设置列表元素的值
		System.out.println(redis.lindex("list", 3));
		System.out.println(redis.lset("list", 3, "changed"));
		System.out.println(redis.lrange("list", 0, 6));
		// rpush ok [third, second, first, changed, r1, hello]
		
		// 移出并获取列表的第一个元素
		// 移除并获取列表最后一个元素
		// 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
		System.out.println(redis.lpop("list"));
		System.out.println(redis.lrange("list", 0, 6));
		System.out.println(redis.rpop("list"));
		System.out.println(redis.lrange("list", 0, 6));
		// third [second, first, changed, r1, hello] hello [second, first, changed, r1]
		System.out.println(redis.ltrim("list", 1, 2));
		System.out.println(redis.lrange("list", 0, 10));
		// ok [first, changed]
		
		// 前:before,后:after  在列表的元素前或者后插入元素
		System.out.println(redis.linsert("list", BinaryClient.LIST_POSITION.BEFORE, "changed", "before"));
		System.out.println(redis.lrange("list", 0, 8));
		System.out.println(redis.linsert("list", BinaryClient.LIST_POSITION.AFTER, "changed", "after"));
		System.out.println(redis.lrange("list", 0, 8));
		// 3 [first, before, changed] 4	[first, before, changed, after]	 
		
		redis.rpush("count", "count1","count2","12","3", "count1","count2","count2");
		// 移除列表元素
		// count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
		// count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
		// count = 0 : 移除表中所有与 VALUE 相等的值。
		System.out.println(redis.lrem("count", 1, "count1"));
		System.out.println(redis.lrange("count", 0, 7));
		System.out.println(redis.lrem("count", -1, "count2"));
		System.out.println(redis.lrange("count", 0, 7));
		System.out.println(redis.lrem("count", 0, "count2"));
		System.out.println(redis.lrange("count", 0, 7));
		// 1 [count2, 12, 3, count1, count2, count2] 1 [count2, 12, 3, count1, count2]
		// 2 [12, 3, count1]
		
		redis.rpush("old","add1","remove1");
	//	redis.rpush("new", "1");
		// 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
		System.out.println(redis.rpoplpush("old", "new"));
		System.out.println(redis.lrange("old", 0, -1));
		System.out.println(redis.lrange("new", 0, -1));
		// remove1 [add1] [remove1]
		
//		BLPOP key1 [key2 ] timeout 
//		移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
//		2	BRPOP key1 [key2 ] timeout 
//		移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
//		3	BRPOPLPUSH source destination timeout 
//		从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为
		redis.del("list");
		redis.del("count");
		redis.del("old");
		redis.del("new");
		
		
		
	}

}
