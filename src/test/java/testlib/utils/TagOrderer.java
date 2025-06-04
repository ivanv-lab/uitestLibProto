package testlib.utils;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.*;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestTag;

import java.util.Comparator;

public class TagOrderer implements MethodOrderer {

    @Override
    public void orderMethods(MethodOrdererContext context){
        context.getMethodDescriptors().sort(Comparator.comparing(descriptor->{
            String tag=getTagValue((TestDescriptor) descriptor);
            return tag!=null?tag:"";
        }));
    }

    private String getTagValue(TestDescriptor descriptor){
        return descriptor.getTags().stream()
                .findFirst()
                .map(TestTag::getName)
                .orElse(null);
    }
}
