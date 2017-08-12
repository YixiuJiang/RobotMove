package com.codetest.toyrobot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codetest.toyrobot.converter.StringLineToActionConverter;
import com.codetest.toyrobot.model.Action;
import com.codetest.toyrobot.model.Command;

@Service
public class ActionService {


  private static final String COMMAND_MATCHER = "(PLACE [0-4],[0-4],(NORTH|SOUTH|EAST|WEST))|MOVE|LEFT|RIGHT|REPORT";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ActionService.class);

  @Autowired
  private FileService fileService;

  @Autowired
  private StringLineToActionConverter stringLineToActionConverter;

  public List<Action> readActionsFromFile() {
    List<Action> actions = new ArrayList<>();
    List<String> lines = filterInValidLines(fileService.readGameInputFileIntoLines());
    lines.forEach(s -> actions.add(stringLineToActionConverter.convert(s)));
    return actions;
  }

  private List<String> filterInValidLines(List<String> lines){
    Optional<String> firstPlaceCommand = lines.stream()
        .filter(s -> validateLine(s) && isPlaceCommand(s))
        .findFirst();
    if (firstPlaceCommand.isPresent()){
      int firstPlaceCommandIndex = lines.indexOf(firstPlaceCommand.get());
      lines = lines.subList(firstPlaceCommandIndex,lines.size());
      return lines.stream().filter(s -> validateLine(s)).collect(Collectors.toList());
    }else {
      return Arrays.asList();
    }
  }

  private boolean validateLine(String line) {
    boolean isValidLine = line.matches(COMMAND_MATCHER);
    if (!isValidLine){
      LOGGER.info("discard invalid line ->" +line);
    }
    return isValidLine;
  }

  private boolean isPlaceCommand(String line){
    boolean isPlaceCommand = line.startsWith(Command.PLACE.name());
    if (!isPlaceCommand){
      LOGGER.info("discard not PLACE command line ->" +line);
    }return isPlaceCommand;
  }

}
