package com.codetest.toyrobot.exception;

public class ToyRobotException extends RuntimeException {

  public ToyRobotException(String string) {
    super(string);
  }

  public ToyRobotException(String string, Exception e) {
    super(string, e);
  }

}
