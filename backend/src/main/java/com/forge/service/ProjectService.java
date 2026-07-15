package com.forge.service;

import com.forge.dto.ApiResponse;
import com.forge.dto.CreateProjectRequest;
import com.forge.entity.Project;
import com.forge.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import com.forge.exception.ResourceNotFoundException;
import com.forge.repository.UserRepository;
import java.util.List;
import com.forge.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
private final UserRepository userRepository;
    public ProjectService(
        ProjectRepository projectRepository,
        UserRepository userRepository) {

    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
}

    public ApiResponse<String> createProject(CreateProjectRequest request) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    Project project = new Project(
            request.name(),
            request.repositoryUrl(),
            user
    );

    projectRepository.save(project);

    return new ApiResponse<>(
            true,
            "Project created successfully.",
            request.name()
    );
}

    public List<Project> getAllProjects() {

    String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    return projectRepository.findByUser(user);
}
    public Project getProjectById(Long id) {

    return projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
}
public void deleteProject(Long id) {
    projectRepository.deleteById(id);
}
public Project updateProject(Long id, CreateProjectRequest request) {

    Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

    project.setName(request.name());
    project.setRepositoryUrl(request.repositoryUrl());

    return projectRepository.save(project);
}
}