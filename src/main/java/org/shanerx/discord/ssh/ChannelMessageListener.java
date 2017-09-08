package org.shanerx.discord.ssh;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Map;

public class ChannelMessageListener extends ListenerAdapter {
	
	private Bot bot;
	
	public ChannelMessageListener(Bot bot) {
		this.bot = bot;
	}
	
	public Bot getBot() {
		return bot;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		Guild guild = event.getGuild();
		TextChannel text = event.getChannel();
		Message mess = event.getMessage();
		
		Map<TextChannel, SSHInfo> channelMap = bot.getChannelMap();
		if (!channelMap.containsKey(text)) {
			return;
		}
		
		String cmd = mess.getContent();
	}
}