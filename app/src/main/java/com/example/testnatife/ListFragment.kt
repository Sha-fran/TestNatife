package com.example.testnatife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        val api = ApiClient.client.create(ApiInterface::class.java)
        val adapter = GifRVAdapter(onItemClickListener = this)

        api.getGif()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.items = it.data
                adapter.notifyDataSetChanged()
                binding.gifRecyclerWiew.adapter = adapter
            },
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                })
        binding.gifRecyclerWiew.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(item: Data) {
        onItemClick.invoke(item)
    }

    fun setItemClickListener(lambda: (item: Data)-> Unit) {
        onItemClick = lambda
    }
}
