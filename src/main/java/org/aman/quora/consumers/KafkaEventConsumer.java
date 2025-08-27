package org.aman.quora.consumers;

import lombok.RequiredArgsConstructor;
import org.aman.quora.config.KafkaConfig;
import org.aman.quora.events.ViewCount;
import org.aman.quora.repositories.QuestionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {
    private final QuestionRepository questionRepository;

    @KafkaListener(
            topics = KafkaConfig.TOPIC_NAME,
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleViewCount(ViewCount viewCount){
         questionRepository.findById(viewCount.getTargetId())
                 .flatMap(question -> {
                     Integer views = question.getViews();
                     System.out.println("Incrementing view count for our question: "+question.getId());
                     question.setViews((views==null?0: views) + 1);
                     return questionRepository.save(question);
                 })
                 .subscribe(updatedQuestion->{
                     System.out.println("view count increment for question:"+updatedQuestion.getId());
                 },error->{
                     System.out.println("Error in incrementing count for question: "+error.getMessage());
                 });
    }
}
