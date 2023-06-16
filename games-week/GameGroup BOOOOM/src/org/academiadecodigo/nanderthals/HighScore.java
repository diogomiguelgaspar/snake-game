package org.academiadecodigo.nanderthals;

import java.io.*;
import java.util.Iterator;

public class HighScore{

    private String fileName = "RegisterScores.txt";
    public void writeScore(int score) throws IOException {

        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.newLine();
        bufferedWriter.write(score);

        bufferedWriter.close();

    }
    public int getHighestScore() throws IOException {

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int highestScore = 0;
        while((line = bufferedReader.readLine()) != null){
            int number = Integer.parseInt(line);
            if(number > highestScore){
                highestScore = number;
            }
        }
        return highestScore;
}
}
