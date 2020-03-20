package com.scoperetail.commons.json.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericEvent<Payload> {

  private String eventName;
  private Payload payload;
}
