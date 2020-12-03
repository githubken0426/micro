package org.config.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataConfig {
	@Value("${data.env}")
	private String env;

	@Value("${data.user.username}")
	private String username;

	@Value("${data.user.password}")
	private String password;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo{env:'" + env + "',username='" + username + '\'' + ", password='" + password + '\'' + '}';
	}
}
