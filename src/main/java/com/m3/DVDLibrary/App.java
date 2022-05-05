package com.m3.DVDLibrary;

import com.m3.DVDLibrary.controller.DVDLibraryController;
import com.m3.DVDLibrary.dao.DVDLibraryDao;
import com.m3.DVDLibrary.dao.DVDLibraryDaoFileImpl;
import com.m3.DVDLibrary.ui.DVDLibraryView;
import com.m3.DVDLibrary.ui.UserIO;
import com.m3.DVDLibrary.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
