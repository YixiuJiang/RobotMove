package com.codetest.toyrobot.service;


import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.yml")

public class GameServiceIntegrationTest {

  @Autowired
  private GameService gameService;

  @Autowired
  private FileService fileService;

  @Test
  public void testGamePlay() throws IOException {
    gameService.play();
    List<String> outputLines = fileService.readFileIntoLines(fileService.getGameOutputFile());
    List<String> expectedOutputLines = fileService
        .readFileIntoLines(fileService.getFileFromPath("/outputTest.result"));

    String outputLine = String.join(", ", outputLines);
    String expectedOutputLine = String.join(", ", expectedOutputLines);
    Assert.assertEquals(outputLine, expectedOutputLine);

  }

}
