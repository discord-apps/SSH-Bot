package org.shanerx.discord.ssh;

public class SSHInfo {
	
	private String hostname;
	private String user;
	private String password;
	
	public SSHInfo(String hostname, String user, String password) {
		this.hostname = hostname;
		this.user = user;
		this.password = password;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
}