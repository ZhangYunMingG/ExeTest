package kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

import kafka.producer.KeyedMessage;
import kafka.serializer.StringEncoder;



public class kafkaProducer extends Thread{
    private String topic;
    public kafkaProducer(String topic){
        super();
        this.topic = topic;
    }
    public static void main(String[] args) {
        new kafkaProducer("mytopic").start();
    }
    @Override
    public void run() {
        Producer producer =createProducer();
        int i = 0;
        while(true){
 //           producer.send(new KeyedMessage<Integer, String>(topic, "message:"+i++));;
            System.out.println("·¢ËÍ³É¹¦£¡");
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private Producer createProducer(){
        Properties properties = new Properties();
        properties.put("zk.connect", "xx.xx.xx.xx:2181");
        properties.put("serializer.class",StringEncoder.class.getName());
        properties.put("metadata.broker.list","xx.xx.xx.xx:9092");
 //       return new Producer<Integer, String>(new ProducerConfig(properties));
        return null;
    }
    
}