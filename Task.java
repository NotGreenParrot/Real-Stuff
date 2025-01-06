
public class Task {

    public String name;
    public int priority;
    public int availableTime;
    public int length;
    public int deadline;

    public Task(String name, int priority, int availableTime, int length, int deadline) {
        this.deadline = deadline;
        this.name = name;
        this.availableTime = availableTime;
        this.length = length;
        this.deadline = deadline;
        this.priority = priority;
    }

}
