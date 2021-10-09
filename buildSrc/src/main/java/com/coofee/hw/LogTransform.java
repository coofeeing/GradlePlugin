package com.coofee.hw;

import com.android.build.api.transform.*;
import com.android.build.gradle.internal.pipeline.TransformManager;

import java.io.IOException;
import java.util.Set;

public class LogTransform extends Transform {
    @Override
    public String getName() {
        return "LogTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    //是否支持增量编译
    @Override
    public boolean isIncremental() {
        return true;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        for(TransformInput transformInput : transformInvocation.getInputs()){
            for(JarInput jarInput : transformInput.getJarInputs()){
                System.out.println("file path="+jarInput.getFile().getAbsolutePath());
            }
        }
    }
}
