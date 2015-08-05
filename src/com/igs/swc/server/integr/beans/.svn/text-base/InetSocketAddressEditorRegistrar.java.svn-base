package com.igs.swc.server.integr.beans;

import java.net.SocketAddress;

import org.apache.mina.integration.beans.InetSocketAddressEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class InetSocketAddressEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(SocketAddress.class,
				new InetSocketAddressEditor());

	}

}
