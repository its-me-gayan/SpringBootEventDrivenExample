package org.common.core.async;

public interface RabbitProducer<T> {
    public void publishEvent(T t) throws Exception;
}
