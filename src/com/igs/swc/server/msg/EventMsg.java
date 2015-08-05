package com.igs.swc.server.msg;

import org.apache.mina.core.buffer.IoBuffer;

public class EventMsg extends Msg {

	private static final long serialVersionUID = 4699875043002971341L;

	public EventMsg(IoBuffer buff) {
		super(buff);
	}

}
