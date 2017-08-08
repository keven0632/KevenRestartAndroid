package com.cihon.androidrestart_keven.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zhengjian on 2017/8/3.
 */

public class FollowMoveBehavior extends CoordinatorLayout.Behavior<TextView> {
    /**
     * ���췽��
     */
    public FollowMoveBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * �ж�child�Ĳ����Ƿ�����dependency
     *
     * �����߼����жϷ���ֵ������false��ʾ������������true��ʾ����
     *
     * ��һ��������Ϊ�У�dependent view�ı仯��������һ�����View����Ϊ��
     * ����������У�Button����dependent view����ΪTextView����������
     * ʵ����dependent view���൱������ǰ����ܵı��۲���
     *
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setX(dependency.getX());
        child.setY(dependency.getY() + 100);
        return true;
    }
}
