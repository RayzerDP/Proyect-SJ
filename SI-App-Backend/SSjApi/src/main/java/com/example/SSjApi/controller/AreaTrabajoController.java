package com.example.SSjApi.controller;

import com.example.SSjApi.entity.AreaTrabajo;
import com.example.SSjApi.repository.AreaTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/areasTrabajo")
public class AreaTrabajoController {

    @Autowired
    private AreaTrabajoRepository areaTrabajoRepository;

    @GetMapping
    public List<AreaTrabajo> getAllAreasTrabajo() {
        return areaTrabajoRepository.findAll();
    }
}
