package jp.androidbook.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import jp.androidbook.bookmark.adapters.BookListAdapter
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookListBinding
import jp.androidbook.bookmark.viewmodels.BookListViewModel


class BookListFragment : Fragment() {
    lateinit var adapter: BookListAdapter
    private val model: BookListViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository =
                    BookDbRepository.getInstance(AppDatabase.getInstance(context!!).BookDao())
                @Suppress("UNCHECKED_CAST")
                return BookListViewModel(repository) as T
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false)

        adapter = BookListAdapter()
        binding.booklistRecyclerview.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.booklistRecyclerview.adapter = adapter

        model.bookLists.observe(viewLifecycleOwner, Observer { bookEntity ->
            if (bookEntity != null) {
                adapter.submitList(bookEntity)
            }
        })

        binding.viewmodel = model
        binding.lifecycleOwner = this@BookListFragment

        binding.booklistFab.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookSearchFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }


}
