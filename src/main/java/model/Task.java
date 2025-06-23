package model;

public class Task {
    public int id;
    public int accountId;
    public String title;
    public String dateAssigned;
    public boolean isCompleted;

    public Task(int id, int accountId, String title, String dateAssigned, boolean isCompleted) {
        this.id = id;
        this.accountId = accountId;
        this.title = title;
        this.dateAssigned = dateAssigned;
        this.isCompleted = isCompleted;
    }
}
