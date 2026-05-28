// Made by: 
    // Arin Ibrahim | ai23019@auis.edu.krd  
    // Zhyar Rebwar | zr22159@auis.edu.krd

import com.google.inject.Guice;
import com.google.inject.Injector;
import library.module.LibraryModule;
import library.ui.LibraryUI;

public class Main {

    public static void main(String[] args) {

        Injector injector =
                Guice.createInjector(new LibraryModule());

        LibraryUI ui =
                injector.getInstance(LibraryUI.class);

        ui.run();
    }
}