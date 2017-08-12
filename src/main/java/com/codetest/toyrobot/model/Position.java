package com.codetest.toyrobot.model;

public class Position {

  private int x;

  private int y;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void move(Direction direction) {
    if (direction.equals(Direction.EAST)) {
      this.x = this.x + 1;
    } else if (direction.equals(Direction.WEST)) {
      this.x = this.x - 1;
    } else if (direction.equals(Direction.NORTH)) {
      this.y = this.y + 1;
    } else {
      this.y = this.y - 1;
    }
  }

}
