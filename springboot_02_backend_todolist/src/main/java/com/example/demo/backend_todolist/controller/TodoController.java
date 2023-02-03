package com.example.demo.backend_todolist.controller;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.backend_todolist.dto.TodoDTO;
import com.example.demo.backend_todolist.service.TodoService;

//http://localhost:8090/todo/all

//@RestController = @Controller + @ResponseBody

@CrossOrigin("*") //포트번호에 관계없이 응답을 허가해주는 어노테이션
//@CrossOrigin(origins = {"http://localhost:3000"}) //특정 url에만 허가하는 방법
@RestController
//@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	public TodoController() {
		System.out.println("controller");
	}//end TodoController()
	
	//http://localhost:8090/todo/all
//	@ResponseBody
	@GetMapping("/todo/all")
	public List<TodoDTO> getList() throws Exception{
		System.out.println("all"); 
		return todoService.search();
	}//end getList()
	
	//http://loacalhost:8090/todo
	@PostMapping("/todo")
	public ResponseEntity<Object> postTodo(@RequestBody TodoDTO dto) throws Exception {
		System.out.println(dto.getTodoname());
		int chk = todoService.insert(dto);
		
		//header 부분에 들어갈 정보 설정
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		//body 부분에 들어갈 정보 설정
		HashMap<String, String> map = new HashMap<>();
		map.put("createDate", new Date().toString());
		map.put("message", "insert ok");
		
		if(chk>=1) {
			//body, headers, status
//			return new ResponseEntity<Object>(HttpStatus.OK);
//			return new ResponseEntity<Object>(header, HttpStatus.OK);
//			return new ResponseEntity<Object>(map, HttpStatus.OK);
			return new ResponseEntity<Object>(map, header, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
	}//end postTodo()
	
	//todo?id=1&completed=0 RequestParam
	@PutMapping("/todo/{id}/{completed}") //PathVariable
	public void putTodo(@PathVariable("id") int id, @PathVariable("completed") int completed) throws Exception{
		System.out.printf("id=%d, completed=%d\n", id, completed);
		TodoDTO dto = new TodoDTO();
		dto.setId(id);
		dto.setCompleted(completed==0 ? 1 : 0);
		todoService.update(dto);
	}//end putTodo()
	
	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable("id") int id) throws Exception {
		System.out.println("id:"+id);
		todoService.delete(id);
	}//end deleteTodo()
}//end class
