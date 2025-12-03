package com.example.Akka.Orders;

import com.example.Akka.Interfaces.OrderMessage;

public record EnrichedOrder(String orderId,
                            String content,
                            String actorId,
                            String timestamp) implements OrderMessage {
}
