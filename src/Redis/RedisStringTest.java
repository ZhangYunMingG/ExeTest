package Redis;

import java.util.Iterator;

import redis.clients.jedis.Jedis;
public class RedisStringTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());	// PONG
		
		// ����key��ֵ
		System.out.println(redis.set("first","this is first!"));	
		// OK
		// ��ȡkey��ֵ
		System.out.println(redis.get("first"));
		// this is first!
		// ��ȡkey��ֵ����start��end ע�⣺������end���ַ�
		System.out.println(redis.getrange("first", 0, 8));
		// this is f
		// ����key���µ�ֵ�����ҷ���key�ɵ�ֵ
		System.out.println(redis.getSet("first", "this is changed"));
		// this is first!
		// GETBIT key offset ???
		// SETBIT key offset value ???
		// SETRANGE key offset value ???
		
		// MGET key1 [key2..] ��ȡ����(һ������)���� key ��ֵ��
		redis.set("name", "123");
		System.out.println(redis.mget(new String[]{"first","name"}));
		// [this is changed, 123]
		// key ����ֵ�������ʱ�䡣��� key �Ѿ����ڣ� SETEX ������滻�ɵ�ֵ
		System.out.println(redis.setex("name", 120, "changed"));
		// ok
		
		// setnx ֻ���� key ������ʱ���� key ��ֵ��
		System.out.println(redis.setnx("setnx", "succ"));
		System.out.println(redis.get("setnx"));
		System.out.println(redis.setnx("setnx", "fail"));
		System.out.println(redis.get("setnx"));
		// 1 succ 0 succ
		
		// strlen���� key ��������ַ���ֵ�ĳ���
		System.out.println(redis.strlen("name"));
		// 7
		// msetͬʱ����һ������ key-value �ԡ�
		System.out.println(redis.mset(new String[]{"key1 value1","key2 v2","k1","v1"}));
		// ok
		// redis.get("key1 value1")  -- key2 value2
		// redis.get("k1") -- v1
		// msetnx ͬʱ����һ������ key-value ��    ���ҽ������и��� key ��������
		redis.msetnx("msetnx1","v1","msetnx2","v2");
		System.out.println(redis.get("msetnx1") + "and" + redis.get("msetnx2"));
		redis.msetnx("msetnx1","f1","msetnx2","f2");
		System.out.println(redis.get("msetnx1") + "and" + redis.get("msetnx2"));
		// v1andv2 v1andv2
		
		redis.set("incr", "10");
		// incr �� key �д��������ֵ��һ
		redis.incr("incr");
		System.out.println(redis.get("incr"));
		// 11
		// �� key �������ֵ���ϸ���������ֵ��increment��
		redis.incrBy("incr", 10);
		System.out.println(redis.get("incr"));
		// 21
		// �� key �������ֵ���ϸ����ĸ�������ֵ��increment�� 
		redis.decr("incr");
		System.out.println(redis.get("incr"));
		// 20
		redis.decrBy("incr", 9);
		System.out.println(redis.get("incr"));
		// 11
		
		// append��� key �Ѿ����ڲ�����һ���ַ����� APPEND ��� value ׷�ӵ� key ԭ����ֵ��ĩβ
		redis.append("append", "zha");
		System.out.println(redis.get("append"));
		redis.append("append", "ng");
		System.out.println(redis.get("append"));
		// zha zhang
		
		// ɾ�����е�key�е�ֵ
		Iterator<String> itor = redis.keys("*").iterator();
		while (itor.hasNext()) {
			redis.del(itor.next());
		}
		
	}
	

}
