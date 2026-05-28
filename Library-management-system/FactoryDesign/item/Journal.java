//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.item;

public class Journal implements LibraryItem {

    private final String id;
    private final String title;
    private final String description;

    public Journal(String id, String title, String description) {
        this.id = id;
        this.title= title;
        this.description= description;
    }

    @Override
    public String getId() {
    	return id; 
    	}

    @Override
    public String getTitle() {
    	return title; 
    	}

    @Override
    public String getDescription() {
    	return description; 
    	}

    @Override
    public String getItemType() {
    	return "Journal"; 
    	}

    @Override
    public int getFinePerDay() {
    	return 3; 
    	}
}
