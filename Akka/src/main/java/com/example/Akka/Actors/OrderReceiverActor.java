package com.example.Akka.Actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

import akka.actor.typed.ActorRef;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.NewOrder;

public class OrderReceiverActor {

    public static Behavior<OrderMessage> create(ActorRef<OrderMessage> ref){
        return Behaviors.receive(OrderMessage.class)
                .onMessage(
                        NewOrder.class, newOrder ->{
                            ref.tell(newOrder);
                            return Behaviors.same();
                        }
                )
                .build();

    }
}
