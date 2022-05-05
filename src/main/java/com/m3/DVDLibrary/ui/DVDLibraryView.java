package com.m3.DVDLibrary.ui;

import com.m3.DVDLibrary.dto.DVD;

import java.time.LocalDate;
import java.util.List;

public class DVDLibraryView {
    private UserIO io;

    public DVDLibraryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add DVD to the collection");
        io.print("2. Remove DVD from the collection");
        io.print("3. Edit DVD in the collection");
        io.print("4. List all DVDs");
        io.print("5. Search for DVD by title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices", 1, 6);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        LocalDate newDate = io.readDate();
        String mpaa = io.readString("Please enter MPAA Rating");
        String director = io.readString("Please enter director's name");
        String studio = io.readString("Please enter Studio name");
        String comment = io.readString("Please enter your rating/comment");
        DVD newDVD = new DVD(title);
        newDVD.setReleaseDate(newDate);
        newDVD.setMpaa(mpaa);
        newDVD.setDirectorsName(director);
        newDVD.setStudio(studio);
        newDVD.setUserRating(comment);
        return newDVD;
    }

    public void displaySuccessBanner() {
        io.readString("DVD successfully added. Please hit enter to continue");
    }

    public void displayAllDVDsBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList){
            io.print(currentDVD.getTitle());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public String getDVDTitleChoice() {
        return io.readString("Please enter DVD title.");
    }

    public void displayRemoveResult(DVD removeDvd) {
        if (removeDvd != null){
            io.print("DVD successfully removed");
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayShowDVDBanner() {
        io.print("=== Show DVD details===");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null){
            io.print(dvd.getTitle() + " " + dvd.getReleaseDate() + " MPAA Rating: " + dvd.getMpaa());
            io.print("Director: " + dvd.getDirectorsName() + " Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue");
    }
}
