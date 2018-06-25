package com.example.finalserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.List;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String role;
  private String bio;
  private String education;
  private String profession;
  private String reputation;

//  @ManyToMany(fetch = FetchType.LAZY,
//          cascade = {
//                  CascadeType.PERSIST,
//                  CascadeType.MERGE
//          })
//  @JoinTable(name = "user_campaign",
//          joinColumns = { @JoinColumn(name = "user_id") },
//          inverseJoinColumns = { @JoinColumn(name = "campaign_id") })
  @ManyToMany
  @JoinTable(name = "user_campaign",
          joinColumns = { @JoinColumn(name = "fk_user") },
          inverseJoinColumns = { @JoinColumn(name = "fk_campaign") })
  private List<Campaign> campaigns;

//  public List<Campaign> getCampaigns() {
//    return campaigns;
//  }
//
//  public void setCampaigns(List<Campaign> campaigns) {
//    this.campaigns = campaigns;
//  }
//
//  public void addCampaign(Campaign campaign) {
//    campaigns.add(campaign);
//    campaign.getUsers().add(this);
//  }
//
//  public void removeCampaign(Campaign campaign) {
//    campaigns.remove(campaign);
//    campaign.getUsers().remove(this);
//  }


  public void setEmail(String email) {
    this.email = email;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }

  public String getBio() {
    return bio;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getReputation() {
    return reputation;
  }

  public void setReputation(String reputation) {
    this.reputation = reputation;
  }
}
