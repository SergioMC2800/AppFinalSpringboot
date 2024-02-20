package org.jrotero.service;

import java.util.List;
import java.util.Optional;

import org.jrotero.model.TaskEntity;
import org.jrotero.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService{

	@Autowired
	private ITaskRepository repository;
	
	@Override
	public List<TaskEntity> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Optional<TaskEntity> findById(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public TaskEntity create(TaskEntity task) {
		var taskResponse = new TaskEntity();
		/*taskResponse.setId(task.getId());
		taskResponse.setCompleted(task.getCompleted());
		taskResponse.setDescript(task.getDescript());
		taskResponse.setDueDate(task.getDueDate());
		taskResponse.setName(task.getName());
		taskResponse.setTag(task.getTag());*/
		return repository.save(task);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public TaskEntity update(TaskEntity task, Long id) throws Exception {
		if(repository.findById(id) != null) {
			var response = task;
			response.setId(id);			
			return repository.save(response);
		}
		return null;
	}

}
