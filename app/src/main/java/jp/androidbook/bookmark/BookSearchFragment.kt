package jp.androidbook.bookmark


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import jp.androidbook.bookmark.adapters.BookSearchAdapter
import jp.androidbook.bookmark.data.api.BookApiRepository
import jp.androidbook.bookmark.data.api.GoogleBookClients
import jp.androidbook.bookmark.databinding.FragmentBookSearchBinding
import jp.androidbook.bookmark.viewmodels.BookSearchViewModel


class BookSearchFragment : Fragment() {
    lateinit var searchView: SearchView
    lateinit var adapter: BookSearchAdapter
    private val model: BookSearchViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = BookApiRepository(GoogleBookClients)
                @Suppress("UNCHECKED_CAST")
                return BookSearchViewModel(repository) as T
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookSearchBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        adapter = BookSearchAdapter()
        binding.booksearchRecyclerview.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.booksearchRecyclerview.adapter = adapter

        model.bookSearchResult.observe(viewLifecycleOwner, Observer { items ->
            if (items != null) {
                adapter.submitList(items)
                model.finishedLoading()
            }
        })

        binding.viewmodel = model
        binding.lifecycleOwner = this@BookSearchFragment


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.serach_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        menu.run {
            searchView = findItem(R.id.app_bar_search).actionView as SearchView
            searchView.also {
                it.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
                it.setIconifiedByDefault(false)
                it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.d("BookSearchFragment", "query is $query")
                        model.setTitle(query)
                        searchView.clearFocus()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }
        }
    }

}
