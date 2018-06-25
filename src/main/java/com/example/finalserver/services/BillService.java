package com.example.finalserver.services;

import com.example.finalserver.models.Action;
import com.example.finalserver.models.Bill;
import com.example.finalserver.repositories.ActionRepository;
import com.example.finalserver.repositories.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BillService {
  @Autowired
  BillRepository billRepository;
  @Autowired
  ActionRepository actionRepository;

  @GetMapping("/api/Lesson/{lessonId}")
  public Bill findCourseById(@PathVariable("lessonId") int lessonId) {
    Optional<Bill> data = billRepository.findById(lessonId);
    return data.orElse(null);
  }

  @PostMapping("/api/module/{moduleId}/lesson")
  public Bill createLesson(
          @PathVariable("moduleId") int moduleId,
          @RequestBody Bill newBill) {
    Optional<Action> data = actionRepository.findById(moduleId);

    if(data.isPresent()) {
      Action action = data.get();
      return billRepository.save(newBill);
    }
    return null;
  }

//  @GetMapping("/api/module/{moduleId}/lesson")
//  public List<Bill> findAllLessonsForCourse(
//          @PathVariable("moduleId") int moduleId) {
//    Optional<Action> data = actionRepository.findById(moduleId);
//    if(data.isPresent()) {
//      Action action = data.get();
//      return action.getBills();
//    }
//    return null;
//  }

  @DeleteMapping("/api/module/{moduleId}/lesson/{lessonId}")
  public void deleteCourse(@PathVariable("lessonId") int id) {
    billRepository.deleteById(id);
  }

  @PutMapping("/api/module/{moduleId}/lesson/{lessonId}")
  public Bill update(@PathVariable("lessonId") int lessonId, @RequestBody Bill newBill)
          throws Exception  {
    Optional<Bill> data = billRepository.findById(lessonId);
    if (data.isPresent()) {
      Bill bill = data.get();
      bill.setTitle(newBill.getTitle());

      billRepository.save(bill);
      return bill;
    }
    else{
      throw new Exception("bad");
    }
  }

}
