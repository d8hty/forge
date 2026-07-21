package com.forge.controller;

import com.forge.dto.ApiResponse;
import com.forge.dto.CreateProjectRequest;
import com.forge.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.forge.entity.Project;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping("/api/v1/projects")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController  {

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
@GetMapping("/{id}")
public Project getProjectById(@PathVariable Long id) {

    return projectService.getProjectById(id);
}
@DeleteMapping("/{id}")
public void deleteProject(@PathVariable Long id) {
    projectService.deleteProject(id);
}
@PutMapping("/{id}")
public Project updateProject(
        @PathVariable Long id,
        @RequestBody CreateProjectRequest request) {

    return projectService.updateProject(id, request);
}
}