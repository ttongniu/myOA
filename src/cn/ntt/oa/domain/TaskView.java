package cn.ntt.oa.domain;

import org.jbpm.api.task.Task;

public class TaskView {
	private Task task;
	private Application application;

	public TaskView() {
		super();
	}

	public TaskView(Task task, Application application) {
		super();
		this.task = task;
		this.application = application;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
