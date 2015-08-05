package com.igs.swc.server.handler;

import java.util.Collection;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.MessageHandler;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igs.swc.eis.MsgKey;
import com.igs.swc.server.msg.HbMsg;

public class HbMsgHandler implements MessageHandler<HbMsg> {

	private RedisTemplate<String, Object> redisTemplate;
	private ObjectMapper mapper = new ObjectMapper();
	private Collection<String> swcKeys;

	public HbMsgHandler(RedisTemplate<String, Object> redisTemplate,
			Collection<String> swcKeys) {
		this.redisTemplate = redisTemplate;
		this.swcKeys = swcKeys;
	}

	@Override
	public void handleMessage(IoSession session, HbMsg msg) throws Exception {
		String swcId = msg.getVal(HbMsg.X_SWC_ID);
		String msgSign = msg.getVal(MsgKey.MSG_SIG);

		BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate
				.boundHashOps(swcId);

		if (swcKeys.contains(swcId)) {
			String oldMsgSign = (String) boundHashOperations
					.get(MsgKey.MSG_SIG);
			if (!msgSign.equals(oldMsgSign)) {
				boundHashOperations.putAll(msg);
				redisTemplate.convertAndSend(MsgKey.T_SWC_HB,
						mapper.writeValueAsString(msg));
			}
		} else {
			boundHashOperations.putAll(msg);
			swcKeys.add(swcId);
			redisTemplate.convertAndSend(MsgKey.T_SWC_NEW,
					mapper.writeValueAsString(msg));
		}

	}
}
