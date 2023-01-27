package com.example.demo.backend_todolist.dto;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @Data : @Getter, @Setter, @ToString, @EqualAndHashCode, 
 * 		   @RequiredArgusConstructor 을 합쳐 놓은 어노테이션이다.
 * 
 * 일반적으로 어노테이션은 필요한거만 따로 사용하는 것을 권장하고 있다.
 */

@Component //특정한 의미가 없는 class 는 @Component 를 선언해줘야 한다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Data -- 위에 설정해 놓은 어노테이션 어노테이션과 겹치므로 주석처리
public class TodoDTO {

	private int id;
	private int completed;
	private String todoname;
}
