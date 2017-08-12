package com.codetest.toyrobot.model;

public class Robot {

  private Position position;
  private Direction direction;

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public String reportPosition(){
    return "Output: "+position.getX()+","+position.getY()+","+direction.name();
  }

  public void move(){
   this.position.move(this.direction);
  }
}
