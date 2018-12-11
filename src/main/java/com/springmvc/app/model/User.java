package com.springmvc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Simple entity class that creates an database table with
 * these params and restrictions
 * @author Pedro Caldeira
 */
@Entity
public class User {

   
 private static final long serialVersionUID = 1L;

    @Id
    private long googleID;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String gender;
    private String photoUrl;

    public long getGoogleID() {
        return googleID;
    }

    public void setGoogleID(int googleID) {
        this.googleID = googleID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    

  
    

    
}
