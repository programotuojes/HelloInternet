package com.programotuojes.hellointernet.activities.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class Separator extends RecyclerView.ItemDecoration {

    private final Paint paint;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int offset = (int) (paint.getStrokeWidth() / 2);

        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            final View view = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();

            final int position = params.getViewAdapterPosition();

            if (position < state.getItemCount())
                c.drawLine(view.getLeft(), view.getBottom() + offset, view.getRight(), view.getBottom() + offset, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int position = layoutParams.getViewAdapterPosition();

        if (position < state.getItemCount())
            outRect.set(0, 0, 0, (int) paint.getStrokeWidth());
        else
            outRect.setEmpty();
    }

    public Separator(Context context) {
        paint = new Paint();
        paint.setARGB(150, 189, 189, 189);
        final float thickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, context.getResources().getDisplayMetrics());
        paint.setStrokeWidth(thickness);
    }
}
