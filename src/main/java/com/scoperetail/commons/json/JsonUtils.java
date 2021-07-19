package com.scoperetail.commons.json;

/*-
 * *****
 * commons-json
 * -----
 * Copyright (C) 2018 - 2021 Scope Retail Systems Inc.
 * -----
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====
 */

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
