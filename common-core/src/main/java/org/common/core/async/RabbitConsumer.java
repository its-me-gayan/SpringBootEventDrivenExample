package org.common.core.async;

public interface RabbitConsumer<T> {
    public void receiveEvent(T t) throws Exception;
}
