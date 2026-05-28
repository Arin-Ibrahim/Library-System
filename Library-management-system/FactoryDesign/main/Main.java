//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.main;

import FactoryDesign.system.LibraryProcesses;

public class Main {

    public static void main(String[] args) {

        System.out.println("  AUIS Library System (Factory Pattern)\n");

        LibraryProcesses library = new LibraryProcesses();
        library.run();
    }
}
