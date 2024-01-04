package com.herry.code.practice.week08.rpc.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ClassTypeAdapter extends TypeAdapter<Class<?>> {
    @Override
    public void write(JsonWriter out, Class<?> value) throws IOException {
        out.value(value.getName());
    }

    @Override
    public Class<?> read(JsonReader in) throws IOException {
        try {
            String className = in.nextString();
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IOException("Error reading Class object", e);
        }
    }
}
