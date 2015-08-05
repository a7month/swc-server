package com.igs.swc.server.codec;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

import com.igs.swc.eis.MsgKey;
import com.igs.swc.server.msg.HbMsg;

public class HbMsgDecoder extends MessageDecoderAdapter {

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer buffer) {

		if (buffer.remaining() < 1) {
			return NEED_DATA;
		}

		if (buffer.get() == 0x00) {
			return OK;
		}

		return NOT_OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer buffer,
			ProtocolDecoderOutput output) throws Exception {

		InetSocketAddress address = (InetSocketAddress) session
				.getRemoteAddress();
		String ip = address.getAddress().getHostAddress();

		HbMsg msg = new HbMsg(buffer);

		msg.add1UShort(MsgKey.PROTOCOL_FLAG).add1UShort(MsgKey.HW_FW_VER)
				.skip(1).add1UShort(MsgKey.HW_TYPE)

				.addHexByte(MsgKey.HW_ID, 4)

				.skip(2).add1UShort(MsgKey.HW_TM_CYCLE)
				.add1UShort(MsgKey.HW_REL_COUNT)

				.add2UShort(MsgKey.HW_WARNING).add2UShort(MsgKey.HW_WM)

				.add8Long(MsgKey.HW_RTC)

				.skip(8)

				.addVal(MsgKey.HW_REL_NUM_0, 0)
				.add1UShort(MsgKey.HW_REL_STATE_0)
				.add1UShort(MsgKey.HW_REL_RRC_0)
				.add2UShort(MsgKey.HW_REL_USED_0)
				.addVal(MsgKey.REL_ID_0, 0)
				.addVal(MsgKey.HW_REL_VAILD_0, 0)
				

				.addVal(MsgKey.HW_REL_NUM_1, 1)
				.add1UShort(MsgKey.HW_REL_STATE_1)
				.add1UShort(MsgKey.HW_REL_RRC_1)
				.add2UShort(MsgKey.HW_REL_USED_1)
				.addVal(MsgKey.REL_ID_1, 0)
				.addVal(MsgKey.HW_REL_VAILD_1, 0)

				.addVal(MsgKey.HW_REL_NUM_2, 2)
				.add1UShort(MsgKey.HW_REL_STATE_2)
				.add1UShort(MsgKey.HW_REL_RRC_2)
				.add2UShort(MsgKey.HW_REL_USED_2)
				.addVal(MsgKey.REL_ID_2, 0)
				.addVal(MsgKey.HW_REL_VAILD_2, 0)

				.addVal(MsgKey.HW_REL_NUM_3, 3)
				.add1UShort(MsgKey.HW_REL_STATE_3)
				.add1UShort(MsgKey.HW_REL_RRC_3)
				.add2UShort(MsgKey.HW_REL_USED_3)
				.addVal(MsgKey.REL_ID_3, 0)
				.addVal(MsgKey.HW_REL_VAILD_3, 0)

				.skip(16).skip(36)

				.addOther()

				.addVal(MsgKey.HW_NET_IP, ip)

				.afterProcess()

				.end();

		output.write(msg);
		return OK;
	}
}
