package jp.androidbook.bookmark


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookSearchDetailBinding
import jp.androidbook.bookmark.viewmodels.BookSearchDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_search_detail.*


class BookSearchDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val isbn = BookSearchDetailFragmentArgs.fromBundle(arguments).isbn

        val binding: FragmentBookSearchDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_search_detail, container, false)

        val model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = BookDbRepository.getInstance(AppDatabase.getInstance(context!!).BookDao())
                @Suppress("UNCHECKED_CAST")
                return BookSearchDetailViewModel(repository, isbn) as T
            }
        }).get(BookSearchDetailViewModel::class.java)

        binding.viewmodel = model
        binding.setLifecycleOwner(this@BookSearchDetailFragment)

        binding.btnSearchDetail.setOnClickListener {
            model.createBook()
            Snackbar.make(it, getString(R.string.add_book_message), Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
