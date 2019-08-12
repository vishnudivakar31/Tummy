package com.wanderingthinkter.tummy


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanderingthinkter.tummy.recycler_adapters.HomeFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var layoutManager:LinearLayoutManager? = null
    var adapter:HomeFragmentAdapter? = null

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

        updateRecipes()
    }

    private fun updateRecipes() {
        // Fetch Recipes
        // Set recipes on adapter
    }
}
