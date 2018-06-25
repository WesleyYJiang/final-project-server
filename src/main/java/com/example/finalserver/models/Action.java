package com.example.finalserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Action {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
//
//  @ManyToOne
//  @JsonIgnore
//  private Campaign campaign;

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
//  public Campaign getCampaign() {
//    return campaign;
//  }
//  public void setCampaign(Campaign campaign) {
//    this.campaign = campaign;
//  }

}