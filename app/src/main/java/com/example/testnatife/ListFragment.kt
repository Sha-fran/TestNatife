package com.example.testnatife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnatife.databinding.ListFragmentLayoutBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragment : Fragment(), GifRVAdapter.OnItemClickListener{
    private lateinit var binding: ListFragmentLayoutBinding
    private var onItemClick:(item: Data) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = GifRVAdapter(onItemClickListener = this)
        binding.gifRecyclerWiew.adapter = adapter
        binding.gifRecyclerWiew.layoutManager = LinearLayoutManager(requireContext())

        val gifViewModel:GifViewModel = ViewModelProvider(this).get(GifViewModel::class.java)

        gifViewModel.uiStateLiveData.observe(viewLifecycleOwner) {uiState ->
            when(uiState) {
                is UIState.EmptyList -> Unit
                is UIState.FilledList -> {
                    adapter.items = uiState.list
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(item: Data) {
        onItemClick.invoke(item)
    }

    fun setItemClickListener(lambda: (item: Data)-> Unit) {
        onItemClick = lambda
    }
}
