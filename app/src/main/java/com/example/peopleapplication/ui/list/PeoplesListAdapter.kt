package com.example.peopleapplication.ui.list

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.peopleapplication.data.model.People
import com.example.peopleapplication.databinding.LayoutListItemBinding

class PeoplesListAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<People, PeoplesListAdapter.PeopleViewHolder>(DiffCallback()) {

    /**
     * Notifies click on an item with attached view
     */
    interface OnItemClickListener {
        fun onItemClick(people: People)
    }

    /**
     * Creates view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PeopleViewHolder(binding)
    }

    /**
     * Binds view with item info
     */
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * View for item, sets item info and click events
     */
    inner class PeopleViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition

                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }
            }
        }


        fun bind(people: People) {
            binding.apply {
                textViewName.text = people.name
                textViewMet.text = people.metAt
                buttonContact.text = people.contact

                buttonContact.setOnClickListener {
                    // Dial contact number
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:${people.contact}")
                    itemView.context.startActivity(dialIntent)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
            oldItem == newItem
    }

}
