package com.example.Akka.Orders;

import com.example.Akka.Interfaces.OrderMessage;

public record NewOrder(String orderId, String context) implements OrderMessage {
}
