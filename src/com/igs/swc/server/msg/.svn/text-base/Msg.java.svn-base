package com.igs.swc.server.msg;

import java.util.LinkedHashMap;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.proxy.utils.ByteUtilities;

public class Msg extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 4368891200785207663L;

	protected IoBuffer buf;

	public Msg() {

	}

	public Msg(IoBuffer buf) {
		this.buf = buf;
	}

	public Msg addVal(String key, Object val) {
		put(key, val);
		return this;
	}

	public Msg skip(int n) {
		for (int i = 0; i < n; i++) {
			buf.get();
		}
		return this;
	}

	public Msg skip8bits() {
		return skip(1);
	}

	public Msg skip16bits() {
		return skip(2);
	}

	public Msg skip32bits() {
		return skip(4);
	}

	@SuppressWarnings("unchecked")
	public <T> T getVal(String key) {
		return (T) get(key);
	}

	public Msg addBytes(String key, int n) {
		byte[] bs = new byte[n];
		buf.get(bs);
		addVal(key, bs);
		return this;
	}

	public Msg addHexByte(String key, int n) {
		byte[] bs = new byte[n];
		buf.get(bs);
		addVal(key, ByteUtilities.asHex(bs));
		return this;
	}

	public Msg addOpResult(String key, Op<?> op) {
		return addVal(key, op.op(this.buf, this));
	}

	public Msg add8Long(String key) {
		return addVal(key, buf.getLong());
	}

	public Msg add4Int(String key) {
		return addVal(key, buf.getInt());
	}

	public Msg add4UInt(String key) {
		return addVal(key, buf.getUnsignedInt());
	}

	public Msg add3Int(String key) {
		return addVal(key, buf.getMediumInt());
	}

	public Msg add3UInt(String key) {
		return addVal(key, buf.getUnsignedMediumInt());
	}

	public Msg add2Short(String key) {
		return addVal(key, buf.getShort());
	}

	public Msg add2UShort(String key) {
		return addVal(key, buf.getUnsignedShort());
	}

	public Msg add1UShort(String key) {
		return addVal(key, buf.getUnsigned());
	}

	public void end() {
	}

	public Msg addOther() {
		return this;
	}

	public Msg afterProcess() {
		return this;
	}

	public interface Op<T> {
		public T op(IoBuffer buffer, Msg msg);
	}

	public <S> S bufOp(Op<S> op) {
		return op.op(this.buf, this);
	}
}
