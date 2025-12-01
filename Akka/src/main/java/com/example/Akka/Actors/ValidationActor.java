package com.example.Akka.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.NewOrder;

public class ValidationActor {

    public static Behavior<OrderMessage> create(ActorRef ref) {
        return Behaviors.receive(OrderMessage.class)
                .onMessage(NewOrder.class, validation -> {
                    if (validation.orderId() == null && validation.orderId().isBlank()) {
                        System.err.println("Invalid order");
                        return Behaviors.same();
                    }
                    ref.tell(validation);
                    return Behaviors.same();
                })
                .build();
    }
}
