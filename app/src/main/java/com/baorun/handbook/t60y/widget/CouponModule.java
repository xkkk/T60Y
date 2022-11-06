package com.baorun.handbook.t60y.widget;


import android.content.Context;

import androidx.annotation.NonNull;

import com.baorun.handbook.t60y.AppContext;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class CouponModule extends AppGlideModule {

    private static final String TAG = "CouponModule";

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(AppContext.INSTANCE).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        int defaultArrayPoolSize = calculator.getArrayPoolSizeInBytes();

        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565));
        builder.setSourceExecutor(GlideExecutor.newSourceBuilder().setThreadCount(1).setName("glide-load-source").setUncaughtThrowableStrategy(GlideExecutor.UncaughtThrowableStrategy.DEFAULT).build());
        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize/ 2));
        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize / 2));
        builder.setArrayPool(new LruArrayPool(defaultArrayPoolSize / 2));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}