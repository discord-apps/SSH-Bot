package org.shanerx.discord.ssh;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Bot {
	
	public static void main(String[] args) {
		
		File config = new File("conf.json");
		Bot bot = new Bot();
		bot.createConfig(config);
		
		try {
			bot.json = (JSONObject) new JSONParser().parse(new FileReader(config));
		} catch (Exception e) {
			Logger.getLogger("BOT").log(java.util.logging.Level.SEVERE, "Could not parse config file.", e.getCause());
			System.exit(-2);
		}
		
		String login = (String) bot.json.get("login_token");
		Game game = Game.of((String) bot.json.get("game"));
		
		try {
			bot.jda = new JDABuilder(AccountType.BOT)
					.setToken(login)
					.setGame(game)
					.buildBlocking();
		} catch (Exception e) {
			Logger.getLogger("BOT").log(java.util.logging.Level.SEVERE, "Could not build JDA object.", e.getCause());
			System.exit(-3);
		}
	}
	
	private JDA jda;
	private JSONObject json;
	
	protected Bot() {}
	
	public JDA getJDA() {
		return jda;
	}
	
	public JSONObject getJson() {
		return json;
	}
	
	protected void createConfig(File config) {
		if (!config.exists()) {
			try {
				config.createNewFile();
				PrintWriter pw = new PrintWriter(config);
				pw.println("{");
				pw.println("    \"login_token\": \"<insert token>\",");
				pw.println("    \"game\": \"PuTTY\",");
				pw.println("    \"ssh\": [");
				pw.println("        {\"guild\":\"<insert id>\",");
				pw.println("        \"channel\":\"<insert id>\",");
				pw.println("        \"hostname\":\"<insert ip>\",");
				pw.println("        \"username\":\"<insert user>\",");
				pw.println("        \"password\":\"<insert pass>\"");
				pw.println("        }");
				pw.println("    ]");
				pw.println("}");
				pw.flush();
				pw.close();
			} catch (IOException e) {
				Logger.getLogger("BOT").log(java.util.logging.Level.SEVERE, "Could not create fresh config file.", e.getCause());
				System.exit(-1);
			}
		}
	}
}