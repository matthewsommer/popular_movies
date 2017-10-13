package io.mattsommer.networking;

/**
 * Created by matt on 10/12/17.
 */

public enum Error {
  SORT_PREFERENCE(0, "Sort preference not set properly."),
  STREAM(1, "Error closing stream.");

  private final int code;
  private final String description;

  Error(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String toString() {
    return code + ": " + description;
  }
}