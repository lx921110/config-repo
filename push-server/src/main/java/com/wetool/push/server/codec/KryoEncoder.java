package com.wetool.push.server.codec;


import java.io.ObjectOutputStream;

import com.esotericsoftware.kryo.KryoSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<KryoSerializable> {
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

	@Override
	protected void encode(ChannelHandlerContext ctx, KryoSerializable msg, ByteBuf out) throws Exception {
		int startIdx = out.writerIndex();

        ByteBufOutputStream bout = new ByteBufOutputStream(out);
        ObjectOutputStream oout = null;
        try {
            bout.write(LENGTH_PLACEHOLDER);
            oout = new CompactObjectOutputStream(bout);
            oout.writeObject(msg);
            oout.flush();
        } finally {
            if (oout != null) {
                oout.close();
            } else {
                bout.close();
            }
        }
        int endIdx = out.writerIndex();

        out.setInt(startIdx, endIdx - startIdx - 4);
	}

}
