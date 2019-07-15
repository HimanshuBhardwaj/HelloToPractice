package UberInterview.src.main.java.com.himanshu.interview.uber;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by himanshubhardwaj on 13/05/19.
 */

@Slf4j
public class Channel {
    private String channelName;
    private List<com.himanshu.interview.uber.Consumer> consumers;
    private TreeMap<Consumer, Integer> consumerIndexTreeMap;
    private Message[] buffer;
    private int BUFFER_DEFAULT_SIZE = 100000;
    private int offsetPublished;

    @java.beans.ConstructorProperties({"consumers", "buffer", "BUFFER_DEFAULT_SIZE", "offsetPublished"})
    public Channel(String channelName) {
        consumers = new ArrayList<>();
        //in future we can give facility to extende it
        buffer = new Message[BUFFER_DEFAULT_SIZE];
        offsetPublished = -1;
        consumerIndexTreeMap = new TreeMap<>();
        this.channelName = channelName;
    }


    public void publish(Message message) {
        //assumption that buffer will not overflow
        buffer[offsetPublished + 1] = message;
        offsetPublished++;
        notifyAllConsumers();
    }

    private void notifyAllConsumers() {
        for (Consumer consumer : consumers) {
            consumer.notifyC();
        }
    }

    public boolean addConsumer(Consumer consumer) {
        if (!consumerIndexTreeMap.containsKey(consumer)) {
            consumerIndexTreeMap.put(consumer, -1);
            return true;
        }
        return false;
    }

    public Message consume(Consumer consumer) {
        if (consumerIndexTreeMap.containsKey(consumer)) {
            if (consumerIndexTreeMap.get(consumer) < offsetPublished) {
                consumerIndexTreeMap.put(consumer, consumerIndexTreeMap.get(consumer) + 1);
                log.info(String.format("returning response for consume %s", consumer));
                return buffer[consumerIndexTreeMap.get(consumer) + 1];
            }
        } else {
            log.info(String.format("Consumer  %s is not registered for %s channel", consumer, channelName));
        }
        return null;
    }


}
