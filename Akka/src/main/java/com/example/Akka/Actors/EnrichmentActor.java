package com.example.Akka.Actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.ActorContext;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.EnrichedOrder;
import com.example.Akka.Orders.NewOrder;

import java.time.Instant;
import java.util.UUID;

public class EnrichmentActor {
        private final ActorContext<OrderMessage> context;
        private  final  ActorRef<OrderMessage> next;
        private final  String actorId;

    public EnrichmentActor(ActorContext<OrderMessage> context, ActorRef<OrderMessage> next) {
        this.context = context;
        this.next = next;
        this.actorId = "enrichment-"+ UUID.randomUUID().toString();
    }

    public static Behavior<OrderMessage> create(ActorRef<OrderMessage> next) {
           return Behaviors.setup(context -> new EnrichmentActor(context, next).behavior());
       }

       private Behavior<OrderMessage> behavior(){
        return Behaviors.receive(OrderMessage.class)
                .onMessage(NewOrder.class, order ->
                        {
                    String timestamp = Instant.now().toString();
                    System.out.println("Enriching order " + order.orderId() +
                            " at " + timestamp +
                            " by actor " + actorId);

                            EnrichedOrder enrichedOrder = new EnrichedOrder(
                                    order.orderId(),
                                    order.content(),
                                    timestamp,
                                    actorId
                            );

                            next.tell(enrichedOrder);
                            return Behaviors.same();
                        }

                )
                .build();
       }


}
