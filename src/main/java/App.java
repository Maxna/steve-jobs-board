import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.JobOpening;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/jobs/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "job-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<JobOpening> jobs = JobOpening.getAll();
            model.put("jobs", jobs);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        post("/jobs/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            String contact = req.queryParams("contact");
            JobOpening newJob = new JobOpening(title, description, contact);
            model.put("jobs", newJob);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/jobs/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfJobToFind = Integer.parseInt(req.params("id"));
            JobOpening foundJob = JobOpening.findById(idOfJobToFind);
            model.put("jobs", foundJob);
            return new ModelAndView(model, "job-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("jobs/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfJobToEdit = Integer.parseInt(req.params("id"));
            JobOpening editJob = JobOpening.findById(idOfJobToEdit);
            model.put("editJob", editJob);
            return new ModelAndView(model, "job-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/jobs/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newTitle = req.queryParams("title");
            String newDescription = req.queryParams("description");
            String newContact = req.queryParams("contact");
            int idOfJobToEdit = Integer.parseInt(req.params("id"));
            JobOpening editJob = JobOpening.findById(idOfJobToEdit);
            editJob.update(newTitle, newDescription, newContact);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/jobs/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfJobToDelete = Integer.parseInt(req.params("id"));
            JobOpening deleteJob = JobOpening.findById(idOfJobToDelete);
            deleteJob.deleteJob();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
