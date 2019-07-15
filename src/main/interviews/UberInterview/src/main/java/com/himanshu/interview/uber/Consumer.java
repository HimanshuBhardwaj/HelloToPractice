package UberInterview.src.main.java.com.himanshu.interview.uber;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by himanshubhardwaj on 13/05/19.
 */

@AllArgsConstructor
@ToString
public class Consumer implements IConsumer, Comparable<Consumer> {
    @ToString.Exclude
    ChannelBroker channelBroker;
    String name;
    String topic;

    public Consumer(String name, ChannelBroker channelBroker) {
        this.name = name;
        this.channelBroker = channelBroker;
    }

    public void registerConsumer(String topicname) {
        channelBroker.registerConsumer(this, topicname);
        this.topic = topicname;
    }


    @Override
    public void consume() {
        channelBroker.consume(this, topic);

    }

    @Override
    public void notifyC() {
        System.out.println("new message published in topic " + topic);
        consume();
    }

    @Override
    public int compareTo(Consumer o) {
        if (this.name.equals(o.name)) {
            return 0;
        } else {
            //implement it
            return 1;

        }
    }
}
