package com.codetest.toyrobot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.codetest.toyrobot.service.GameService;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

  @Autowired
  private GameService gameService;

  @Override
  public void run(String... args) {
    gameService.play();
  }


  public static void main(String[] args) {
    SpringApplication.run(GameApplication.class, args);

  }
}
