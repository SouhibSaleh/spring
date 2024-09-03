package com.example.dolist.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dolist.Components.Task;
import com.example.dolist.Repositories.TasksRepository;

@Service
public class RequestService {
	
    private final TasksRepository TaskRep;
     
    
    @Autowired   
   	public RequestService(TasksRepository taskRep) {
		super();
		TaskRep = taskRep;
	}
    
    public List<Task>getAll()
    {
    	return TaskRep.getAll();
    }

    public List<Task>Between(List<Task>temp,String sdate,String edate)
    {
    	List<Task>temp1 = temp;
    	List<Task>temp2 = new ArrayList<>();
    	
    	for(int x=0;x<temp1.size();x++)
    	{
    		String date = temp1.get(x).getDate().toString();

    		if(date.compareTo(sdate)>=0&&edate.compareTo(date)>=0)
    		{
              	temp2.add(temp1.get(x));		
    		}
    	}
    	return temp2;
    }
    
    public List<Task>SortTasks(List<Task>temp,String type)
    {
    
    	
    	if(type.equals("A"))
    	{
    		  temp.sort((Task a, Task b)->
        	  a.getDate().toString().compareTo(
        			  b.getDate().toString()));
    	 
    	}
    	else if(type.equals("D")) 
    	{
    		  temp.sort((Task a, Task b)->
        	  b.getDate().toString().compareTo(
        			  a.getDate().toString()));
    	}
       return temp;
    }
    
    public List<Task>getByDesc(List<Task>temp,String keyword)
    {
    	List<Task>templ = new ArrayList<>();
    	for(int x=0;x<temp.size();x++)
    	{
    		String ts = temp.get(x).getDescription();
    		ts = ts.toLowerCase();
    		keyword = keyword.toLowerCase();
    		if(ts.contains(keyword))
    			templ.add(temp.get(x));
    			
    	}
    	return templ;
    }
    
    public List<Task>getById(List<Task>temp,int id)
    {
           for(int x=0;x<temp.size();x++)
           {
        	   if(temp.get(x).getId()==id)
        	   {
        		   return List.of(temp.get(x));
        	   }
           }
           return null;
    }

    public int deleteTasks(List<Task>temp)
    {
    	for(int x=0;x<temp.size();x++)
    	{
    	   TaskRep.delete(temp.get(x));
    	}
    	return temp.size();
    }
    
    public int getTasksSize()
    {
    	return TaskRep.getAll().size();
    }
    
    public void addTask(Task temp)
    {
    	TaskRep.add(temp);
    }
    public void UpdateCheck(List<Task>temp,String s)
    {
    	 String []args = s.split(",");
         List<Integer>arr = new ArrayList<>();
         for(int x=1;x<args.length;x++)
         {
        	 Integer t = Integer.parseInt(args[x]);
        	 arr.add(t);
    	 }
         for(int x=0;x<temp.size();x++)
         {
        	 Integer t = temp.get(x).getId();
        	 if(arr.lastIndexOf(t)!=-1)
        	 {
        		 TaskRep.updateCheckbox(temp.get(x),true);
        	 }
        	 else  TaskRep.updateCheckbox(temp.get(x),false);
         }
    }
    
      
}
