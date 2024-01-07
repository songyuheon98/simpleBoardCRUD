package com.syh.entity;

import lombok.Data;

@Data
public class SimpleBoard {

	private int id;
	private String title;
	private String content;
	private String writer;
	private String indate;
	private int count;

}
