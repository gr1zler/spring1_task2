package com.example.bitlab_spring1_task2.controller;

import com.example.bitlab_spring1_task2.DBconnection.ApplicationRequest;
import com.example.bitlab_spring1_task2.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ApplicationRepository repository;

    @GetMapping(value = "/")
    public String MainPage(Model model){
        List<ApplicationRequest> requestList=repository.findAll();
        model.addAttribute("Type","Все заявки");
        model.addAttribute("requestList",requestList);
        return "MainPage";
    }
    @GetMapping(value = "/new_applications")
    public String New_applications(Model model){
        List<ApplicationRequest> requestList=  repository.findByHandled(false);
        model.addAttribute("Type","Новые заявки");
        model.addAttribute("requestList",requestList);
        return "MainPage";
    }
    @GetMapping(value = "/worked_out_applications")
    public String Worked_out_applications(Model model){
        model.addAttribute("Type","Отработанные заявки");
        List<ApplicationRequest> requestList=  repository.findByHandled(true);
        model.addAttribute("requestList",requestList);
        return "MainPage";
    }
    @GetMapping(value = "/add_application")
    public String add_application(){
        return "add_application";
    }

    @PostMapping(value = "/add_application")
    public String add_application(ApplicationRequest applicationRequest){
        repository.save(applicationRequest);
        return "redirect:/";
    }
    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id, Model model){
        ApplicationRequest request=repository.findById(id).orElse(null);
        model.addAttribute("app",request);
        return "details";
    }
    @PostMapping(value = "/handle_app")
    public String handle_app(ApplicationRequest request){
        request.setHandled(true);
        repository.save(request);
        return "redirect:/";
    }
    @PostMapping(value = "/delete")
    public String delete(@RequestParam(name = "id") Long id){
        repository.deleteById(id);
        System.out.println(id);
        return "redirect:/";
    }







}
