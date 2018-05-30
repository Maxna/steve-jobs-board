package models;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class JobOpeningTest {
    @Test
    public void newJobOpening_InstantiatesCorrectly() throws Exception {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals(true, jobOpening instanceof JobOpening);
    }

    @Test
    public void getTitle_CorrectlyGetsTitle() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals("QA Manager", jobOpening.getTitle());
    }

    @Test
    public void getDescription_CorrectlyGetsDescription() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals("Manages Quality Assurance", jobOpening.getDescription());
    }

    @Test
    public void getContact_CorrectlyGetsContact() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals("Tyler Durden", jobOpening.getContact());
    }

    @Test
    public void getAll_CorrectlyGetsAll() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        JobOpening job = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertTrue(JobOpening.getAll().contains(job));
        assertTrue(JobOpening.getAll().contains(jobOpening));
    }

    @Test
    public void getPosted_CorrectlyGetsTimeOfPost() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals(LocalDateTime.now().getDayOfWeek(), jobOpening.getPosted().getDayOfWeek());
    }

    @Test
    public void getID_jobOpeningInstantiatesWithAnId() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals(1, jobOpening.getId());
    }

    @Test
    public void findById_returnsCorrectJobId() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals(1, JobOpening.findById(jobOpening.getId()).getId());
    }

    @Test
    public void findReturnsCorrectJobWhenMoreThanOneJobExists() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        JobOpening job2 = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        assertEquals(2, JobOpening.findById(job2.getId()).getId());
    }

    @Test
    public void updateChangesJobProperties() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        String formerTitle = jobOpening.getTitle();
        String formerDescription = jobOpening.getDescription();
        String formerContact = jobOpening.getContact();
        LocalDateTime formerDate = jobOpening.getPosted();
        int formerId = jobOpening.getId();

        jobOpening.update("Java Developer", "Develops in Java", "JavaMcJavaFace");

        assertEquals(formerId, jobOpening.getId());
        assertEquals(formerDate, jobOpening.getPosted());
        assertNotEquals(formerTitle, jobOpening.getTitle());
        assertNotEquals(formerDescription, jobOpening.getDescription());
        assertNotEquals(formerContact, jobOpening.getContact());
    }

    @Test
    public void deleteJob_deletesCorrectJobPost() {
        JobOpening jobOpening = new JobOpening("QA Manager", "Manages Quality Assurance", "Tyler Durden");
        JobOpening jobs2 = new JobOpening("Java Developer", "Develops in Java", "JavaMcJavaFace");
        jobOpening.deleteJob();
        assertEquals(1, JobOpening.getAll().size());
        assertEquals(JobOpening.getAll().get(0).getId(), 2);
    }
}
