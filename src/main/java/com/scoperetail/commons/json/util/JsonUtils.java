package com.scoperetail.commons.json.util;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.IOException;
import java.util.Optional;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

  private JsonUtils() {}

  static final ObjectMapper mapper = new ObjectMapper();

  public static final <T> T unmarshal(
      final Optional<String> message, Optional<TypeReference<T>> typeReference)
      throws JsonParseException, JsonMappingException, IOException {
    T unmarshaledMessage = null;
    log.debug("Trying to unmarshal json message {} into {} type", message, typeReference);
    unmarshaledMessage =
        mapper.readValue(
            message.orElseThrow(IOException::new), typeReference.orElseThrow(IOException::new));

    return unmarshaledMessage;
  }

  public static <E> String marshal(final E type) {
    String jsonValue = null;
    if (null != type) {
      mapper.setSerializationInclusion(NON_NULL);
      try {
        jsonValue = mapper.writeValueAsString(type);
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Empty JSON String: Object to Json transformation failed.");
      }
    }
    return jsonValue;
  }
}
