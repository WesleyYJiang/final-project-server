package com.example.finalserver.services;


import com.example.finalserver.models.Campaign;
import com.example.finalserver.repositories.CampaignRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CampaignService {
  @Autowired
  CampaignRepository campaignRepository;

  @GetMapping("/api/campaign")
  public Iterable<Campaign> findAllCampaigns() {
    return campaignRepository.findAll();
  }

  @GetMapping("/api/campaign/{campaignId}")
  public Campaign findCampaignById(@PathVariable("campaignId") int campaignId) {
    Optional<Campaign> data = campaignRepository.findById(campaignId);
    return data.orElse(null);
  }

  @PostMapping("/api/campaign")
  public Campaign createCampaign(@RequestBody Campaign campaign) {
    return campaignRepository.save(campaign);
  }

  @DeleteMapping("/api/campaign/{campaignId}")
  public void deleteCampaign(@PathVariable("campaignId") int id) {
    campaignRepository.deleteById(id);
  }

  @PutMapping("/api/campaign/{campaignId}")
  public Campaign updateCampaign(@PathVariable("campaignId") int campaignId, @RequestBody Campaign newCampaign)
          throws Exception  {
    Optional<Campaign> data = campaignRepository.findById(campaignId);
    if (data.isPresent()) {
      Campaign campaign = data.get();
      if (newCampaign.getTitle() != null){ campaign.setTitle(newCampaign.getTitle());}
      if (newCampaign.getCreated() != null){
        campaign.setCreated(newCampaign.getCreated());}
      campaignRepository.save(campaign);
      return campaign;
    }
    else{
      throw new Exception("bad");
    }
  }
}
