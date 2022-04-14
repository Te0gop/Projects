package com.project.company.controller;

import com.project.company.entity.Directorate;
import com.project.company.service.DirectorateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directorates")
public class DirectorateController {

    @Autowired
    private DirectorateService directorateService;

    @GetMapping("/{id}")
    public Directorate getDirectorateById(@PathVariable("id") Long id) {
        return directorateService.getDirectorateById(id);
    }

    @GetMapping
    public List<Directorate> getAllDirectorates() {
        return directorateService.getAllDirectorates();
    }

    @PostMapping
    public ResponseEntity<Directorate> addDirectorate(@RequestBody Directorate directorate) {
        return new ResponseEntity<>(directorateService.addDirectorate(directorate), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directorate> updateDirectorate(@PathVariable("id") Long id, @RequestBody Directorate directorate) {
        return new ResponseEntity<>(directorateService.updateDirectorate(id, directorate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDirectorate(@PathVariable("id") Long id) {
        directorateService.deleteDirectorate(id);
    }
}
