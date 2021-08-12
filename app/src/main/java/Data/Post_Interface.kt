package Data

import pojo.Post_Model
import retrofit2.Call
import retrofit2.http.GET

interface Post_Interface {
    @GET("/posts")
    fun get_posts():Call<List<Post_Model>>
}