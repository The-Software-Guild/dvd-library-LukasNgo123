package com.m3.DVDLibrary.ui;

import java.time.LocalDate;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

    final private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public LocalDate readDate() {
        while (true){
            int year = 0,month = 0,day = 0;
            try {
                year = Integer.parseInt(this.readString("Enter year"));
                month = Integer.parseInt(this.readString("Enter month"));
                day = Integer.parseInt(this.readString("Enter day"));
                return LocalDate.of(year,month,day);
            } catch (Exception e){
                this.print("Input error. Please try again.");
            }
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return console.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput){
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e){
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }
}
