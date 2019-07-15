package UberInterview.src.main.java.com.himanshu.interview.uber;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) throws IOException {
        ChannelBroker channelBroker = new ChannelBroker();
        String topic = "testing";
        Publisher publisher = new Publisher(topic, channelBroker);
        Consumer consumer1 = new Consumer("Consumer 1", channelBroker);
        Consumer consumer2 = new Consumer("Consumer 2", channelBroker);
        consumer1.registerConsumer(topic);
        consumer2.registerConsumer(topic);


        publisher.publish(new Message("Testing"));
        consumer1.consume();
        consumer2.consume();
    }
}
