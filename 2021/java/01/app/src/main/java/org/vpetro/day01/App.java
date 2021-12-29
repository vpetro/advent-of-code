/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.vpetro.day01;

import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class App {

  public static ArrayList<Integer> readFile() throws IOException {
    Path path = Paths.get(
      System.getProperty("user.dir") + "/src/test/resources/input"
    );

    BufferedReader reader = Files.newBufferedReader(path);
    ArrayList<Integer> lines = new ArrayList<Integer>();

    String line = null;
    while((line = reader.readLine()) != null) {
      lines.add(Integer.parseInt(line));
    }
    
    return lines;

  }

  public static int solve(ArrayList<Integer> data) {

    int count = 0;

    for(int i = 0; i < data.size() - 1; i++) {
        count += data.get(i) < data.get(i+1) ? 1 : 0;
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println("Day 1 - Part 1");

    try {
      int result = solve(readFile());
      System.out.println("Result: " + result);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}