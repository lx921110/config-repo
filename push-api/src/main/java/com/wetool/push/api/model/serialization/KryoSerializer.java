package com.wetool.push.api.model.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KryoSerializer implements Serializer {

    private static final ThreadLocal<Kryo> THREAD_LOCAL = new ThreadLocal<Kryo>(){
        @Override
        protected Kryo initialValue() {

            Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            return kryo;
        }
    };

    @Override
    public byte[] encode(Object msg) throws IOException {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Output output = new Output(bos)){

            Kryo kryo = THREAD_LOCAL.get();
            kryo.writeObject(output, msg);
            return output.toBytes();
        }
    }

    @Override
    public <T> T decode(byte[] buf, Class<T> type) throws IOException {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(buf);
             Input input = new Input(bis)) {

            Kryo kryo = THREAD_LOCAL.get();
            return kryo.readObject(input, type);
        }
    }
}
