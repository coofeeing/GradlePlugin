package com.coofee.hw;

import com.android.build.api.transform.*;
import com.android.build.gradle.internal.pipeline.TransformManager;

import java.io.*;
import java.util.Set;
import java.util.function.Consumer;

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
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
//        for(TransformInput transformInput : transformInvocation.getInputs()){
//            for(JarInput jarInput : transformInput.getJarInputs()){
//                System.out.println("file path="+jarInput.getFile().getAbsolutePath());
//            }
//        }
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
        if (outputProvider != null) {
            outputProvider.deleteAll();
        }
        transformInvocation.getInputs().forEach(new Consumer<TransformInput>() {
            @Override
            public void accept(TransformInput transformInput) {
                transformInput.getDirectoryInputs().forEach(new Consumer<DirectoryInput>() {
                    @Override
                    public void accept(DirectoryInput directoryInput) {
                        System.out.println("name1=" + directoryInput.getName());
                        File file = directoryInput.getFile();
                        searchFiles(file);
                    }
                });
//                transformInput.getJarInputs().forEach(new Consumer<JarInput>() {
//                    @Override
//                    public void accept(JarInput jarInput) {
////                        System.out.println("name2=" + jarInput.getFile());
//                        if (jarInput.getFile().getAbsolutePath().endsWith(".jar")) {
//
//
//                        }
//                    }
//                });
            }
        });
    }

    private void searchFiles(File directory){
        File[] files = directory.listFiles();
        for (File file : files){
            if(file.isDirectory()){
                searchFiles(file);
            }else if(file.getAbsolutePath().endsWith(".class")){
                System.out.println("class name="+file.getName());
            }
        }
    }

    private void traceFile(File inputFile, File outputFile) {
        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);


            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){

        }
    }
}
