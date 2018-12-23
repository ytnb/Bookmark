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
import androidx.navigation.fragment.findNavController
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookListDetailBinding
import jp.androidbook.bookmark.viewmodels.BookListDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_list_detail.*


class BookListDetailFragment : Fragment() {
    lateinit var model: BookListDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = BookListDetailFragmentArgs.fromBundle(arguments).id

        val binding: FragmentBookListDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_book_list_detail,
            container,
            false
        )

        model = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = BookDbRepository.getInstance(AppDatabase.getInstance(context!!).BookDao())
                @Suppress("UNCHECKED_CAST")
                return BookListDetailViewModel(repository, id) as T
            }
        }).get(BookListDetailViewModel::class.java)

        binding.viewmodel = model
        binding.setLifecycleOwner(this@BookListDetailFragment)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = BookListDetailFragmentArgs.fromBundle(arguments).id

        btn_list_detail.setOnClickListener {
            model.deleteBook(id)
            // TODO fabとsnackbarが重なって表示されている
            Snackbar.make(it, getString(R.string.remove_book_message), Snackbar.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

    }


}
