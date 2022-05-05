package com.m3.DVDLibrary.dao;

import com.m3.DVDLibrary.dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    private Map<String, DVD> dvds = new HashMap<>();
    public static final String DVD_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDs();
        DVD newDvd = dvds.put(title, dvd);
        writeDVDs();
        return newDvd;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDs();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDs();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDs();
        DVD removedDVD = dvds.remove(title);
        writeDVDs();
        return removedDVD;
    }

    @Override
    public DVD updateDVD(String title, int choice, String updateString) throws DVDLibraryDaoException {
        loadDVDs();
        boolean keepGoing = true;
        int menuSelection = 0;
        DVD dvd = dvds.get(title);
        while (keepGoing){
            menuSelection = choice;

            switch (menuSelection){
                case 2:
                    dvd.setMpaa(updateString);
                    dvds.put(title, dvd);
                    keepGoing = false;
                    break;
                case 3:
                    dvd.setDirectorsName(updateString);
                    dvds.put(title, dvd);
                    keepGoing = false;
                    break;
                case 4:
                    dvd.setStudio(updateString);
                    dvds.put(title, dvd);
                    keepGoing = false;
                    break;
                case 5:
                    dvd.setUserRating(updateString);
                    dvds.put(title, dvd);
                    keepGoing = false;
                    break;
                case 6:
                    keepGoing = false;
                    break;
            }
        }
        writeDVDs();
        return dvd;
    }

    @Override
    public DVD updateDVD(String dvdTitle, int[] date) throws DVDLibraryDaoException {
        loadDVDs();
        boolean keepGoing = true;
        int menuSelection = 0;
        DVD dvd = dvds.get(dvdTitle);
        dvd.setReleaseDate(LocalDate.of(date[0], date[1], date[2]));
        dvds.put(dvdTitle, dvd);
        writeDVDs();
        return dvd;
    }

    private void loadDVDs() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e){
            throw new DVDLibraryDaoException("Could not load dvd data into memory", e);
        }

        String currentLine;
        DVD currentDVD;

        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
    }

    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        DVD dvdFromFile = new DVD(dvdTitle);
        dvdFromFile.setReleaseDate(LocalDate.of(Integer.parseInt(dvdTokens[1]),
                Integer.parseInt(dvdTokens[2]),
                Integer.parseInt(dvdTokens[3])));
        dvdFromFile.setMpaa(dvdTokens[4]);
        dvdFromFile.setDirectorsName(dvdTokens[5]);
        dvdFromFile.setStudio(dvdTokens[6]);
        dvdFromFile.setUserRating(dvdTokens[7]);
        return dvdFromFile;
    }

    private void writeDVDs() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        }catch (IOException e){
            throw new DVDLibraryDaoException("Could not save DVD data", e);
        }

        String dvdAsText;
        List<DVD> dvdlist = this.getAllDVDs();
        for (DVD currentDVD : dvdlist){
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    private String marshallDVD(DVD dvd){
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate().getYear() + DELIMITER;
        dvdAsText += dvd.getReleaseDate().getMonthValue() + DELIMITER;
        dvdAsText += dvd.getReleaseDate().getDayOfMonth() + DELIMITER;
        dvdAsText += dvd.getMpaa() + DELIMITER;
        dvdAsText += dvd.getDirectorsName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getUserRating();
        return  dvdAsText;
    }
}
