package jp.androidbook.bookmark


import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import jp.androidbook.bookmark.adapters.BookSearchAdapter
import jp.androidbook.bookmark.data.Items
import jp.androidbook.bookmark.databinding.FragmentBookSearchBinding
import jp.androidbook.bookmark.viewmodels.BookSearchViewModel


class BookSearchFragment : Fragment() {
    lateinit var searchView: SearchView
    lateinit var adapter: BookSearchAdapter
    lateinit var model: BookSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentBookSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_search, container, false)

        setHasOptionsMenu(true)

        adapter = BookSearchAdapter()
        binding.booksearchRecyclerview.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.booksearchRecyclerview.adapter = adapter

        model = ViewModelProviders.of(this).get(BookSearchViewModel::class.java)
        model.bookSearchResult.observe(this, Observer { book ->
            if (book != null) {
                adapter.submitList(book.items)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.serach_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        menu?.run {
            searchView = findItem(R.id.app_bar_search).actionView as SearchView
            searchView.also {
                it.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
                it.setIconifiedByDefault(false)
                it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.d("BookSearchFragment", "query is $query")
                        // TODO ↓不要？？
                        activity?.actionBar?.title = query
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
