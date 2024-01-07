package com.syh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.syh.entity.SimpleBoard;
import com.syh.mapper.SimpleBoardMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SimpleBoardController {
	
//	// MyBatis 기반 CRUD 
//	@Autowired
//	private SimpleBoardMapper simpleBoardMapper;
//	
//	@GetMapping("/home")
//	public String Home(Model model) {
//		List<SimpleBoard> list = simpleBoardMapper.getAllBoards();
//		model.addAttribute("list",list);
//		
//		return "home";
//	}
//	@GetMapping("/simpleBoardForm")
//	public String boardForm() {
//		return "simpleBoardForm";
//	}
//	
//	@PostMapping("/simpleBoardInsert")
//	public String boardInsert(SimpleBoard vo) {
//		simpleBoardMapper.simpleBoardInsert(vo);
//		return "redirect:/home";
//	}
//	
//	@GetMapping("/simpleBoardContent")
//	public String simpleBoardContent(@RequestParam("id") int id , Model model) {
//		SimpleBoard vo = simpleBoardMapper.simpleBoardContent(id);
//		simpleBoardMapper.simpleBoardCount(id);
//		model.addAttribute("vo",vo);
//		return "simpleBoardContent";
//	}
//	
//	@GetMapping("/simpleBoardUpdateForm/{id}")
//	public String simpleBoardUpdateForm(@PathVariable("id") int id,Model model) {
//		SimpleBoard vo = simpleBoardMapper.simpleBoardContent(id);
//		model.addAttribute("vo",vo);
//		return "simpleBoardUpdateForm";
//	}
//	
//	@PostMapping("/simpleBoardUpdate")
//	public String simpleBoardUpdate(SimpleBoard vo) {
//		simpleBoardMapper.simpleBoardUpdate(vo);
//		return "redirect:/home";
//	}
//	
//	@GetMapping("/simpleBoardDelete/{id}")
//	public String boardDelete(@PathVariable int id) {
//		simpleBoardMapper.simpleBoardDelete(id);
//		return "redirect:/home";
//	}
	
	
	// JdbcTemplate를 통한 CRUD
	private final JdbcTemplate jdbcTemplate;
	
	@GetMapping("/home")
	public String Home(Model model) {
		String sql = "select * from simpleBoard";
		
		List<SimpleBoard> list =jdbcTemplate.query(sql, new RowMapper<SimpleBoard>() {
			@Override
	        public SimpleBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				SimpleBoard board = new SimpleBoard();
	            board.setId(rs.getInt("id"));
	            board.setTitle(rs.getString("title"));
	            board.setContent(rs.getString("content"));
	            board.setWriter(rs.getString("writer"));	            
	            board.setIndate(rs.getDate("indate").toString());
	            board.setCount(rs.getInt("count"));
	            
	            return board;
	        }
	    });
		model.addAttribute("list",list);
		return "home";
	}
	
	
	@GetMapping("/simpleBoardForm")
	public String boardForm() {
		return "simpleBoardForm";
	}
	
	
	@PostMapping("/simpleBoardInsert")
	public String boardInsert(SimpleBoard vo) {
		String sql = "insert into simpleBoard(title,content,writer) values (?, ?, ?)";
		jdbcTemplate.update(con->{
			PreparedStatement preparedStatement =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, vo.getTitle());
			preparedStatement.setString(2, vo.getContent());
			preparedStatement.setString(3, vo.getWriter());
			
			return preparedStatement;
		});
		
		return "redirect:/home";
	}
	
	@GetMapping("/simpleBoardContent")
	public String simpleBoardContent(@RequestParam("id") int id , Model model) {
		String sql = "select * from simpleBoard";
		
		List<SimpleBoard> list =jdbcTemplate.query(sql, new RowMapper<SimpleBoard>() {
			@Override
	        public SimpleBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				SimpleBoard board = new SimpleBoard();
	            board.setId(rs.getInt("id"));
	            board.setTitle(rs.getString("title"));
	            board.setContent(rs.getString("content"));
	            board.setWriter(rs.getString("writer"));	            
	            board.setIndate(rs.getDate("indate").toString());
	            board.setCount(rs.getInt("count"));
	            
	            return board;
	        }
	    });
		model.addAttribute("vo",list.get(0));
		
		String countPlusSql = "update simpleBoard set count = count + 1 where id = ?";
	    jdbcTemplate.update(countPlusSql, id);
		return "simpleBoardContent";
	}
	
	@GetMapping("/simpleBoardUpdateForm/{id}")
	public String simpleBoardUpdateForm(@PathVariable("id") int id,Model model) {
		String sql = "select * from simpleBoard";
		
		List<SimpleBoard> list =jdbcTemplate.query(sql, new RowMapper<SimpleBoard>() {
			@Override
	        public SimpleBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				SimpleBoard board = new SimpleBoard();
	            board.setId(rs.getInt("id"));
	            board.setTitle(rs.getString("title"));
	            board.setContent(rs.getString("content"));
	            board.setWriter(rs.getString("writer"));	            
	            board.setIndate(rs.getDate("indate").toString());
	            board.setCount(rs.getInt("count"));
	            
	            return board;
	        }
	    });
		model.addAttribute("vo",list.get(0));
		return "simpleBoardUpdateForm";
	}
	
	@PostMapping("/simpleBoardUpdate")
	public String simpleBoardUpdate(SimpleBoard vo) {
		String sql = "update simpleBoard set title=?,content=? where id=?";
		jdbcTemplate.update(sql,vo.getTitle(),vo.getContent(),vo.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/simpleBoardDelete/{id}")
	public String boardDelete(@PathVariable int id) {
		String sql = "delete from simpleBoard where id = ?";
		jdbcTemplate.update(sql,id);
		return "redirect:/home";
	}
	
	

	
	
}
