package tlt.th.co.toyotaleasing.view.spotlight.target;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.lang.ref.WeakReference;

import tlt.th.co.toyotaleasing.view.spotlight.OnTargetStateChangedListener;
import tlt.th.co.toyotaleasing.view.spotlight.shape.Circle;
import tlt.th.co.toyotaleasing.view.spotlight.shape.Shape;

public abstract class AbstractTargetBuilder<T extends AbstractTargetBuilder<T, S>, S extends Target> {

    private static final PointF DEFAULT_POINT = new PointF(0, 0);
    private static final long DEFAULT_DURATION = 250L;
    private static final TimeInterpolator DEFAULT_ANIMATION = new DecelerateInterpolator(2f);
    private static final Shape DEFAULT_SHAPE = new Circle(100);
    private WeakReference<Activity> contextWeakReference;

    protected PointF point = DEFAULT_POINT;
    protected Shape shape = DEFAULT_SHAPE;
    protected long duration = DEFAULT_DURATION;
    protected TimeInterpolator animation = DEFAULT_ANIMATION;
    protected OnTargetStateChangedListener listener = null;

    protected abstract T self();

    protected abstract S build();

    protected Activity getContext() {
        return contextWeakReference.get();
    }

    public AbstractTargetBuilder(@NonNull Activity context) {
        contextWeakReference = new WeakReference<>(context);
    }

    public T setPoint(@NonNull View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        int x = location[0] + view.getWidth() / 2;
        int y = location[1] + view.getHeight() / 2;
        return setPoint(x, y);
    }

    public T setPoint(float x, float y) {
        setPoint(new PointF(x, y));
        return self();
    }

    public T setPoint(@NonNull PointF point) {
        this.point = point;
        return self();
    }

    public T setShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }
        this.shape = shape;
        return self();
    }

    public T setDuration(@NonNull long duration) {
        this.duration = duration;
        return self();
    }

    public T setAnimation(@NonNull TimeInterpolator animation) {
        this.animation = animation;
        return self();
    }

    public T setOnSpotlightStartedListener(@NonNull final OnTargetStateChangedListener<S> listener) {
        this.listener = listener;
        return self();
    }
}