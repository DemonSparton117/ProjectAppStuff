package com.secure.userInfo;

import com.App;
import com.Comment;
import helpers.UserInfoDump;

public class User {

    

    private String name;
    private String password;
    private final boolean admin;
    private final boolean moderator;

    //constructor for guests only.
    public User() {
        this.name = "Guest";
        this.password = "";
        this.admin = false;
        this.moderator = false;
    }

    public User(String name, String password, boolean admin, boolean moderator) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.moderator = moderator;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and setters. Click on the + sign on the left to edit the code.">
    public boolean isAdmin() {
        return this.admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * This setName method is used to change the name of the user. First it checks
     * to make sure that the username the user wants to change to is not taken.
     * Then if it is not taken already, the username gets changed in the database.
     * @param name the new username the user wants
     * @return returns true if the change is successful and false if it is not
     */
    public boolean setName(String name) {
        this.name = name;
        /* database stuff */
        //TODO: make sure no one else is using the name before setting the name
        /* once you determine the username has not been taken, change the
         * username in the database too*/
        return true;//dummy return statement for now
    }

    /**
     * This method checks to make sure the user knows the current password and
     * then changes the password to the new one (it does not do the rest of the
     * checking like the setUsername one does because that is done before this
     * method is called)
     * @param password the new password the user wants to change the current password to
     * @param currentPassword what the user enters as his or her current password
     */
    public void setPassword(String password) {
        
        this.password = password;
        /* database stuff */
        //need to make sure it is changed in the database as well
    }

    public boolean isModerator() {
        return moderator;
    }// </editor-fold>

    //Edits the ArrayList forum which is an instance variable in app.
    
    public void postComment(App app, String quote) {
        /* TODO: implement the logic to add a comment to the Forum object
         * of an App's page here so all user objects can use it*/
        /* database stuff */
//<<<<<<< HEAD
        UserInfoDump info = UserInfoDump.getInstance();
        Comment comment = new Comment(info.getUsername(),quote); 
        app.addComment(comment);
    }
    
    public void editComment(App app, String newQuote, String oldQuote){
        int postion = app.getCommentPosition(oldQuote);
        UserInfoDump info = UserInfoDump.getInstance();
        Comment comment = new Comment(info.getUsername(),newQuote);
        app.editComment(comment, postion);
//=======
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
    }

    /*methods for the Users
     * post (all User objects except for Guests can do this)
     * request app (all Users except Guest and possibly Administrator can do this)
     * List to be continued*/
}
