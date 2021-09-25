package com.ybennun.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)

        find_button.setOnClickListener {

            val word = word_edit_tex.text
            val apiKey = "25c1bbbe-665e-426d-b5c7-9f7050cf6b41"
            var url =
                "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

            val stringRequest =
                StringRequest(Request.Method.GET, url, { response ->
                    extractDefinitionFromJason(response)
                },
                    { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                    }
                )
            queue.add(stringRequest)
        }
    }

    private fun extractDefinitionFromJason(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)
    }
}