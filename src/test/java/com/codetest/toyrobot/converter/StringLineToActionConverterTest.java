package com.codetest.toyrobot.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.codetest.toyrobot.model.Action;
import com.codetest.toyrobot.model.Command;

public class StringLineToActionConverterTest {

  private StringLineToActionConverter subject;

  @Before
  public void setUp() throws Exception {
    subject = new StringLineToActionConverter();
  }

  @Test
  public void convertSuccess() throws Exception {
    Action action = subject.convert("PLACE 0,0,WEST");
    Assert.assertEquals(action.getCommand(), Command.PLACE);
    Assert.assertEquals(action.getArgument(), "PLACE 0,0,WEST");
    action = subject.convert("LEFT");
    Assert.assertEquals(action.getCommand(), Command.LEFT);
    action = subject.convert("RIGHT");
    Assert.assertEquals(action.getCommand(), Command.RIGHT);
    action = subject.convert("REPORT");
    Assert.assertEquals(action.getCommand(), Command.REPORT);
    action = subject.convert("MOVE");
    Assert.assertEquals(action.getCommand(), Command.MOVE);

  }

  @Test(expected = IllegalArgumentException.class)
  public void convertFailed() throws Exception {
    subject.convert("Go");
  }

}
