package com.example.finalserver.services;

import com.example.finalserver.models.Action;
import com.example.finalserver.models.Campaign;
import com.example.finalserver.repositories.ActionRepository;
import com.example.finalserver.repositories.CampaignRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ActionService {
  @Autowired
  CampaignRepository campaignRepository;

  @Autowired
  ActionRepository actionRepository;

  @PostMapping("/api/course/{courseId}/module")
  public Action createModule(
          @PathVariable("courseId") int courseId,
          @RequestBody Action newAction) {
    Optional<Campaign> data = campaignRepository.findById(courseId);

    if(data.isPresent()) {
      Campaign campaign = data.get();
//      newAction.setCampaign(campaign);
      return actionRepository.save(newAction);
    }
    return null;
  }
//
//  @GetMapping("/api/course/{courseId}/module")
//  public List<Action> findAllModulesForCourse(
//          @PathVariable("courseId") int courseId) {
//    Optional<Campaign> data = campaignRepository.findById(courseId);
//    if(data.isPresent()) {
//      Campaign campaign = data.get();
//      return campaign.getActions();
//    }
//    return null;
//  }

  @DeleteMapping("/api/module/{moduleId}")
  public void deleteModule(@PathVariable("moduleId") int moduleId)
  {
    actionRepository.deleteById(moduleId);
  }

  @GetMapping("/api/module")
  public List<Action> findAllModules()
  {
    return (List<Action>) actionRepository.findAll();
  }
}
