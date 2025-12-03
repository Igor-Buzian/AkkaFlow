package com.example.Akka.Configurations;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import com.example.Akka.Actors.*;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.NewOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class AkkaConfig {
    private static final boolean IS_DEV = true;
    private static final int TOTAL_MESSAGES = 1_000_000;
    @Bean
    public ActorSystem<OrderMessage> actorSystem(){
        ActorSystem system = ActorSystem.create(

                Behaviors.setup(context -> {
                    ActorRef<OrderMessage> processor;
                    if (IS_DEV) {
                        processor = context.spawn(TestTimeActor.create(TOTAL_MESSAGES), "TestTimeActor");
                    } else {
                        processor = context.spawn(OrderProcessorActor.create(), "OrderProcessorActor");
                    }
                   // var processor = context.spawn(OrderProcessorActor.create(), "OrderProcessorActor");
                    var enrichment = context.spawn(EnrichmentActor.create(processor), "EnrichmentActor");
                    var validation = context.spawn(ValidationActor.create(enrichment), "ValidationActor");

                   var receiver = context.spawn(OrderReceiverActor.create(validation), "OrderReceiverActor");

                    for (int i = 0; i < TOTAL_MESSAGES; i++) {
                        receiver.tell(new NewOrder("order-" + i, "payload-" + i));
                    }
                    return Behaviors.empty();

        }), "order-system");
        return system;
    }
}
