package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class JobOpening {
    private String title;
    private String description;
    private String contact;
    private static ArrayList<JobOpening> jobList = new ArrayList<>();
    private LocalDateTime posted;
    private int id;

    public JobOpening(String title, String description, String contact) {
        this.title = title;
        this.description = description;
        this.contact = contact;
        jobList.add(this);
        this.posted = LocalDateTime.now();
        this.id = jobList.size();
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getContact() {
        return contact;
    }


    public static ArrayList<JobOpening> getAll() {
        return jobList;
    }

    public LocalDateTime getPosted() {
        return posted;
    }

    public int getId() {
        return id;
    }

    public static JobOpening findById(int id) {
        return jobList.get(id-1);
    }

    public void update(String title, String description, String contact) {
        this.title = title;
        this.description = description;
        this.contact = contact;
    }

    public void deleteJob() {
        jobList.remove(id-1);
    }


}
