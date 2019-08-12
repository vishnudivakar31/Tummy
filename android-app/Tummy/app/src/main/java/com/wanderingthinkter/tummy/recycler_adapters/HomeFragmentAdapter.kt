package com.wanderingthinkter.tummy.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wanderingthinkter.tummy.R
import com.wanderingthinkter.tummy.entities.Recipe
import kotlinx.android.synthetic.main.home_fragment_item.view.*

class HomeFragmentAdapter(val recipes: List<Recipe>) : RecyclerView.Adapter<HomeFragmentAdapter.RecipeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.home_fragment_item, parent, false))
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bindRecipe(recipes[position])
    }

    class RecipeHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        var view: View = v
        lateinit var recipe: Recipe

        init {
            v.setOnClickListener(this)
        }

        fun bindRecipe(recipe: Recipe) {
            this.recipe = recipe
            view.recipeTitleTxt.text = recipe.title
            view.recipeCusineTxt.text = recipe.cuisine
            view.likesTxt.text = "${recipe.likes} likes"
            view.ratingsTxt.text = "${recipe.avgRatings} ratings"
        }

        override fun onClick(p0: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}