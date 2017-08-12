package com.codetest.toyrobot.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import com.codetest.toyrobot.model.Action;
import com.codetest.toyrobot.model.Command;
import com.codetest.toyrobot.model.Direction;
import com.codetest.toyrobot.model.Position;
import com.codetest.toyrobot.model.Robot;

public class GameServiceTest {

  @InjectMocks
  private GameService subject;

  @Mock
  private ActionService actionService;

  @Mock
  private FileService fileService;

  @Captor
  private ArgumentCaptor<ArrayList<String>> listStringArgumentCaptor;

  @Before
  public void setUp() throws Exception {
    subject = new GameService();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testPlay() throws Exception {
    List<Action> actions = new ArrayList<>();
    Action positionAction = new Action();
    positionAction.setArgument("PLACE 0,0,NORTH");
    positionAction.setCommand(Command.PLACE);
    Action moveAction = new Action();
    moveAction.setCommand(Command.MOVE);
    Action reportAction = new Action();
    reportAction.setCommand(Command.REPORT);
    actions.add(positionAction);
    actions.add(moveAction);
    actions.add(reportAction);
    Mockito.when(actionService.readActionsFromFile()).thenReturn(actions);
    subject.play();
    Mockito.verify(fileService).writeIntoOutputFile(listStringArgumentCaptor.capture());
    List<String> outputLines = listStringArgumentCaptor.getValue();
    Assert.assertEquals(outputLines.size(), 1);
    Assert.assertEquals(outputLines.get(0), "Output: 0,1,NORTH");

  }


  @Test
  public void testGetPositionFromArgument() throws Exception {
    Position position = ReflectionTestUtils
        .invokeMethod(subject, "getPostionFromArgument", "PLACE 1,2,WEST");
    Assert.assertEquals(position.getX(), 1);
    Assert.assertEquals(position.getY(), 2);
  }

  @Test
  public void testGetDirectionFromArgument() throws Exception {
    Direction direction = ReflectionTestUtils
        .invokeMethod(subject, "getDirectionFromArgument", "PLACE 1,2,WEST");
    Assert.assertEquals(direction, Direction.WEST);
  }

  @Test
  public void moveRobotSuccess() {
    Robot robot = new Robot();
    Position position = new Position();
    position.setX(0);
    position.setY(0);
    robot.setPosition(position);
    robot.setDirection(Direction.NORTH);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 1);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 2);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 3);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 4);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 4);
    robot.setDirection(Direction.SOUTH);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 3);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 2);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 1);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 0);
    ReflectionTestUtils.invokeMethod(subject, "moveRobot", robot);
    Assert.assertEquals(robot.getPosition().getX(), 0);
    Assert.assertEquals(robot.getPosition().getY(), 0);
  }


  @Test
  public void testIsRobotOnEdge() {
    Robot robot = new Robot();
    Position position = new Position();
    position.setX(0);
    position.setY(0);
    robot.setPosition(position);
    robot.setDirection(Direction.NORTH);
    boolean isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertFalse(isRobotOnEdge);
    position.setY(4);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertTrue(isRobotOnEdge);
    robot.setDirection(Direction.SOUTH);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertFalse(isRobotOnEdge);
    position.setY(0);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertTrue(isRobotOnEdge);
    robot.setDirection(Direction.WEST);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertTrue(isRobotOnEdge);
    position.setX(4);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertFalse(isRobotOnEdge);
    robot.setDirection(Direction.EAST);
    isRobotOnEdge = ReflectionTestUtils.invokeMethod(subject, "isRobotOnEdge", robot);
    Assert.assertTrue(isRobotOnEdge);
  }


}
