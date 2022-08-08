package com.akb.siposyandu.models;
import com.akb.siposyandu.constants.*;

public class User
{
	private String username;
	private Level level;

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setLevel(Level level){
		this.level = level;
	}

	public Level getLevel(){
		return level;
	}}
