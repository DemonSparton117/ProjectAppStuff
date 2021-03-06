/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

//<<<<<<< HEAD
import com.App;
//=======
//import com.others.App;
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * So this class is going to be used for managing search results. The instance
 * variables are set to static so they can be consistent throughout any users
 * entire session, making our job easier (and it is required for us to know what
 * the user input is through the HTML input)
 *
 * @author Damon Wolfgang Duckett
 */
public class SearchesInfo {

    /*these constants can be used to determine what kind
     of sorting needs to be done and also to determine
     what sort of filtering needs to be done as well*/
    public final int NAME = 1;
    public final int DEVELOPER = 2;
    public final int RATING = 3;
    public final int PLATFORM = 4;
    public final int SORT = 1;
    public final int FILTER = 2;
    
    /*they is is what will be compared to the above constants in order to
     know exactly how we need to sort or filter*/
    private int sortConstant; //is with the 1 - 4
    private int sortType; // is with the 1 - 2

    private String keyword;
    private App[] searchResults; // this is the one you can manipulate
    private String name;
    private int requestedApp;
    
    private static SearchesInfo me;

    public static SearchesInfo getInstance() {
        if(me == null) {
            me = new SearchesInfo();
        }
        return me;
    }
    
    private SearchesInfo() {
        name = "me";
        keyword = "Wolfgang";
        sortConstant = 0;
        sortType = 0;
        //searchResults = staticSearchResults;
 /* uncomment above line if we do need to make a static copy of searchResults*/
    }

    public App[] getResults() {
        searchResults = search();
        return searchResults;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and setter methods. Click on the + sign on the left to edit the code.">

    /**
     * @return the Keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param aKeyword the Keyword to set
     */
    public void setKeyword(String aKeyword) {
        keyword = aKeyword;
    }

    /*getName and setName methods are only needed for the NameResponsePage*/
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @return the sortConstant
     */
    public int getSortConstant() {
        return sortConstant;
    }

    /**
     * @param aSortConstant the sortConstant to set
     */
    public void setSortConstant(int aSortConstant) {
        sortConstant = aSortConstant;
    }

    public void setRequestedApp(int i) {
        requestedApp = i;
    }

    public int getRequestedApp() {
        return requestedApp;
        //method actually only needed for testing purposes (possibly might be required)
    }
    
    public void setSortType(int x) {
        sortType = x;
    }
    
    public int getSortType() {
        return sortType;
    }
    // </editor-fold>

    /* Database stuff */
    public App[] search() {
        ArrayList<App> thisAppList = new ArrayList<App>();
        try {
            thisAppList.addAll(DBHandling.search(keyword, false, false, 0));
        } catch (Exception ex) {
            Logger.getLogger(SearchesInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*public App(String name, String developer, String description, String[] platforms, String link) {*/
        
        searchResults = thisAppList.toArray(new App[thisAppList.size()]);
        
        return searchResults;
    }

    public App[] filterOrSort() throws Exception {
        if(sortType == SORT) {
            return sortResults();
        }
        else if (sortType == FILTER) {
            return filterResults();
        } else {
            throw new IllegalStateException("Wrong type argument: " + sortType);
        }
    }
    
    /* Database stuff */
    private App[] filterResults() throws Exception {
        //TODO: add logic to change searchResults so that things are filtered
        //according to the sortConstant variable
        //use the keyword variable to help the filtering

        ArrayList<App> tmp = DBHandling.search(keyword, true, false, sortConstant);
        App[] ret = tmp.toArray(new App[tmp.size()]);
        return ret;
    }

    /* Database stuff */
    private App[] sortResults() throws Exception {
        //TODO: add logic to change searchResults so that things are sorted
        //according to the sortConstant variable
        ArrayList<App> tmp = DBHandling.search(keyword, false, true, sortConstant);
        App[] ret = tmp.toArray(new App[tmp.size()]);
        return ret;
    }

    /**
     * This method is designed so that the servlet appPageMaker can get the app
     * that you need to create a page for an app. To do this properly, the link
     * to the app's page will set a variable equal to the element number of the
     * app as it is stored in the searchResults array and then the
     * staticRequestedApp variable will be set equal to that using a Bean in a
     * stepping stone jsp page that I need to set up and then everything will be
     * fine from there
     *
     * @return the app in the search results that the user wants to see
     */
    public App getDesiredApp() {
        return searchResults[requestedApp];
    }

    /**
     * this method should be deleted as soon as possible. It is here as dummy
     * code until the rest of the logic is properly implemented
     * @return an array of some made up apps
     */
    @Deprecated
    private App[] makeAppList() {
        ArrayList<App> thisAppList = new ArrayList<App>();
        try {
            thisAppList.addAll(DBHandling.search(keyword, false, false, 0));
        } catch (Exception ex) {
            Logger.getLogger(SearchesInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*public App(String name, String developer, String description, String[] platforms, String link) {*/
        
        return thisAppList.toArray(new App[thisAppList.size()]);
    }
    /*TODO: need to make a method that will sort all of the app objects in a specified way*/
}
