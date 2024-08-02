package com.osieljunior.bdatividade1907.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.osieljunior.bdatividade1907.R
import com.osieljunior.bdatividade1907.baseclasses.Item
import com.osieljunior.bdatividade1907.baseclasses.StoreAdapter
import com.osieljunior.bdatividade1907.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerViewStores: RecyclerView
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var database: DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerViewStores = root.findViewById(R.id.recyclerViewStores)
        recyclerViewStores.layoutManager = LinearLayoutManager(context)

        database = FirebaseDatabase.getInstance().reference

        fetchStores()


        return root
    }



    private fun fetchStores() {
        database.child("stores").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val storeList = mutableListOf<Item>()
                for (storeSnapshot in snapshot.children) {
                    val store = storeSnapshot.getValue(Item::class.java)
                    store?.let { storeList.add(it) }
                }
                storeAdapter = StoreAdapter(storeList)
                recyclerViewStores.adapter = storeAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load stores: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}