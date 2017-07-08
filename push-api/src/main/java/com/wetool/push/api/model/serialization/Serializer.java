package com.wetool.push.api.model.serialization;

import java.io.IOException;

public interface Serializer {

	byte[] encode(Object msg) throws IOException;

	<T> T decode(byte[] buf, Class<T> type) throws IOException;
}