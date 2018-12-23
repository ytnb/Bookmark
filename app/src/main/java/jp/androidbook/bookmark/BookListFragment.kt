package jp.androidbook.bookmark


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import jp.androidbook.bookmark.adapters.BookListAdapter
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookListBinding
import jp.androidbook.bookmark.viewmodels.BookListViewModel


class BookListFragment : Fragment() {
    lateinit var adapter: BookListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentBookListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false)

        adapter = BookListAdapter()
        binding.booklistRecyclerview.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.booklistRecyclerview.adapter = adapter

        val model: BookListViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = BookDbRepository.getInstance(AppDatabase.getInstance(context!!).BookDao())
                @Suppress("UNCHECKED_CAST")
                return BookListViewModel(repository) as T
            }
        }).get(BookListViewModel::class.java)

        model.bookLists.observe(this, Observer { bookEntity ->
            if (bookEntity != null) {
                adapter.submitList(bookEntity)
            }
        })

        binding.viewmodel = model
        binding.setLifecycleOwner(this@BookListFragment)

        binding.booklistFab.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookSearchFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }


}
