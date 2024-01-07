package com.syh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.syh.entity.SimpleBoard;

@Mapper
public interface SimpleBoardMapper {
//	public List<SimpleBoard> getAllBoards();
//	public void simpleBoardInsert(SimpleBoard board);
//	public SimpleBoard simpleBoardContent(int id);
//	public void simpleBoardCount(int id);
//	public void simpleBoardUpdate(SimpleBoard board);
//	public void simpleBoardDelete(int id);
	
	@Select("select * from simpleBoard")
	public List<SimpleBoard> getAllBoards();
	
	@Insert("insert into simpleBoard(title,content,writer) values(#{title},#{content},#{writer})")
	public void simpleBoardInsert(SimpleBoard board);
	
	@Select("select * from simpleBoard where id=#{id}")
	public SimpleBoard simpleBoardContent(int id);
	
	@Update("update simpleBoard set count=count+1 where id=#{id}")
	public void simpleBoardCount(int id);
	
	@Update("update simpleBoard set title=#{title},content=#{content} where id=#{id}")
	public void simpleBoardUpdate(SimpleBoard board);
	
	@Delete("delete from simpleBoard where id=#{id}")
	public void simpleBoardDelete(int id);
}
