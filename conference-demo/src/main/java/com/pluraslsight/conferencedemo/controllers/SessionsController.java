package com.pluraslsight.conferencedemo.controllers;

import com.pluraslsight.conferencedemo.models.Speaker;
import com.pluraslsight.conferencedemo.repositories.SessionRepository;
import com.pluraslsight.conferencedemo.models.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionsRepository;


    @GetMapping
    public List<Session> list(){
        return sessionsRepository.findAll();
    }

    @GetMapping
    @RequestMapping(value = "{id}")
    public Session get(@PathVariable Long id){
        return sessionsRepository.getOne(id);
    }


    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionsRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionsRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionsRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionsRepository.saveAndFlush(existingSession);
    }

}
