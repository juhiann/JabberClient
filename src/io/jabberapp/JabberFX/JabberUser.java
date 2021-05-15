package io.jabberapp.JabberFX;

import javafx.beans.property.SimpleStringProperty;

public class JabberUser {
  private final SimpleStringProperty username;
  private final SimpleStringProperty jab;
  private final SimpleStringProperty like;
  private final SimpleStringProperty likeCount;

  public JabberUser(){
    this.likeCount = new SimpleStringProperty("");
    this.like = new SimpleStringProperty("");
    this.jab =  new SimpleStringProperty("");
    this.username =  new SimpleStringProperty("");

  }

  public JabberUser(String username,String jab, String like, String likeCount ){
    this.username = new SimpleStringProperty(username);
    this.jab = new SimpleStringProperty(jab);
    this.like = new SimpleStringProperty(like);
    this.likeCount = new SimpleStringProperty(likeCount);

  }



  public String getJab() {
    return jab.get();
  }

  public SimpleStringProperty jabProperty() {
    return jab;
  }

  public String getUsername() {
    return username.get();
  }

  public SimpleStringProperty usernameProperty() {
    return username;
  }

  public String getLike() {
    return like.get();
  }

  public SimpleStringProperty likeProperty() {
    return like;
  }

  public String getLikeCount() {
    return likeCount.get();
  }

  public SimpleStringProperty likeCountProperty() {
    return likeCount;
  }
}
