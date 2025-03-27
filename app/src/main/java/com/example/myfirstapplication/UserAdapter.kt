package com.example.myfirstapplication

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val userList: MutableList<UserModel>,
    private val onDelete: (Int) -> Unit // Callback for deleting items
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvId)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDob: TextView = view.findViewById(R.id.tvDob)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvId.text = "ID: ${user.id}"
        holder.tvName.text = "Name: ${user.name}"
        holder.tvDob.text = "DOB: ${user.dob}"

        // Handle item click for deletion
        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Delete User")
            builder.setMessage("Are you sure you want to delete ${user.name}?")

            builder.setPositiveButton("Yes") { _, _ ->
                onDelete(position) // Call delete function
            }

            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
