package com.whyn.asm.adapters.inject;

import com.whyn.define.Const;
import com.whyn.utils.Log;
import com.yn.asmbutterknife.annotations.ViewInject;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public abstract class MethodViewInjection extends AdviceAdapter {

    private String mMethodName;
    private String mMethodDesc;
    private int mViewInjectType;

    protected MethodViewInjection(MethodVisitor mv, int access, String name, String desc, int viewInjectType) {
        super(Const.ASM_API, mv, access, name, desc);
        this.mMethodName = name;
        this.mMethodDesc = desc;
        this.mViewInjectType = viewInjectType;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if (this.mViewInjectType == ViewInject.ACTIVITY) {
            if ("setContentView".equals(name) && "(I)V".equals(desc)) {
                inject();
            }
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
    }

    @Override
    protected void onMethodEnter() {
        Log.v("onMethodEnter");
        super.onMethodEnter();
        if (this.mViewInjectType == ViewInject.VIEWHOLDER) {
            if (this.dstInjectMethodName().equals(this.mMethodName)
                    && this.dstInjectMethodDesc().equals(this.mMethodDesc))
                inject();
        }
    }

    private void inject() {
        injectFindViewById();
        injectOnClick();
    }

    protected abstract String dstInjectMethodName();

    protected abstract String dstInjectMethodDesc();

    protected abstract void injectFindViewById();

    protected abstract void injectOnClick();


}
