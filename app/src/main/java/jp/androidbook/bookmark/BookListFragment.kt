package jp.androidbook.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import jp.androidbook.bookmark.adapters.BookListAdapter
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookListBinding
import jp.androidbook.bookmark.viewmodels.BookListViewModel


class BookListFragment : Fragment() {
    lateinit var adapter: BookListAdapter
    private lateinit var viewDataBinding: FragmentBookListBinding
    private val viewModel: BookListViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository =
                    BookDbRepository.getInstance(AppDatabase.getInstance(context!!).bookDao())
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
        viewDataBinding = FragmentBookListBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupButton()
        setupListAdapter()
    }

    private fun setupButton() {
        viewDataBinding.booklistFab.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookSearchFragment()
            findNavController().navigate(action)
        }

        viewModel.openTaskEvent.observe(viewLifecycleOwner, EventObserver {
            val action =
                BookListFragmentDirections.actionBookListFragmentToBookListDetailFragment(it)
            findNavController().navigate(action)
        })
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = BookListAdapter(viewModel)
            viewDataBinding.booklistRecyclerview.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
            viewDataBinding.booklistRecyclerview.adapter = adapter
        }
    }
}
