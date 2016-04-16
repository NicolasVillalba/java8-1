package bq.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import bq.java8.stream.Task.Status;

public class Stream2 {

	private List<Task> tasks = new ArrayList<>();
	
	@Before
	public void testData(){
		tasks.add(new Task(8,Task.Status.OPEN));
		tasks.add(new Task(18, Task.Status.OPEN));
		tasks.add(new Task(88, Task.Status.CLOSE));
		
		System.out.println();
	}
	
	@Test
	public void testSum(){
		System.out.println("Test Sum:");
		
		// way 1: use method reference in map method "Task::getPoints"
		int sum = tasks.stream().filter(task->task.status==Task.Status.OPEN).mapToInt(Task::getPoints).sum();
		System.out.println("total open points:" + sum);
		
		// way 2: it's equal to "task->task.getPoints()"
		sum = tasks.stream().filter(task->task.status==Task.Status.CLOSE).mapToInt(task->task.getPoints()).sum();
		System.out.println("total close points:" + sum);
	}
	
	@Test
	public void testParallel(){
		System.out.println("Test Parallel:");
		
		Optional<Integer> sum = tasks.stream().parallel().map(Task::getPoints).reduce(Integer::sum);
		System.out.println("total open points:" + sum.orElse(-1));
	}
	
	@Test
	public void testGroup(){
		System.out.println("Test Group");
	
		Map<Status, List<Task>> group = tasks.stream()
			.collect(Collectors.groupingBy(Task::getStatus));
		
		group.forEach(
				(key, value) -> {
					System.out.println("\n" + key + ":"); 
					value.forEach(System.out::print);
				});
		
	}
	
	@Test
	public void testOther(){
		System.out.println("Test avg:");
		
		Integer sum = tasks.stream().parallel().mapToInt(Task::getPoints).sum();
		
		List<String> group = tasks.stream()
			.mapToInt(Task::getPoints)
			.map(value->Double.valueOf(100*value/sum).intValue())
			.mapToObj(value->String.valueOf(value))
//			.asLongStream()
//			.mapToDouble(point->(point/sum))
//			.boxed()
//			.mapToLong(weight->(long)(100*weight))
//			.mapToObj(value->value+"")b
			.collect(Collectors.toList());
		
		group.forEach(System.out::println);
	}
	
	
	
}

class Task{
	enum Status{
		OPEN, CLOSE
	}
	
	Integer points;
	Status status;
	
	public Task(Integer points, Status status) {
		this.points = points;
		this.status = status;
	}

	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[points=" + points + ", status=" + status + "]";
	}
	
}
