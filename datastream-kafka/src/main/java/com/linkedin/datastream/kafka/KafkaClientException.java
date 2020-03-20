package com.linkedin.datastream.kafka;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaClientException extends Exception {
  private static final long serialVersionUID = 1;

  private final RecordMetadata _metadata;

  private final boolean _propagate;

  public RecordMetadata getMetadata() {
    return _metadata;
  }

  public boolean doPropagate() { return _propagate; }

  public KafkaClientException(RecordMetadata metadata, Throwable innerException, boolean propagate) {
    super(innerException);
    _metadata = metadata;
    _propagate = propagate;
  }

  public KafkaClientException(RecordMetadata metadata, Throwable innerException) {
    this(metadata, innerException, false);
  }
}
