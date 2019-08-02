package com.addlog;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;


public class AddLogPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
//        表示从使用者的gradle配置中读取插件配置信息，可让插件的使用者可以自定义一些插件的属性。
        AddLogExtension extension = project.getExtensions().create("addLog", AddLogExtension.class);

        Config.getInstance().extension = extension;

//        这两行就是实例化了一个自定义Transform类AddLogTransform，并注册到编译流程中去。
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        appExtension.registerTransform(new AddLogTransform(project), Collections.EMPTY_LIST);
    }
}