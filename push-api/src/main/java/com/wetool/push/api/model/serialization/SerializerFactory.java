package com.wetool.push.api.model.serialization;


public class SerializerFactory {

    public static Serializer getSerializer(){
        return new HessianSerializer();
    }
}
