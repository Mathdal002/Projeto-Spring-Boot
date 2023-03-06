package br.gov.go.sefaz.accesscontrol.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.go.sefaz.accesscontrol.model.User;

@Service
public class UserService {
	
	//Simulando um banco de dados com a tabela user
	private List<User> database = new ArrayList<>();
	private String username;
	
	public void gravar(User user) {
		database.add(user);
	}
	
	public void alterar(User user) {
		int index = database.indexOf(user);
		database.set(index, user);
	}
	
	public User buscarPorUsername(String username) {
		int index = database.indexOf(new User(username));
		User selectUser = database.get(index);
		return selectUser;
	}
	
	public List<User> listar(){
		return database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
