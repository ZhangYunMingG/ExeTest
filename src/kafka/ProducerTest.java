package kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerTest {
	
	private void execMsgSend() {
        Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.137.117:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "0");
         
        ProducerConfig config = new ProducerConfig(props); 
         
         
        Producer<String, String> procuder = new Producer<>(config);
         
        String topic = "mytopic";
        for (int i = 1; i <= 10; i++) {
            String value = "value_" + i;
            KeyedMessage<String, String> msg = new KeyedMessage<String, String>(topic, value);
//            procuder.send(msg);
        }
             
        procuder.close();
    }
     
    /**
     * @param args
     */
    public static void main(String[] args) {
    	ProducerTest simpleProducer = new ProducerTest();
        simpleProducer.execMsgSend();
    }
}
