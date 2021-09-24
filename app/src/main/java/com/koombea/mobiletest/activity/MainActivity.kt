package com.koombea.mobiletest.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koombea.mobiletest.R
import com.koombea.mobiletest.adapter.UserListAdapter
import com.koombea.mobiletest.model.UserModel
import com.koombea.mobiletest.network.APIService
import com.koombea.mobiletest.network.RetrofitInstance
import com.koombea.mobiletest.viewmodel.UserListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class MainActivity : AppCompatActivity() {
    private var userModelList : List<UserModel?>? = null
    private lateinit var userListAdapter : UserListAdapter
    private lateinit var userViewModel : UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView : RecyclerView = findViewById(R.id.mainRecyclerView)
        var noResult : TextView = findViewById(R.id.noResultTextView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        userListAdapter = UserListAdapter(this, userModelList, this)
        recyclerView.adapter = userListAdapter

        userViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userViewModel.getUsersListObserver()?.observe(this, object : Observer<List<UserModel?>?> {

            @Suppress("INAPPLICABLE_JVM_NAME")
            @JvmName("onChanged1")
            override fun onChanged(userModels: List<UserModel?>?) {
                if (userModels != null) {
                    userModelList = userModels
                    userListAdapter.setUserList(userModels)
                    recyclerView.visibility = View.VISIBLE
                    noResult.setVisibility(View.GONE)
                } else {
                    noResult.text = "There is an error trying to show users and their posts"
                    noResult.setVisibility(View.VISIBLE)
                    recyclerView.visibility = View.GONE
                }
            }
        })

        userViewModel.makeApiCall()

    }



    fun onUserClick(userModel: UserModel?) {

    }
}

