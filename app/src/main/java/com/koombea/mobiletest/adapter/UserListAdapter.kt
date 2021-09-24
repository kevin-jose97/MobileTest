package com.koombea.mobiletest.adapter

import com.koombea.mobiletest.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.koombea.mobiletest.activity.MainActivity
import com.koombea.mobiletest.model.UserModel


class UserListAdapter(
    context: Context,
    userList: List<UserModel?>?,
    clickListener: MainActivity
) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private val context: Context
    private var userList: List<UserModel?>?

    private val clickListener: MainActivity
    fun setUserList(userList: List<UserModel?>?) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userNameTextView.setText(userList!![position]?.userName)
        holder.userEmailTextView.setText(userList!![position]?.userEmail)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                clickListener.onUserClick(userList!![position])
            }

            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun hashCode(): Int {
                return super.hashCode()
            }

            override fun toString(): String {
                return super.toString()
            }
        })
        Glide.with(context)
            .load(userList!![position]?.userProfilePic)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.userPicImage)

    }

    override fun getItemCount(): Int {
        return if (userList != null) {
            userList!!.size
        } else 0
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userNameTextView : TextView
        var userEmailTextView : TextView
        var userPicImage : ImageView


        init {
            userNameTextView = itemView.findViewById(R.id.userNameTextView)
            userEmailTextView = itemView.findViewById(R.id.userEmailTextView)
            userPicImage = itemView.findViewById(R.id.userPicImageView) as ImageView
        }
    }



    init {
        this.context = context
        this.userList = userList
        this.clickListener = clickListener
    }
}

