package com.codetest.toyrobot.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.codetest.toyrobot.model.Action;
import com.codetest.toyrobot.model.Command;

@Component
public class StringLineToActionConverter implements Converter<String, Action> {

  @Override
  public Action convert(String source) {
    Action action = new Action();
    if (source.startsWith(Command.PLACE.name())){
      action.setCommand(Command.PLACE);
      action.setArgument(source);
    }else {
      action.setCommand(Command.valueOf(source));
    }
    return action;
  }
}
