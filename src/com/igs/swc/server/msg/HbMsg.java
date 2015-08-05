package com.igs.swc.server.msg;

import org.apache.mina.core.buffer.IoBuffer;

import com.igs.swc.eis.MsgKey;

public class HbMsg extends Msg {

	private static final long serialVersionUID = 4699875043002971341L;

	public static final String X_SWC_ID = "x_swc_id";
	private static final String X_REL_ID_KEY_P = "x_rel_id_%d";
	private static final String REL_ID_P = "rel%05d%s";

	public HbMsg(IoBuffer buff) {
		super(buff);
	}

	@Override
	public Msg addOther() {
		return skip(156);
	}

	@Override
	public Msg afterProcess() {
		addVal(X_SWC_ID, String.format("swc%05d%s", 0, getVal(MsgKey.HW_ID)));

		processRelays();

		addVal(MsgKey.MSG_SIG, msgSign());

		addVal(MsgKey.HW_OFFLINE_TIME, getVal(MsgKey.HW_RTC));
		addVal(MsgKey.HW_OFFLINE_FLAG, MsgKey.HW_OFFLINE_FLAG_F);

		return this;
	}

	private final String msgSign() {
		StringBuilder sb = new StringBuilder(64);
		sb.append(getVal(MsgKey.HW_WARNING)).append(getVal(MsgKey.HW_WM))
				.append(getVal(MsgKey.HW_NET_IP))
				.append(getVal(MsgKey.HW_REL_STATE_0))
				.append(getVal(MsgKey.HW_REL_USED_0))
				.append(getVal(MsgKey.HW_REL_STATE_1))
				.append(getVal(MsgKey.HW_REL_USED_1))
				.append(getVal(MsgKey.HW_REL_STATE_2))
				.append(getVal(MsgKey.HW_REL_USED_2))
				.append(getVal(MsgKey.HW_REL_STATE_3))
				.append(getVal(MsgKey.HW_REL_USED_3));

		return sb.toString();
	}

	private final void processRelays() {
		short i = getVal(MsgKey.HW_REL_COUNT);
		String hwId = getVal(MsgKey.HW_ID);

		for (int j = 0; j < i; j++) {
			this

			.addVal(String.format("hw_rel_vaild_%d", j), 1)
					.addVal(String.format("hw_rel_num_%d", j), j)
					.addVal(String.format(X_REL_ID_KEY_P, j),
							String.format(REL_ID_P, j, hwId));
		}
	}

}
