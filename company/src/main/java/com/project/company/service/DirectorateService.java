package com.project.company.service;
import com.project.company.entity.Directorate;
import com.project.company.repository.DirectorateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectorateService.class);

    @Autowired
    private DirectorateRepository directorateRepo;

    public Directorate getDirectorateById(Long id) {
        LOGGER.debug("Searching directorate with id: " + id);
        Optional<Directorate> directorateRepoById = directorateRepo.findById(id);

        if(directorateRepoById.isEmpty()) {
            LOGGER.error("Directorate with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Directorate with id: " + id + " was found successfully.");
        return directorateRepoById.get();
    }

    public List<Directorate> getAllDirectorates() {
        LOGGER.warn("Searching all directorates...");
        ArrayList<Directorate> directorates = new ArrayList<>(directorateRepo.findAll());
        LOGGER.warn(directorates.size() + " directorates was found.");
        return directorates;
    }

    public Directorate addDirectorate(Directorate directorate) {
        LOGGER.warn("Adding new directorates to repository: " + directorate.getName());
        directorateRepo.save(directorate);
        LOGGER.info(directorate.getName() + " was saved successfully.");
        return directorate;
    }

    public Directorate updateDirectorate(Long id, Directorate directorate) {
        LOGGER.warn("Updating directorate: " + directorate.getName());
        Optional<Directorate> optionalDirectorate = directorateRepo.findById(id);
        if(optionalDirectorate.isEmpty()) {
            LOGGER.error("Directorate with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        Directorate updatedDirectorate = directorateRepo.getById(id);
        updatedDirectorate.setDirectorateChief(directorate.getDirectorateChief());
        updatedDirectorate.setDepartments(directorate.getDepartments());
        LOGGER.info(directorate.getName() + " was updated successfully.");
        return directorateRepo.save(updatedDirectorate);
    }

    public void deleteDirectorate(Long id) {
        Optional<Directorate> optionalDirectorate = directorateRepo.findById(id);
        if(optionalDirectorate.isEmpty()) {
            LOGGER.error("Directorate with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Directorate with id: " + id + " was deleted successfully.");
        directorateRepo.deleteById(id);
    }
}
