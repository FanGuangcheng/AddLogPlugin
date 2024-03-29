package com.addlog;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class AddLogClassAdapter extends ClassVisitor {
    private String mClassName;
    private List<String> mMethodNames;

    public AddLogClassAdapter(ClassWriter classWriter) {
        super(Opcodes.ASM5, classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mClassName = name.replace("/", ".");
        mMethodNames = Config.getInstance().extension.hookPoint.get(mClassName);
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println("add log class : " + mClassName);

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (mMethodNames.contains(name)) {
            return new AddLogAdviceAdapter(Opcodes.ASM5,
                    super.visitMethod(access, name, desc, signature, exceptions), access, name, desc, mClassName);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
