package com.codetest.toyrobot.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.codetest.toyrobot.exception.ToyRobotException;

@Service
public class FileService {

  @Value("${gameInputFile}")
  private String gameInputFile;

  @Value("${gameOutputFile}")
  private String gameOutputFile;

  public File getFileFromPath(String filePath) throws IOException {
    File file;
    URL fileURL = getClass().getResource(filePath);
    if (fileURL == null) {
      file = new File(filePath);
    } else {
      file = new File(fileURL.getFile());
    }
    return file;
  }

  public File getGameInputFile() {
    try {
      return getFileFromPath(gameInputFile);
    } catch (IOException e) {
      throw new ToyRobotException("Can't get game input file", e);
    }
  }

  public File getGameOutputFile() {
    try {
      return getFileFromPath(gameOutputFile);
    } catch (IOException e) {
      throw new ToyRobotException("Can't get game output file", e);
    }
  }

  public List<String> readGameInputFileIntoLines() {
    return readFileIntoLines(getGameInputFile());
  }

  public List<String> readFileIntoLines(File file) {
    try {
      List<String> lines = new ArrayList<>();
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
      bufferedReader.close();
      fileReader.close();
      return lines;
    } catch (IOException e) {
      throw new ToyRobotException("Can't read file into lines.", e);
    }
  }

  public void writeIntoOutputFile(List<String> lines) {
    File outputFile = getGameOutputFile();
    try {
      FileWriter writer = new FileWriter(outputFile);
      for (String str : lines) {
        writer.write(str + "\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new ToyRobotException("Can't write into output file " + outputFile.getAbsolutePath(),
          e);
    }
  }
}
