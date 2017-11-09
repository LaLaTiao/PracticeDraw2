package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    CornerPathEffect cornerPathEffect = new CornerPathEffect(70);
    DiscretePathEffect discretePathEffect = new DiscretePathEffect(2, 10);
    DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10, 5, 2, 5}, 100);
    PathDashPathEffect pathDashPathEffect;
    SumPathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect);
    ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        Path mPath = new Path();
        mPath.moveTo(10f, 10f);
        mPath.rLineTo(20f, 20f);
        mPath.rLineTo(-40f, 0f);
        mPath.close();
        pathDashPathEffect = new PathDashPathEffect(mPath, 25, 0, PathDashPathEffect.Style.TRANSLATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * CornerPathEffect         把所有直角变成圆角(radius)
         * DiscretePathEffect       随机将线条偏移(每段偏移线条的长度,偏移量)
         * DashPathEffect           虚线(数组至少2个元素,第一段长度,gap长度),偏移量phase干嘛的?
         * PathDashPathEffect       加一个path当效果(path效果,每个path之间的间隔,偏移量,显示效果)
         *                          TRANSLATE：位移  ROTATE：旋转  MORPH：变体
         * SumPathEffect            绘制2种效果,叠加
         * ComposePathEffect        先绘制1种效果,然后在该效果上绘制第二种效果
         */
        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);

        // 第二处：DiscretePathEffect
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);

        // 第三处：DashPathEffect
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);

        // 第四处：PathDashPathEffect
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);

        // 第五处：SumPathEffect
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);

        // 第六处：ComposePathEffect
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);

        canvas.restore();
    }
}
