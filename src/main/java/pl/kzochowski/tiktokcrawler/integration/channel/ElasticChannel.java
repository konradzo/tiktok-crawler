package pl.kzochowski.tiktokcrawler.integration.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import pl.kzochowski.tiktokcrawler.service.SenderService;

@Slf4j
@Component
public class ElasticChannel implements MessageChannel {

    private final SenderService senderService;

    public ElasticChannel(SenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public boolean send(Message<?> message, long timeout) {
        return false;
    }

}
