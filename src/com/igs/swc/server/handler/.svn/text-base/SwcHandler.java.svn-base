package com.igs.swc.server.handler;

import java.util.Collection;

import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.handler.demux.DemuxingIoHandler;
import org.springframework.data.redis.core.RedisTemplate;

import com.igs.swc.server.msg.HbMsg;

public class SwcHandler extends DemuxingIoHandler {

	private RedisTemplate<String, Object> redisTemplate;
	private Collection<String> swcKeys;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setSwcKeys(Collection<String> swcKeys) {
		this.swcKeys = swcKeys;
	}

	public void init() {
		this.addReceivedMessageHandler(HbMsg.class, new HbMsgHandler(
				redisTemplate, swcKeys));

		this.addExceptionHandler(ProtocolDecoderException.class,
				new ProtocolDecoderExceptionHandler());

		this.addExceptionHandler(Exception.class,
				new CatchAllExceptionHandler());
	}
}
