package com.codetest.toyrobot;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

  @Override
  public void run(String... args) {
  }


  public static void main(String[] args) {
    SpringApplication.run(GameApplication.class, args);

  }
}
