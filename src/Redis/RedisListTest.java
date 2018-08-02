package Redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

public class RedisListTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		// lpush��һ������ֵ���뵽�б�ͷ��
		// lpushx��һ��ֵ���뵽�Ѵ��ڵ��б�ͷ��
		// llen	��ȡ�б���
		System.out.println(redis.lpush("list", "first","second"));
		System.out.println(redis.lpushx("list", "third"));
		System.out.println(redis.lpushx("test", "test"));
		System.out.println(redis.llen("list"));
		// 2 3 0 3
		
		// lrange(key, start, end)��ȡ�б�ָ����Χ�ڵ�Ԫ��
		// rpush���б������һ������ֵ(����β��)
		// rpushxΪ�Ѵ��ڵ��б����ֵ(����β��)
		System.out.println(redis.lrange("list", 0, 6));
		System.out.println(redis.rpush("list", "rpush", "r1"));
		System.out.println(redis.rpushx("list", "hello"));
		System.out.println(redis.lrange("list",0,10));
		// [third, second, first] 5 6 [third, second, first, rpush, r1, hello]
		
		// ͨ��������ȡ�б��е�Ԫ��
		// ͨ�����������б�Ԫ�ص�ֵ
		System.out.println(redis.lindex("list", 3));
		System.out.println(redis.lset("list", 3, "changed"));
		System.out.println(redis.lrange("list", 0, 6));
		// rpush ok [third, second, first, changed, r1, hello]
		
		// �Ƴ�����ȡ�б�ĵ�һ��Ԫ��
		// �Ƴ�����ȡ�б����һ��Ԫ��
		// ��һ���б�����޼�(trim)������˵�����б�ֻ����ָ�������ڵ�Ԫ�أ�����ָ������֮�ڵ�Ԫ�ض�����ɾ��
		System.out.println(redis.lpop("list"));
		System.out.println(redis.lrange("list", 0, 6));
		System.out.println(redis.rpop("list"));
		System.out.println(redis.lrange("list", 0, 6));
		// third [second, first, changed, r1, hello] hello [second, first, changed, r1]
		System.out.println(redis.ltrim("list", 1, 2));
		System.out.println(redis.lrange("list", 0, 10));
		// ok [first, changed]
		
		// ǰ:before,��:after  ���б��Ԫ��ǰ���ߺ����Ԫ��
		System.out.println(redis.linsert("list", BinaryClient.LIST_POSITION.BEFORE, "changed", "before"));
		System.out.println(redis.lrange("list", 0, 8));
		System.out.println(redis.linsert("list", BinaryClient.LIST_POSITION.AFTER, "changed", "after"));
		System.out.println(redis.lrange("list", 0, 8));
		// 3 [first, before, changed] 4	[first, before, changed, after]	 
		
		redis.rpush("count", "count1","count2","12","3", "count1","count2","count2");
		// �Ƴ��б�Ԫ��
		// count > 0 : �ӱ�ͷ��ʼ���β�������Ƴ��� VALUE ��ȵ�Ԫ�أ�����Ϊ COUNT ��
		// count < 0 : �ӱ�β��ʼ���ͷ�������Ƴ��� VALUE ��ȵ�Ԫ�أ�����Ϊ COUNT �ľ���ֵ��
		// count = 0 : �Ƴ����������� VALUE ��ȵ�ֵ��
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
		// �Ƴ��б�����һ��Ԫ�أ�������Ԫ����ӵ���һ���б�����
		System.out.println(redis.rpoplpush("old", "new"));
		System.out.println(redis.lrange("old", 0, -1));
		System.out.println(redis.lrange("new", 0, -1));
		// remove1 [add1] [remove1]
		
//		BLPOP key1 [key2 ] timeout 
//		�Ƴ�����ȡ�б�ĵ�һ��Ԫ�أ� ����б�û��Ԫ�ػ������б�ֱ���ȴ���ʱ���ֿɵ���Ԫ��Ϊֹ��
//		2	BRPOP key1 [key2 ] timeout 
//		�Ƴ�����ȡ�б�����һ��Ԫ�أ� ����б�û��Ԫ�ػ������б�ֱ���ȴ���ʱ���ֿɵ���Ԫ��Ϊֹ��
//		3	BRPOPLPUSH source destination timeout 
//		���б��е���һ��ֵ����������Ԫ�ز��뵽����һ���б��в��������� ����б�û��Ԫ�ػ������б�ֱ���ȴ���ʱ���ֿɵ���Ԫ��Ϊ
		redis.del("list");
		redis.del("count");
		redis.del("old");
		redis.del("new");
		
		
		
	}

}
