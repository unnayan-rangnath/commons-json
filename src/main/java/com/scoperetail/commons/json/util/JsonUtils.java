package com.scoperetail.commons.json.util;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

  private JsonUtils() {}

  static final ObjectMapper mapper = new ObjectMapper();

  public static final <T> T unmarshal(
      final Optional<String> message, Optional<TypeReference<T>> typeReference) throws IOException {
    String incomingMessage =
        message.orElseThrow(() -> new IOException("Unable to unmarshal :: Message = null"));
    TypeReference<T> incomingType =
        typeReference.orElseThrow(() -> new IOException("Unable to unmarshal :: Type = null"));
    log.debug("Trying to unmarshal json message {} into {} type", incomingMessage, incomingType);
    return mapper.readValue(incomingMessage, incomingType);
  }

  public static <E> String marshal(final Optional<E> obj) throws IOException {
    mapper.setSerializationInclusion(NON_NULL);
    return mapper.writeValueAsString(
        obj.orElseThrow(() -> new IOException("Unable to marshal :: obj = null")));
  }
}
