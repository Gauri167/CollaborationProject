package com.blog.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.JobDAO;
import com.blog.domain.Job;

@RestController
public class JobController {
	
	@Autowired
	JobDAO jobDAO;
	
	@GetMapping(value="/jobdemo")
	public ResponseEntity<String> demoPurpose(){
		
		return new ResponseEntity<>("Demo Data",HttpStatus.OK);
	}

	@PostMapping(value="/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job)
	{System.out.println("line 28 of addJob");
	System.out.println(jobDAO.addJob(job));
		if(jobDAO.addJob(job)) {
			System.out.println("line 30 of addJob");
			return new ResponseEntity<String>("Successfully added Job",HttpStatus.OK);}
		else
			return new ResponseEntity<String>("Failed to add Job",HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping(value="/updateJob/{jobId}")
	public ResponseEntity<String> updateJob(@PathVariable("jobId") int jobId,@RequestBody Job job)
	{
		Job mJob=jobDAO.getJob(jobId);
		if(mJob==null)
			return new ResponseEntity<String>("Failed to update job",HttpStatus.NOT_MODIFIED);
		jobDAO.updateJob(mJob);
		return new ResponseEntity<String>("Successfully Updated Job",HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId)
	{
		if(jobDAO.deleteJob(jobId))
             return new ResponseEntity<String>("Successfully deleted job",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failed to delete job",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/showJob/{jobId}")
	public ResponseEntity<Job> showJob(@PathVariable("jobId") int jobId)
	{
		Job job=jobDAO.getJob(jobId);
		if(job==null)
			return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@GetMapping(value="/showAllJobs")
	public ResponseEntity<List<Job>> showAllJobs()
	{
		List<Job> list=jobDAO.getAllJobs();
		if(list.size()>0)
			return new ResponseEntity<List<Job>>(list,HttpStatus.OK);
		else
			return new ResponseEntity<List<Job>>(list,HttpStatus.NOT_FOUND);
	}
}
