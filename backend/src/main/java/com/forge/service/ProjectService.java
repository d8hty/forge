package com.forge.service;

import com.forge.dto.ApiResponse;
import com.forge.dto.CreateProjectRequest;
import com.forge.entity.Project;
import com.forge.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ApiResponse<String> createProject(CreateProjectRequest request) {

        Project project = new Project(
                request.name(),
                request.repositoryUrl()
        );

        projectRepository.save(project);

        return new ApiResponse<>(
                true,
                "Project created successfully.",
                request.name()
        );
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}