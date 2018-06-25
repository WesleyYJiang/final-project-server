package com.example.finalserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.List;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Campaign")
public class Campaign {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  private String description;
  private String type;
  private String location;
  private String owner;
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

//  @OneToMany(mappedBy="campaign")
//  @JsonIgnore
//  private List<Action> actions;

//  @ManyToMany(fetch = FetchType.LAZY,
//          cascade = {
//                  CascadeType.PERSIST,
//                  CascadeType.MERGE
//          },
//          mappedBy = "tags")
  @ManyToMany(mappedBy="campaigns")
  @JsonIgnore
  private List<User> users;


  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getCreated() {
    return created;
  }
  public void setCreated(Date created) {
    this.created = created;
  }
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

//  public List<Action> getActions() {
//    return actions;
//  }
//  public void setActions(List<Action> actions) {
//    this.actions = actions;
//  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
