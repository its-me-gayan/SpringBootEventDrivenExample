package org.common.core.constant;

public class RabbitMQConstant {

    public static final String ORDER_EVENT_INVENTORY_QUEUE = "order.inventory.queue";
    public static final String ORDER_EVENT_NOTIFICATION_QUEUE = "order.notification.queue";

    public static final String ORDER_EVENT_DLQ_INVENTORY_QUEUE = "order.inventory.queue.dlq";
    public static final String ORDER_EVENT_DLQ_NOTIFICATION_QUEUE = "order.notification.queue.dlq";

    public static final String ORDER_EVENT_TOPIC_EXCHANGE = "order.event.topic.exchange";
    public static final String ORDER_EVENT_DLQ_TOPIC_EXCHANGE = "order.event.topic.exchange.dlq";
    public static final String ORDER_EVENT_INVENTORY_ROUTING_KEY = "order.event.topic.exchange.dlq";
    public static final String ORDER_EVENT_NOTIFICATION_ROUTING_KEY = "order.event.topic.exchange.dlq";
    public static final String ORDER_EVENT_ALL_ROUTING_KEY = "order.event.all";
}
