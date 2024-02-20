package org.jrotero.controller;

import java.util.List;

import org.jrotero.model.TaskEntity;
import org.jrotero.service.ITaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

@RestController
public class TaskController {

	private ITaskService service;

	public TaskController(ITaskService service) {
		this.service = service;
	}

	// Get
	@GetMapping("/get-all")
	public ResponseEntity<List<TaskEntity>> findAll() {
		List<TaskEntity> tasks = this.service.findAll();
		if (tasks.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/get-id/{id}")
	public ResponseEntity<TaskEntity> findById(Long id) {
		TaskEntity task = service.findById(id).orElseGet(null);
		if (task == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(task);
		}
	}

	//Post
	@PostMapping("/create")
	public ResponseEntity<TaskEntity> create(@RequestBody TaskEntity task) throws Exception {
		service.create(task);
		return ResponseEntity.ok(task);
	}

	//Post
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskEntity> update(@RequestBody TaskEntity task, @PathVariable("id") Long id) throws Exception {
		service.update(task, id);
		return ResponseEntity.ok(task);
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TaskEntity> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
