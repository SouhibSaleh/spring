package com.example.dolist.Controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dolist.Components.Task;
import com.example.dolist.Services.RequestService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
   
	private final RequestService req;
	private List<Task>temp = new ArrayList<>();
	
	@Autowired
	public TaskController(RequestService req) 
	{
		super();
		this.req = req;
	}

	@PostMapping(path="/Main")
	public String sendReq(
			@RequestParam String data,
			@RequestParam String type,
			@RequestParam String choose,
			@RequestParam String sdate,
			@RequestParam String edate,
			@RequestParam String order,
			@RequestParam String box,
		    Model model)
	{  
		 model.addAttribute("note","");
		 
		if(type.equals("Update Status"))
		{
			req.UpdateCheck(temp,box);
			
		}
		
	    if(type.equals("Delete")) {
		       model.addAttribute("note",
		       req.deleteTasks(temp)+
		       " task(s) has been deleted successfully");
	        return "Main.html";
	    }
	    
	    
	     if(type.equals("Add"))
	    {
	   
	    	if(data.equals("")||edate.equals(""))
	    	{
	    		return "Main.html";
	    	}
	     	int id = req.getTasksSize()+1;
	    	String task = data;
   
			try {
		        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			    java.util.Date parsedate =  format.parse(edate);
		        java.sql.Date Mydate = new java.sql.Date(parsedate.getTime());

		        req.addTask(new Task(id,task,Mydate,false));
		        model.addAttribute("note",
		 		       "task has been Added successfully");
			} catch (ParseException e) {

				e.printStackTrace();
			}
	    	
    		return "Main.html";

	    }
	       
	     if(type.equals("Show All"))
	    {	
		   temp = req.getAll();
	       model.addAttribute("tasks",temp);
	    }
	 
	     if(type.equals("Search")) 
		{
            
		   if(sdate.equals(""))
			    sdate = "1980-01-01";
		   if(edate.equals(""))
			    edate = java.time.LocalDate.now().toString();
		   if(order.equals(""))
			    order = "A"; 
		   if(choose.equals("description")&&!data.equals(""))
		   {
			   temp = req.getByDesc(temp, data);
		   }
		   if(choose.equals("id")&&!data.equals(""))
		   {
			      
			     int i = Integer.parseInt(data);
			     temp = req.getById(temp, i);
			     if(temp == null)
			    	 return "Main.html";
		   }
		   temp = req.Between(temp, sdate, edate);
           temp = req.SortTasks(temp, order);
           
		   if(type.equals("Search"))
				model.addAttribute("tasks",temp);   
		}
		
		 return "Main.html";
	}
	

	@GetMapping("/Main")
	public String getPage()
	{
		return "Main.html";
	}
	
}
