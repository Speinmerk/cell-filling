package ru.speinmerk.cell_filling

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val adapter = CellAdapter()

    fun clickAddFAB() {
        val type = CellType.values()[Random.nextInt(0, 2)]
        val cell = Cell(type)
        val lastCells = adapter.takeLast(2)
        adapter.add(cell)
        if (lastCells.size > 1 && lastCells.all { !it.isUsed && it.type == type })  {
            if (type == CellType.DEAD) {
                cell.isUsed = true
                lastCells.forEach { it.isUsed = true }
                adapter.removeLast(CellType.LIFE)
            } else {
                adapter.add(Cell(CellType.LIFE))
            }
        }
    }
}
