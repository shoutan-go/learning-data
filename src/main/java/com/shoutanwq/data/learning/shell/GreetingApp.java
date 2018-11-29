package com.shoutanwq.data.learning.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
 
@ShellComponent
public class GreetingApp {
 
  @ShellMethod("Say hi")
  public String sayHi(String name) {
    return String.format("Hi %s", name);
  }
}