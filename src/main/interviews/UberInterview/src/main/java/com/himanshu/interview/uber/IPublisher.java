package UberInterview.src.main.java.com.himanshu.interview.uber;

/**
 * Created by himanshubhardwaj on 13/05/19.
 */
public interface IPublisher {

    ChannelBroker channelBroker = null;

    public void createTopic();

    public void publish(Message message);
}
