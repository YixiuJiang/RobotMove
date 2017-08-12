package com.codetest.toyrobot.model;

public enum Direction {

  NORTH {
    @Override
    public Direction moveLeft() {
      return WEST;
    }

    @Override
    public Direction moveRight() {
      return EAST;
    }
  }, SOUTH {
    @Override
    public Direction moveLeft() {
      return EAST;
    }

    @Override
    public Direction moveRight() {
      return WEST;
    }
  }, EAST {
    @Override
    public Direction moveLeft() {
      return NORTH;
    }

    @Override
    public Direction moveRight() {
      return SOUTH;
    }
  }, WEST {
    @Override
    public Direction moveLeft() {
      return SOUTH;
    }

    @Override
    public Direction moveRight() {
      return NORTH;
    }
  };

  public abstract Direction moveLeft();

  public abstract Direction moveRight();

}
