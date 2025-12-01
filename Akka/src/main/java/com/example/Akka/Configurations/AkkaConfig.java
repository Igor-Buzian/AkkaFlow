package com.example.Akka.Configurations;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import com.example.Akka.Actors.OrderProcessorActor;
import com.example.Akka.Actors.OrderReceiverActor;
import com.example.Akka.Interfaces.OrderMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem<OrderMessage> actorSystem(OrderProcessorActor orderProcessorActor){
        ActorSystem system = ActorSystem.create(
                Behaviors.setup(context -> {
                    var processor = context.spawn(OrderProcessorActor.create(), "OrderProcessorActor");

                    var reciver = context.spawn(OrderReceiverActor.create(processor), "OrderReceiverActor");
                    return Behaviors.empty();
        }), "order-system");
        return system;
    }
}
