package com.igs.swc.server.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class SwcProtocolCodecFactory extends DemuxingProtocolCodecFactory {

	public SwcProtocolCodecFactory() {
		this.addMessageDecoder(new HbMsgDecoder());
		this.addMessageDecoder(new EventMsgDecoder());
	}

}
