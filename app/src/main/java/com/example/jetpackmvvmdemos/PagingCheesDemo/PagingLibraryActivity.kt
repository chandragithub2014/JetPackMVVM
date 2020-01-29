package com.example.jetpackmvvmdemos.PagingCheesDemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.R
import kotlinx.android.synthetic.main.paging_main_activity_layout.*
import kotlinx.android.synthetic.main.room_db_info_list_fragment_layout.view.*

class PagingLibraryActivity : AppCompatActivity() {
    private val viewModel by viewModels<CheeseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paging_main_activity_layout)

        // Create adapter for the RecyclerView
        cheeseList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val adapter = CheeseAdapter()
        cheeseList.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
      //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        viewModel.allCheeses.observe(this, androidx.lifecycle.Observer{
            adapter.submitList(it)
        })

       // initAddButtonListener()
        //initSwipeToDelete()
    }

    /*private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            // enable the items to swipe to the left or right
            override fun getMovementFlags(recyclerView: RecyclerView,
                                          viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            // When an item is swiped, remove the item via the view model. The list item will be
            // automatically removed in response, because the adapter is observing the live list.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as CheeseViewHolder).cheese?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(cheeseList)
    }*/

    private fun addCheese() {
        val newCheese = inputText.text.trim()
        if (newCheese.isNotEmpty()) {
            viewModel.insert(newCheese)
            inputText.setText("")
        }
    }

    /*private fun initAddButtonListener() {
        addButton.setOnClickListener {
            addCheese()
        }

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                return@setOnEditorActionListener true
            }
            false // action that isn't DONE occurred - ignore
        }
        // When the user clicks on the button, or presses enter, save the item.
        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false // event that isn't DOWN or ENTER occurred - ignore
        }
    }*/
}