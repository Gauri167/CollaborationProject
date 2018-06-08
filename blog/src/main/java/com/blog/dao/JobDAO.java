package com.blog.dao;

import java.util.List;

import com.blog.domain.Job;

public interface JobDAO {

	public boolean addJob(Job job);
	public boolean updateJob(Job job);
	public boolean deleteJob(int id);
	public Job getJob(int id);
	public List<Job> getAllJobs();
}
