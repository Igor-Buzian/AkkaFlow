package com.example.Akka.Orders;

import com.example.Akka.Interfaces.OrderMessage;

public record NewOrder(String orderId, String content) implements OrderMessage {
}
