package com.igs.swc.server.handler;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.handler.demux.ExceptionHandler;

public class ProtocolDecoderExceptionHandler implements
		ExceptionHandler<ProtocolDecoderException> {

	@Override
	public void exceptionCaught(IoSession session,
			ProtocolDecoderException cause) throws Exception {
		System.out.println(cause);
	}

}