package com.wetool.push.api.model.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements Serializer {

    @Override
    public byte[] encode(Object msg) throws IOException {
        Hessian2Output out = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            out = new Hessian2Output(bos);
            out.writeObject(msg);
            out.flush();
            return bos.toByteArray();
        } finally {
            if(out!=null){
                out.close();
            }
        }
    }

    @Override
    public <T> T decode(byte[] buf, Class<T> type) throws IOException {
        Hessian2Input input = null;
        try {
            input = new Hessian2Input(new ByteArrayInputStream(buf));
            return (T) input.readObject(type);
        } finally {
            if(input!=null){
                input.close();
            }
        }
    }
}