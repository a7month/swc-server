package com.igs.swc.server.integr.redis;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

public class ToStringRedisSerializer implements RedisSerializer<Object> {

	private final Charset charset;

	public ToStringRedisSerializer() {
		this(Charset.forName("UTF8"));
	}

	public ToStringRedisSerializer(Charset charset) {
		Assert.notNull(charset);
		this.charset = charset;
	}

	@Override
	public byte[] serialize(Object obj) throws SerializationException {

		if (obj == null) {
			return null;
		}

		if (obj.getClass() == String.class) {
			return ((String) obj).getBytes(charset);
		}

		return String.valueOf(obj).getBytes(charset);

	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return (bytes == null ? null : new String(bytes, charset));
	}

}
