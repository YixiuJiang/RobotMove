package com.codetest.toyrobot;

import org.junit.Test;
import org.mockito.InjectMocks;

public class GameApplicationTest {

  @InjectMocks
  private GameApplication app = new GameApplication();

  @Test
  public void testRunNoArgs() throws Exception {
    app.run();
  }

}
