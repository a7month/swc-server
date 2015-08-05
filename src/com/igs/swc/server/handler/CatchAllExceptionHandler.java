package com.igs.swc.server.handler;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.ExceptionHandler;

public class CatchAllExceptionHandler implements
		ExceptionHandler<Exception> {

	@Override
	public void exceptionCaught(IoSession session,
			Exception cause) throws Exception {
		System.out.println(cause);
	}

}