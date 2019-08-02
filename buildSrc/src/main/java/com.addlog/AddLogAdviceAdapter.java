package com.addlog;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.Map;

public class AddLogAdviceAdapter extends AdviceAdapter {

    Label l1;
    Label l2;
    private String logHandleClass;
    private String logHandleMethod;
    private String mClassName;
    private String mMethodName;

    /**
     * Creates a new {@link AdviceAdapter}.
     *
     * @param api    the ASM API version implemented by this visitor. Must be one
     *               of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @param mv     the method visitor to which this adapter delegates calls.
     * @param access the method's access flags (see {@link Opcodes}).
     * @param name   the method's name.
     * @param desc   the method's descriptor (see {@link Type Type}).
     */
    protected AddLogAdviceAdapter(int api, MethodVisitor mv, int access, String name, String desc, String className) {
        super(api, mv, access, name, desc);
        mClassName = className;
        mMethodName = name;
        Map<String, String> logHandler = Config.getInstance().extension.logPrinter;
        if (logHandler != null && !logHandler.isEmpty()) {
            logHandler.entrySet().forEach(entry -> {
                logHandleClass = entry.getKey().replace(".", "/");
                logHandleMethod = entry.getValue();
            });
        }
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        if (logHandleClass != null && logHandleMethod != null) {
            String methodStr = mClassName + "->" + mMethodName + "onMethodEnter";

            mv.visitLdcInsn(methodStr);
            mv.visitMethodInsn(INVOKESTATIC, logHandleClass,
                    logHandleMethod, "(Ljava/lang/String;)V", false);
        }
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        if (logHandleClass != null && logHandleMethod != null) {
            String end = mClassName + "->" + mMethodName + ": onMethodExit ";

            mv.visitLdcInsn(end);
            mv.visitMethodInsn(INVOKESTATIC, logHandleClass,
                    logHandleMethod, "(Ljava/lang/String;)V", false);
        }
    }
}
