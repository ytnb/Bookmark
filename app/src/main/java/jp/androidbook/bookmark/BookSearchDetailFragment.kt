package jp.androidbook.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookSearchDetailBinding
import jp.androidbook.bookmark.viewmodels.BookSearchDetailViewModel


class BookSearchDetailFragment : Fragment() {
    private val args: BookSearchDetailFragmentArgs by navArgs()
    private val model: BookSearchDetailViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository =
                    BookDbRepository.getInstance(AppDatabase.getInstance(context!!).bookDao())
                @Suppress("UNCHECKED_CAST")
                return BookSearchDetailViewModel(repository, args.isbn) as T
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            FragmentBookSearchDetailBinding.inflate(inflater, container, false)


        binding.viewmodel = model
        binding.lifecycleOwner = this@BookSearchDetailFragment

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
