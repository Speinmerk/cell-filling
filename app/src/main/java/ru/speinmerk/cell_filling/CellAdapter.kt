package ru.speinmerk.cell_filling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cell.view.*


class CellAdapter : RecyclerView.Adapter<CellAdapter.CellViewHolder>() {

    private val cells = ArrayList<Cell>()

    val scrollTo = ObservableInt(0)

    fun add(cell: Cell) {
        cells.add(cell)
        notifyItemInserted(cells.lastIndex)
        scrollTo.set(cells.lastIndex)
    }

    fun takeLast(count: Int) = cells.takeLast(count)

    fun removeLast(type: CellType) {
        val index = cells.indexOfLast { it.type == type }
        if (index != -1) {
            cells.removeAt(index)
            notifyItemRemoved(index)
            scrollTo.set(cells.lastIndex)
        }
    }

    override fun getItemCount(): Int = cells.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cell, parent, false)
        return CellViewHolder(view)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(cells[position])
    }

    class CellViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageFrameLayout = view.cellImageFrameLayout
        private val imageView = view.cellImageView
        private val titleTextView = view.titleCellTextView
        private val descriptionTextView = view.descriptionCellTextView

        fun bind(cell: Cell) {
            val index = cell.type.ordinal
            itemView.context.resources.apply {
                titleTextView.text = getStringArray(R.array.cell_titles)[index]
                descriptionTextView.text = getStringArray(R.array.cell_descriptions)[index]
            }
            val resImage = when (index) {
                0 -> R.drawable.ic_dead
                1 -> R.drawable.ic_lively
                else -> R.drawable.ic_life
            }
            val resBackgroundImage = when (index) {
                0 -> R.drawable.background_image_dead
                1 -> R.drawable.background_image_lively
                else -> R.drawable.background_image_life
            }
            imageFrameLayout.setBackgroundResource(resBackgroundImage)
            Picasso.get()
                .load(resImage)
                .resize(20, 20)
                .centerCrop()
                .into(imageView)
        }
    }

}