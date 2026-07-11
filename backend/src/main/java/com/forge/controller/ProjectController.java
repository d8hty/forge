package com.forge.controller;

import com.forge.dto.ApiResponse;
import com.forge.dto.CreateProjectRequest;
import com.forge.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.forge.entity.Project;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ApiResponse<String> createProject(
            @Valid @RequestBody CreateProjectRequest request) {

        return projectService.createProject(request);
    }
    @GetMapping
public List<Project> getAllProjects() {
    return projectService.getAllProjects();
}
}