package com.codetest.toyrobot.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import com.codetest.toyrobot.exception.ToyRobotException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.yml")

public class FileServiceTest {


  @Autowired
  private FileService fileService;

  @Test
  public void testGetGameInputFileSuccess() throws Exception {
    File gameInputFile = fileService.getGameInputFile();
    Assert.assertNotNull(gameInputFile);
  }

  @Test
  public void testGetGameOutFileSuccess() throws Exception {
    File gameOutputFile = fileService.getGameOutputFile();
    Assert.assertNotNull(gameOutputFile);
  }

  @Test
  public void testReadGameInputFileIntoLines(){
    List<String> lines = fileService.readGameInputFileIntoLines();
    Assert.assertTrue(lines.size()==12);
  }

  @Test(expected = ToyRobotException.class)
  public void testReadGameInputFileIntoLinesFailed(){
    ReflectionTestUtils.setField(fileService,"gameInputFile","inputfile");
    fileService.readGameInputFileIntoLines();
  }

  @Test
  public void testWriteIntoOutputFile(){
    ReflectionTestUtils.setField(fileService,"gameOutputFile","test.output" );
    List<String> lines = Arrays.asList("Output: 0,0,WEST","Output: 3,3,NORTH");
    fileService.writeIntoOutputFile(lines);
    File outputFile = fileService.getGameOutputFile();
    outputFile.delete();
  }

}
