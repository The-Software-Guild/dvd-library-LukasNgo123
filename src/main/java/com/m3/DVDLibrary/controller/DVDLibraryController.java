package com.m3.DVDLibrary.controller;

import com.m3.DVDLibrary.dao.DVDLibraryDao;
import com.m3.DVDLibrary.dao.DVDLibraryDaoException;
import com.m3.DVDLibrary.dto.DVD;
import com.m3.DVDLibrary.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {
    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing){
                menuSelection = getMenuSelection();

                switch (menuSelection){
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listAllDVDs();
                        break;
                    case 5:
                        searchForDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void searchForDVD() throws DVDLibraryDaoException {
        view.displayShowDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }

    private void listAllDVDs() throws DVDLibraryDaoException {
        view.displayAllDVDsBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void editDVD() {
        System.out.println("not implemented yet");
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }

    private void addDVD() throws DVDLibraryDaoException{
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displaySuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
}
