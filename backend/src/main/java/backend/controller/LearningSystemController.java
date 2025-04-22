package backend.controller;

import backend.exception.LearningSystemNotFoundException;
import backend.exception.UserNotFoundException;
import backend.model.Comment;
import backend.model.LearningSystemModel;
import backend.repository.LearningSystemRepository;
import backend.repository.NotificationRepository;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
public class LearningSystemController {
    @Autowired
    private LearningSystemRepository learningSystemRepository;

    @PostMapping("/learningSystem")
    public LearningSystemModel newLearningSystemModel(@RequestBody LearningSystemModel newLearningSystemModel) {
        return learningSystemRepository.save(newLearningSystemModel);
    }

    @GetMapping("/learningSystem")
    List<LearningSystemModel> getAll() {
        return learningSystemRepository.findAll();
    }

    @PutMapping("/learningSystem/{id}")
    LearningSystemModel update(@RequestBody LearningSystemModel newLearningSystemModel, @PathVariable String id) {
        return learningSystemRepository.findById(id)
                .map(learningSystemModel -> {
                    (newLearningSystemModel.getEndDate());
                    LearningSystemModel.setLevel(newLearningSystemModel.getLevel());
                    return learningSystemRepository.save(learningSystemModel);
                }).orElseThrow(() -> new LearningSystemNotFoundException(id));
    }

    @DeleteMapping("/learningSystem/{id}")
    public void delete(@PathVariable String id) {
        learningSystemRepository.deleteById(id);
    }
}
