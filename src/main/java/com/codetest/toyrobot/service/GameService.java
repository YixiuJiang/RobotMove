package com.codetest.toyrobot.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.codetest.toyrobot.exception.ToyRobotException;
import com.codetest.toyrobot.model.Action;
import com.codetest.toyrobot.model.Command;
import com.codetest.toyrobot.model.Direction;
import com.codetest.toyrobot.model.Game;
import com.codetest.toyrobot.model.Position;
import com.codetest.toyrobot.model.Robot;

@Service
public class GameService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GameService.class);
  @Autowired
  private ActionService actionService;

  @Autowired
  private FileService fileService;

  public void play() {
    List<Action> actions = actionService.readActionsFromFile();
    if (CollectionUtils.isEmpty(actions)) {
      throw new ToyRobotException("No valid robot actions!");
    } else {
      Game game = new Game();
      game.setOutputs(new ArrayList<>());
      actions.forEach(action -> excuteAction(action, game));
      fileService.writeIntoOutputFile(game.getOutputs());
    }
  }

  private void excuteAction(Action action, Game game) {
    if (action.getCommand().equals(Command.PLACE)) {
      Robot robot = new Robot();
      robot.setDirection(getDirectionFromArgument(action.getArgument()));
      robot.setPosition(getPostionFromArgument(action.getArgument()));
      game.setRobot(robot);
    } else {
      Robot robot = game.getRobot();
      if (action.getCommand().equals(Command.LEFT)) {
        robot.setDirection(robot.getDirection().moveLeft());
      } else if (action.getCommand().equals(Command.RIGHT)) {
        robot.setDirection(robot.getDirection().moveRight());
      } else if (action.getCommand().equals(Command.MOVE)) {
        moveRobot(robot);
      } else {
        String robotPosition = robot.reportPosition();
        LOGGER.info(robotPosition);
        game.getOutputs().add(robotPosition);
      }
    }
  }

  private void moveRobot(Robot robot) {
    if (!isRobotOnEdge(robot)) {
      robot.move();
    }
  }


  private boolean isRobotOnEdge(Robot robot) {
    return (robot.getDirection().equals(Direction.EAST) && robot.getPosition().getX() == 4) ||
        (robot.getDirection().equals(Direction.WEST) && robot.getPosition().getX() == 0) ||
        (robot.getDirection().equals(Direction.NORTH) && robot.getPosition().getY() == 4) ||
        (robot.getDirection().equals(Direction.SOUTH) && robot.getPosition().getY() == 0);
  }

  /**
   * Argument is always valid as we've already filtered invalid command
   */
  private Position getPostionFromArgument(String argument) {
    Position position = new Position();
    position.setX(Integer.parseInt(argument.substring(6, 7)));
    position.setY(Integer.parseInt(argument.substring(8, 9)));
    return position;
  }

  /**
   * Argument is always valid as we've already filtered invalid command
   */
  private Direction getDirectionFromArgument(String argument) {
    String directionString = argument.substring(argument.lastIndexOf(",") + 1);
    return Direction.valueOf(directionString);
  }
}
