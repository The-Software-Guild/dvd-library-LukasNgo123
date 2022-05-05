package com.m3.DVDLibrary.dao;

import com.m3.DVDLibrary.dto.DVD;

import java.time.LocalDate;
import java.util.List;

public interface DVDLibraryDao {
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD getDVD(String title) throws DVDLibraryDaoException;

    DVD removeDVD(String title) throws DVDLibraryDaoException;

    DVD updateDVD(String title, int choice, String updateString) throws DVDLibraryDaoException;

    DVD updateDVD(String dvdTitle, int[] date) throws DVDLibraryDaoException;
}
