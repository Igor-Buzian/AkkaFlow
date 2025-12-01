package com.example.Akka.Orders;

import com.example.Akka.Interfaces.OrderMessage;

public record ProcessedOrder(String orderId, String result) implements OrderMessage {
}
