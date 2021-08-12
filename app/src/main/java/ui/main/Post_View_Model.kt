package ui.main

import Data.Post_Client
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pojo.Post_Model
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Post_View_Model:ViewModel() {

    var MutableLiveData_Posts = MutableLiveData<List<Post_Model>>()

    fun get_Posts()
    {
        Post_Client().get_INSTANSE().get_Data().enqueue(object :retrofit2.Callback<List<Post_Model>>{
            override fun onResponse(
                call: Call<List<Post_Model>>,
                response: Response<List<Post_Model>>
            ) {
                MutableLiveData_Posts.value = response.body()
            }

            override fun onFailure(call: Call<List<Post_Model>>, t: Throwable) {

            }

        })
    }

    fun refresh()
    {
        MutableLiveData_Posts.value = arrayListOf()
    }

}