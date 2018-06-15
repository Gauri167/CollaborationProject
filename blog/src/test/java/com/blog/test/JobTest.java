package com.blog.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.blog.dao.JobDAO;
import com.blog.domain.Job;

public class JobTest {
	
	@Autowired
	private static JobDAO jobDAO;
	@Autowired
	private static AnnotationConfigApplicationContext context;
	@Autowired
	private static Job job=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.blog");
		context.refresh();
		jobDAO=(JobDAO) context.getBean("jobDAO");
	}

	@Test
	public void testAddJob() {
		job=new Job();
		SimpleDateFormat textFormat = new SimpleDateFormat("dd-MM-yyyy");
		job.setCompany("Niit");
		job.setJobDesignation("Tech Mentor");
		job.setJobDescription("Should have knowledege of JAVA");
		job.setLocation("Lucknow");
		job.setSalary(100000);
		try {
			job.setApplyLastDate(textFormat.parse("12-08-2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean result=jobDAO.addJob(job);
		assertTrue("Job Add Test Case",result);
	}
    @Ignore
	@Test
	public void testUpdateJob() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testDeleteJob() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetJob() {
		fail("Not yet implemented");
	}
    @Ignore
	@Test
	public void testGetAllJobs() {
		fail("Not yet implemented");
	}

}
