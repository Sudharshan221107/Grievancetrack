package com.example.grievance.track.controller;

import com.example.grievance.track.entity.Grievance;
import com.example.grievance.track.service.GrievanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grievances")
public class GrievanceController {

    private final GrievanceService service;

    public GrievanceController(
            GrievanceService service) {

        this.service = service;
    }

    @PostMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Grievance create(
            @PathVariable Long categoryId,
            @Valid @RequestBody Grievance grievance) {

        return service.create(
                grievance,
                categoryId
        );
    }

    @GetMapping
    public List<Grievance> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Grievance getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PutMapping("/{id}/status")
    public Grievance updateStatus(
            @PathVariable Long id,
            @RequestParam Grievance.Status status) {

        return service.updateStatus(
                id,
                status
        );
    }

    @PutMapping("/{id}/rating")
    public Grievance rate(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> request) {

        Integer rating = request.get("rating");

        return service.rate(
                id,
                rating
        );
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        service.delete(id);

        return "Grievance deleted successfully";
    }
}