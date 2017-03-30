package com.mooney.gateway.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MathematicalResult {

  public enum MathematicalOperation {
    MULTIPLICATION;
  }

  private Long result;
  private MathematicalOperation action;
}
