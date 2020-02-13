package ru.speinmerk.cell_filling

data class Cell(
    val type: CellType,
    var isUsed: Boolean = false
)