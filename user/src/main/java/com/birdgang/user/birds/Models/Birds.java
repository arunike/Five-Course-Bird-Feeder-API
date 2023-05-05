package com.birdgang.user.birds.Models;

import com.birdgang.user.birdfeeder.Models.Birdfeeder;
import com.birdgang.user.user.Models.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Birds {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer birdsID;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "birdUserID", referencedColumnName = "userID")
  private User user;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "birdBirfeederID", referencedColumnName = "BirdfeederID")
  private Birdfeeder birdfeeder;

  private String birdUnderstood;
  private String birdName;
  private String birdTimeSeenID;
  private String birdImagePath;


  public Birdfeeder getBirdBirdfeederID() {
    return birdfeeder;
  }

  public void setBirdBirdfeederID(Birdfeeder birdBirdfeederID) {
    this.birdfeeder = birdBirdfeederID;
  }

  public User getBirdUserID() {
    return user;
  }

  public void setBirdUserID(User birdUserID) {
    this.user = birdUserID;
  }

  public Integer getBirdsID(){
    return birdsID;
  }

  public void setBirdsID(Integer birdsID){
    this.birdsID = birdsID;
  }

  public String getBirdUnderstood(){
    return birdUnderstood;
  }

  public void setBirdUnderstood(String birdUnderstood){
    this.birdUnderstood = birdUnderstood;
  }

  public String getBirdName(){
    return birdName;
  }

  public void setBirdName(String birdName){
    this.birdName = birdName;
  }

  public String getBirdTimeSeenID(){
    return birdTimeSeenID;
  }

  public void setBirdTimeSeenID(String birdTimeSeenID){
    this.birdTimeSeenID = birdTimeSeenID;
  }

  public String getBirdImagePath(){
    return birdTimeSeenID;
  }

  public void setBirdImagePath(String birdTimeSeenID){
    this.birdTimeSeenID = birdTimeSeenID;
  }
}