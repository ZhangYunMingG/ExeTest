package Redis;

import java.util.Iterator;

import redis.clients.jedis.Jedis;

public class RedisSetTest {
	public static void main(String[] args)
	{
		Jedis redis = new Jedis("192.168.179.130", 6379);
		redis.auth("admin");
		System.out.println(redis.ping());
		
		// �򼯺����һ��������Ա(Ԫ�ز����ظ�)
		// scard��ȡ���ϵĳ�Ա��
		// ���ؼ����е����г�Ա
		System.out.println(redis.sadd("set", "first", "second"));
		System.out.println(redis.sadd("set", "first", "third"));
		System.out.println(redis.scard("set"));
		System.out.println(redis.smembers("set"));
		// 2 1 3 [second, first, third]
		
		// �ж� member Ԫ���Ƿ��Ǽ��� key �ĳ�Ա
		System.out.println(redis.sismember("set", "second"));
		// true;
		// �Ƴ�������һ��������Ա
		System.out.println(redis.srem("set", "first", "third"));
		System.out.println(redis.smembers("set"));;
		// 2 [second]
		
		redis.sadd("random", "one", "two","three");
		// �Ƴ������һ��Ԫ�أ������ؼ����еĸ�Ԫ��
		System.out.println(redis.spop("random"));
		System.out.println(redis.smembers("random"));
		// one [two, three](����one [two, three]��������ģ�Ҳ������two [one, three])
		
		// ���ؼ�����һ�����������
		System.out.println(redis.srandmember("random"));
		// ---
		
		redis.sadd("A", "1","2","3");
		redis.sadd("B", "1","3","5");
		// ���ظ������м��ϵĲ
		// ���ظ������м��ϵĽ���
		// �������и������ϵĲ���
		System.out.println(redis.sdiff("A","B"));
		System.out.println(redis.sinter("A","B"));
		System.out.println(redis.sunion("A","B"));
		// [2] [3, 1] [3, 2, 1, 5]
		
		// ���ظ������м��ϵĲ���洢�� new  ��
		// ���ظ������м��ϵĽ������洢�� new1   ��
		// �������и������ϵĲ������洢�� new2   ��
		System.out.println(redis.sdiffstore("new", "A","B"));
		System.out.println(redis.sinterstore("new1", "A","B"));
		System.out.println(redis.smembers("new"));
		System.out.println(redis.smembers("new1"));
		// 1 2 [2] [3, 1]
		// �����new1�滻��new���µ�new���Ḳ�Ǿɵ�new�������ǵ���
		System.out.println(redis.sunionstore("new2", "A","B"));
		System.out.println(redis.smembers("new2"));
		// 4 [3, 2, 1, 5]
		
		// ��չ
		redis.sadd("C", "2","3");
		System.out.println(redis.sdiff("A","B","C"));
		// []      ˵��sdiff(A,B,C,D,...)�Ĳ��A-B-C-D-...
		System.out.println(redis.sinter("A","B","C"));
		// [3]
		System.out.println(redis.sunion("A","B","C"));
		// [3, 2, 1, 5]
		
		redis.del("new");
		// �� Ԫ�ش� old �����ƶ��� new ���� ,����old�е�Ԫ�ؽ����Ƴ�
		redis.sadd("old", "one","three");
		redis.sadd("new", "1","2");
		System.out.println(redis.smove("old", "new", "one"));
		System.out.println(redis.smembers("old"));
		System.out.println(redis.smembers("new"));
		// 1 [three] [2, 1, one]
		
		// sscan���������е�Ԫ��  ???
		// System.out.println(redis.);
		Iterator<String> itor = redis.keys("*").iterator();
		while (itor.hasNext()) {
			redis.del(itor.next());
		}
		
	}

}
