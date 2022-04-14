package com.project.company.service;
import com.project.company.entity.Directorate;
import com.project.company.repository.DirectorateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorateService {

    @Autowired
    private DirectorateRepository directorateRepo;

    public Directorate getDirectorateById(Long id) {
        Optional<Directorate> directorateRepoById = directorateRepo.findById(id);

        if(directorateRepoById.isEmpty()) {
            throw new IllegalStateException();
        }

        return directorateRepoById.get();
    }

    public List<Directorate> getAllDirectorates() {
        return new ArrayList<>(directorateRepo.findAll());
    }

    public Directorate addDirectorate(Directorate directorate) {
        return directorateRepo.save(directorate);
    }

    public Directorate updateDirectorate(Long id, Directorate directorate) {
        Optional<Directorate> optionalDirectorate = directorateRepo.findById(id);
        if(optionalDirectorate.isEmpty()) {
            throw new IllegalStateException();
        }
        Directorate updatedDirectorate = directorateRepo.getById(id);
        updatedDirectorate.setDirectorateChief(directorate.getDirectorateChief());
        updatedDirectorate.setDepartments(directorate.getDepartments());
        return directorateRepo.save(updatedDirectorate);
    }

    public void deleteDirectorate(Long id) {
        Optional<Directorate> optionalDirectorate = directorateRepo.findById(id);
        if(optionalDirectorate.isEmpty()) {
            throw new IllegalStateException();
        }
        directorateRepo.deleteById(id);
    }
}
