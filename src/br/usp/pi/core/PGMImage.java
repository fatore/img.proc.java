package br.usp.pi.core;

import java.io.*;

public class PGMImage extends Image {

    private String magicNumber;
    private String comment;
    private int maxGrayValue;
    private int[][] data;

    public PGMImage(int columns, int rows, String comment, int maxGrayValue) {
        super(columns, rows);
        this.magicNumber = "P2";
        this.comment = comment;
        this.maxGrayValue = maxGrayValue;
        this.data = new int[rows][columns];
    }

    public PGMImage() {
    }

    public int[][] getData() {return data;}
    public int getMaxGrayValue() {return maxGrayValue;}

    public void fillWith(int number) {
        for (int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                data[i][j] = number;
            }
        }
    }

     public void printImage() {
        System.out.println(magicNumber);
        System.out.println(comment);
        System.out.println(columns + " " + rows);
        System.out.println(maxGrayValue);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

     public void saveImage(String filename) throws Exception {
        PrintWriter out = new PrintWriter(new File(filename));
        out.println(magicNumber);
        out.println(comment);
        out.println(columns + " " + rows);
        out.println(maxGrayValue);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                out.println(data[i][j]);
            }
        }
        out.close();
    }

     public void readImage(String filename) {
         try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            
            this.magicNumber = input.readLine();
            this.comment = input.readLine();

            String line =  input.readLine();
            String splitLine[] = line.split(" ");

            this.columns = Integer.parseInt(splitLine[0]);
            this.rows = Integer.parseInt(splitLine[1]);
            
            this.maxGrayValue = Integer.parseInt(input.readLine());

            this.data = new int[rows][columns];

            for (int i = 0; i < data.length; i++) {
                for(int j = 0; j < data[i].length; j++) {
                    line = input.readLine();
                    data[i][j] = Integer.parseInt(line);
                }
            }
         } catch (Exception e) {
             System.out.println("Error: Invalid File.");
         }
     }

     public static void main(String args[]) {
     }
}
