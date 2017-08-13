package com.codetest.toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.codetest.toyrobot.service.GameService;

public class GameApplicationTest {

  @InjectMocks
  private GameApplication app = new GameApplication();

  @Mock
  private GameService gameService;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testRunNoArgs() throws Exception {
    app.run();
    Mockito.verify(gameService).play();
  }

}
