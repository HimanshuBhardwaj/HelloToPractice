package UberInterview.src.main.java.com.himanshu.interview.uber;

/**
 * Created by himanshubhardwaj on 13/05/19.
 */
public class Publisher implements IPublisher {
    String topicName;
    ChannelBroker channelBroker;

    public Publisher(String topic, ChannelBroker channelBroker) {
        this.topicName = topic;
        this.channelBroker = channelBroker;
        createTopic();
     }

    @Override
    public void createTopic() {
        channelBroker.createTopic(topicName);
    }

    @Override
    public void publish(Message message) {
        channelBroker.publish(topicName, message);
    }
}
