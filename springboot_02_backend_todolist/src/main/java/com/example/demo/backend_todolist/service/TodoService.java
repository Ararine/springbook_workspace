package com.example.demo.backend_todolist.service;

import java.util.List;

import com.example.demo.backend_todolist.dto.TodoDTO;

public interface TodoService {

	public List<TodoDTO> search() throws Exception;
	
	public int insert(TodoDTO dto) throws Exception;
	
	public int update(TodoDTO dto) throws Exception;
	
	public int delete(int id) throws Exception;
}
