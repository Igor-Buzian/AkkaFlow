package com.example.Akka.Actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import com.example.Akka.Interfaces.OrderMessage;
import com.example.Akka.Orders.EnrichedOrder;

public class TestTimeActor {

    private final int totalMessages;
    private int processed = 0;
    private long startTime = 0;

    public TestTimeActor(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public static Behavior<OrderMessage> create(int totalMessages) {
        return Behaviors.setup(context -> new TestTimeActor(totalMessages).behavior());
    }

    private Behavior<OrderMessage> behavior() {
        return Behaviors.receive(OrderMessage.class)
                .onMessage(EnrichedOrder.class, order -> {

                    if (processed == 0) {
                        startTime = System.currentTimeMillis();
                    }

                    processed++;

                    if (processed == totalMessages) {
                        long endTime = System.currentTimeMillis();
                        System.out.println("All " + totalMessages + " messages processed in " + (endTime - startTime) + " ms");
                    }

                    return Behaviors.same();
                })
                .build();
    }
}
