package UberInterview.src.main.java.com.himanshu.interview.uber;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * Created by himanshubhardwaj on 13/05/19.
 */
@Slf4j
//make is singleton
public class ChannelBroker {
    private HashMap<String, Channel> listOfChannel;

    public ChannelBroker() {
        listOfChannel = new HashMap<>();
    }

    public void registerConsumer(Consumer consumer, String channel) {
        if (listOfChannel.containsKey(channel)) {
            listOfChannel.get(channel).addConsumer(consumer);
        }
    }


    public void createTopic(String topicName) {
        if (!listOfChannel.containsKey(topicName)) {
            listOfChannel.put(topicName, new Channel(topicName));
            log.info(topicName + "created");
        } else {
            log.info(topicName + " Exists!");
        }
    }

    public void publish(String topicName, Message message) {
        if (listOfChannel.containsKey(topicName)) {
            listOfChannel.get(topicName).publish(message);
        } else {
            log.info(String.format("topicName %s does not exist", topicName));
        }

    }

    public Message consume(Consumer consumer, String topic) {
        return listOfChannel.get(topic).consume(consumer);
    }
}
