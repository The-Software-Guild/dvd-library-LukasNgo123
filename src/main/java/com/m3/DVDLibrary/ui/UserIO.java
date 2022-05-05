package com.m3.DVDLibrary.ui;

import java.time.LocalDate;

public interface UserIO {
    void print(String msg);

    LocalDate readDate();

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
}
