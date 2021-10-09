package com.coofee.hw;

import com.android.build.gradle.AppExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class LogPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("rootDir="+project.getRootDir());
        System.out.println("name="+project.getName());
        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class);
        appExtension.registerTransform(new LogTransform());
    }
}