package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    BitmapShader mBatman = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    BitmapShader mlogo = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    ComposeShader composeShader = new ComposeShader(mBatman, mlogo, PorterDuff.Mode.DST_IN);
//  ComposeShader composeShader = new ComposeShader(mBatman, mlogo, PorterDuff.Mode.SRC);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * PorterDuff即Alpha算法
         * ComposeShader构造函数 ShaderA是 目标图像, ShaderB 是源图像
         * SRC_OVER 源图像盖在目标图像上面
         * DST_OVER 目标图像盖在源图像上面
         * SRC_IN 只保留 源图像和目标图像重合区域的 源图像
         * DST_IN 只保留 源图像和目标图像重合区域的 目标图像
         * SRC_ATOP 只显示 目标图像,但 源图像和目标图像重合区域 显示 源图像
         * DST_ATOP 只显示 源图像,但 源图像和目标图像重合区域 显示 目标图像
         * SRC_OUT 只显示 源图像,但 源图像和目标图像重合区域 空缺
         * DST_OUT 只显示 目标图像,但 源图像和目标图像重合区域 空缺
         * XOR 2个全部显示,但 源图像和目标图像重合区域 空缺
         */

        paint.setShader(composeShader);
        canvas.drawCircle(200, 200, 200, paint);
    }
}
