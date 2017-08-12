package com.codetest.toyrobot.service;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import com.codetest.toyrobot.converter.StringLineToActionConverter;
import com.codetest.toyrobot.model.Action;

public class ActionServiceTest {

  @InjectMocks
  private ActionService subject;

  @Mock
  private FileService fileService;

  private StringLineToActionConverter stringLineToActionConverter;

  @Before
  public void setUp() throws Exception {
    subject = new ActionService();
    MockitoAnnotations.initMocks(this);
    stringLineToActionConverter = new StringLineToActionConverter();
  }

  @Test
  public void testReadActionsFromFile() throws Exception {
    ReflectionTestUtils
        .setField(subject, "stringLineToActionConverter", stringLineToActionConverter);
    List<String> lines = Arrays
        .asList("MOVE", "PLACE 0,0,NORTH", "MOVE", "MOVE1", "LEFT", "RIGHT", "REPORT", "REPORT2");
    Mockito.when(fileService.readGameInputFileIntoLines()).thenReturn(lines);
    List<Action> actions = subject.readActionsFromFile();
    Assert.assertTrue(actions.size() == 5);

  }

  @Test
  public void testFilterInValidLines() {
    List<String> lines = Arrays
        .asList("MOVE", "PLACE 0,0,NORTH", "MOVE", "MOVE1", "LEFT", "RIGHT", "REPORT", "REPORT2");
    lines = ReflectionTestUtils.invokeMethod(subject, "filterInValidLines", lines);
    Assert.assertTrue(lines.size() == 5);

  }

  @Test
  public void testValidateLineTrue() {
    boolean isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "MOVE");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "LEFT");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "RIGHT");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "REPORT");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 0,1,SOUTH");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 1,2,WEST");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 3,4,NORTH");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 4,0,EAST");
    Assert.assertTrue(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 0,0,NORTH");
    Assert.assertTrue(isValid);
  }

  @Test
  public void testValidateLineFalse() {
    boolean isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "MOVE1");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "2LEFT");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "2RIGHT");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "2REPORT");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 0,0,SOUTH1");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 5,90,WEST");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 3,4,NORTH1");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 4,0,EAST2");
    Assert.assertFalse(isValid);
    isValid = ReflectionTestUtils.invokeMethod(subject, "validateLine", "PLACE 0,0,WEST2");
    Assert.assertFalse(isValid);
  }

}
