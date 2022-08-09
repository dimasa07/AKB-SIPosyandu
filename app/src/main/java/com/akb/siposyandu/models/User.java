package com.akb.siposyandu.models;
import com.akb.siposyandu.constants.*;

public class User
{
	private String username;
	private Level level;
	private String nama;
	
	public User(){}
	
	public User(String username, String nama, Level level){
		this.username = username;
		this.nama = nama;
		this.level = level;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

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
	}
}
