package com.example.Akka.Actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.NewOrder;

public class OrderProcessorActor {
    public static Behavior<OrderMessage> create(){
        return Behaviors.receive(OrderMessage.class)
                .onMessage(NewOrder.class, order ->{
                    System.out.println("New order received");
                    System.out.println("Order " + order.orderId() + " processed");
                    return Behaviors.same();
                })
                .build();
    }
}
