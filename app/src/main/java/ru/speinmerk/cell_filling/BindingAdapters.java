package ru.speinmerk.cell_filling;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @BindingAdapter({"bind:scrollTo"})
    public static void scrollTo(RecyclerView recyclerView, int position) {
        recyclerView.scrollToPosition(position);
    }

}