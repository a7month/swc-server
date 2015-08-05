package com.igs.swc.server.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.igs.swc.eis.MsgKey;
import com.igs.swc.server.msg.EventMsg;

public class EventMsgDecoder extends MessageDecoderAdapter {

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer buffer) {

		if (buffer.remaining() < 1) {
			return NEED_DATA;
		}

		if (buffer.get() == 0x37) {
			return OK;
		}

		return NOT_OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer buffer,
			ProtocolDecoderOutput output) throws Exception {

		EventMsg msg = new EventMsg(buffer);

		msg.skip(3).add1UShort(MsgKey.HW_TYPE)

//		.add(MsgKey.KEY_RELAY_RRC_0, new EventMsg.this.SwcIdOp())

		.add1UShort(MsgKey.HW_WARNING).skip8bits()
				.add2UShort(MsgKey.HW_WM)
				.add2UShort("src").add2UShort("code")
				.add8Long(MsgKey.HW_RTC).addOther().afterProcess().end();
		output.write(msg);
		return OK;
	}
}
