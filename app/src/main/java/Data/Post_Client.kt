package Data

import pojo.Post_Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Post_Client {

    var BASEURL:String = "http://jsonplaceholder.typicode.com/"
    var INSTANSE:Post_Client? = null
    var Post_Interface:Post_Interface? = null

    constructor()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        Post_Interface = retrofit.create(Data.Post_Interface::class.java)

    }

    fun get_INSTANSE():Post_Client
    {
        if (null == INSTANSE)
        {
            INSTANSE = Post_Client()
        }
        return INSTANSE!!
    }

    fun get_Data():Call<List<Post_Model>>
    {
        return Post_Interface!!.get_posts()
    }

}