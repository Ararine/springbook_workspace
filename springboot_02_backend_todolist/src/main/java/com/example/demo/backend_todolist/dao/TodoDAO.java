package com.example.demo.backend_todolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.backend_todolist.dto.TodoDTO;

@Mapper //todoMapper.xml과 자동으로 연결시켜주는 어노테이션
@Repository //dao 부분을 선언하는 어노테이션 //sqlSessionTemplete 과 같은 설정을 대신해준다.
public interface TodoDAO {

	public List<TodoDTO> getTodoList() throws Exception;
	
	public int insertTodoList(TodoDTO dto) throws Exception;
	
	public int updateTodoList(TodoDTO dto) throws Exception;
	
	public int deleteTodoList(int id) throws Exception;
}
