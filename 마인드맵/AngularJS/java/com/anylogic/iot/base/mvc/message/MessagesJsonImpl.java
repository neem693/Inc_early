package com.anylogic.iot.base.mvc.message;

import java.util.ArrayList;
import java.util.List;

public class MessagesJsonImpl implements Messages, MessageAccessor{

	private List<Message> messages = new ArrayList<Message>();

	@Override
	public void addMessage(String code, String msg) {

		messages.add(new Message(code, msg));
	}

	@Override
	public List<Message> getMessageList() {
		return messages;
	}

}
