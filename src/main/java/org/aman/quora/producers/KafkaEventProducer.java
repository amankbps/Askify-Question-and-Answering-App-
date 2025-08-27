package org.aman.quora.producers;

import lombok.RequiredArgsConstructor;
import org.aman.quora.config.KafkaConfig;
import org.aman.quora.events.ViewCount;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    public void publishViewCountEvent(ViewCount viewCountEvent) {
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(),viewCountEvent)
                .whenComplete((result,err)->{
                    if(err!=null){
                        System.out.println("Error publishing view count event: "+err.getMessage());
                    }
                });

    }
}
