package com.wanderingthinkter.tummy


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_recipe_finder.*


class RecipeFinderFragment : Fragment() {

    var ingredientsCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_finder, container, false)
    }

    override fun onResume() {
        super.onResume()
        addIngredients.setOnClickListener {
            val linearLayout = ingredientsLayout
            val editText = EditText(context)
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0,0, 0, 10)
            editText.layoutParams = layoutParams
            editText.hint = getString(R.string.ingredients_txt)
            editText.textSize = 14f
            editText.id = ++ingredientsCount
            linearLayout.addView(editText)
        }
    }

}
