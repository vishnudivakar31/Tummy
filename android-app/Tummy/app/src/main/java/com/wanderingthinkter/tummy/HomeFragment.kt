package com.wanderingthinkter.tummy


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.wanderingthinkter.tummy.api.RestApi
import com.wanderingthinkter.tummy.api.Routes
import com.wanderingthinkter.tummy.appfactorylib.CredentialHelper
import com.wanderingthinkter.tummy.entities.Recipe
import com.wanderingthinkter.tummy.recycler_adapters.HomeFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray

class HomeFragment : Fragment() {

    var layoutManager:LinearLayoutManager? = null
    var adapter:HomeFragmentAdapter? = null
    val gson = Gson()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()

        layoutManager = LinearLayoutManager(context)
        homeRecyclerView.layoutManager = layoutManager

        refreshButton.setOnClickListener {
            val snackbar = Snackbar.make(it, getString(R.string.refresh_label), Snackbar.LENGTH_LONG)
            snackbar.show()
            updateRecipes()
        }

        updateRecipes()
    }

    private fun updateRecipes() {
        var restApi = context?.let { RestApi(it) }
        val listenerFun = Listener<JSONArray> { response ->
            if(response.length() > 0) {
                emptyTxt.visibility = View.GONE
                val recipeList = gson.fromJson(response.toString(), Array<Recipe>::class.java).toList()
                adapter = HomeFragmentAdapter(recipeList)
                homeRecyclerView.adapter = adapter
            } else {
                emptyTxt.visibility = View.VISIBLE
            }
        }
        val errorFun = Response.ErrorListener {
            if(it.networkResponse.statusCode == 401) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            } else {
                val snackbar = Snackbar.make(this!!.view!!, getString(R.string.server_busy), Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
        restApi?.requestArray(Request.Method.GET, Routes.HOME_URL, null, listenerFun, errorFun)
    }
}
