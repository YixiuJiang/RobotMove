package com.codetest.toyrobot.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  /**
   * only one robot while game is playing
   */
  private Robot robot;
  private List<String> outputs;

  public Robot getRobot() {
    return robot;
  }

  public void setRobot(Robot robot) {
    this.robot = robot;
  }


  public List<String> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<String> outputs) {
    this.outputs = outputs;
  }
}
