package com.nagihong.androidsimplify.recyclerView.expandable

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.nagihong.androidsimplify.recyclerView.SimpleAdapterDataObserver

class SimpleSectionAdapter(
    private var sections: List<BaseSection>,
    enableStableIds: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var count = 0
    private var dataIndex = mutableListOf<Int>()
    private var typeIndex = mutableMapOf<Int, BaseSection>()
    private val notify: (() -> Unit) = { notifyDataSetChanged() }
    var onNotifyDataSetChanged: (() -> Unit)? = null

    constructor(hasStableIds: Boolean = false) : this(mutableListOf(), hasStableIds)

    constructor(section: BaseSection) : this(mutableListOf(section))

    fun addSection(section: BaseSection) {
        val new = sections.toMutableList()
        new.add(section)
        sections = new
    }

    fun addSections(sections: List<BaseSection>) {
        val new = this.sections.toMutableList()
        new.addAll(sections)
        this.sections = new
    }

    fun setSections(sections: List<BaseSection>) {
        this.sections = sections
    }

    init {
        setHasStableIds(enableStableIds)
        registerAdapterDataObserver(SimpleAdapterDataObserver { whenDataSetChanged() })
        indexing()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return typeIndex[viewType]!!.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val section = mapSection(position)
        val sectionPosition = mapSectionPosition(position)
        section?.onBind(holder, sectionPosition)
    }

    override fun getItemCount() = count

    override fun getItemViewType(position: Int): Int {
        val section = mapSection(position)
        if (null == section) {
            Log.d(javaClass.name, "warning: return default type at position: $position")
            return 0
        }
        return if (section is BaseExpandableSection) {
            val sectionPosition = mapSectionPosition(position)
            section.typeAt(sectionPosition)
        } else {
            section.type()
        }
    }

    override fun getItemId(position: Int): Long {
        val section = mapSection(position) ?: return position.toLong()
        return section.id(mapSectionPosition(position))
    }

    private fun whenDataSetChanged() {
        indexing()
        onNotifyDataSetChanged?.invoke()
    }

    private fun indexing() {
        count = 0
        dataIndex.clear()
        typeIndex.clear()
        sections.forEach {
            count += it.count()
            dataIndex.add(count - 1)
            if (it is BaseExpandableSection) {
                it.types.forEach { type ->
                    typeIndex[type] = it
                }
            } else {
                typeIndex[it.type()] = it
            }
            it.attach(notify)
        }
    }

    private fun mapSection(position: Int): BaseSection? {
        dataIndex.forEachIndexed { index, indexPosition ->
            if (position <= indexPosition) {
                return sections[index]
            }
        }
        return null
    }

    private fun mapSectionPosition(position: Int): Int {
        dataIndex.forEachIndexed { index, sectionLastPosition ->
            if (position <= sectionLastPosition) {
                if (index <= 0) return position
                val lastSectionLastPosition = dataIndex[index - 1]
                return position - lastSectionLastPosition - 1
            }
        }
        return -1
    }

}