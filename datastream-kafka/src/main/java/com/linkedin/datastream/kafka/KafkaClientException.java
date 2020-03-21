package com.linkedin.datastream.kafka;
import org.apache.kafka.clients.producer.RecordMetadata;

class KafkaClientException extends Exception {
  private static final long serialVersionUID = 1;

  private final RecordMetadata _metadata;

  public RecordMetadata getMetadata() {
    return _metadata;
  }

  public KafkaClientException(RecordMetadata metadata, Throwable innerException) {
    super(innerException);
    _metadata = metadata;
  }
}
