package jp.androidbook.bookmark


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import jp.androidbook.bookmark.data.db.AppDatabase
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.databinding.FragmentBookListDetailBinding
import jp.androidbook.bookmark.viewmodels.BookListDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_list_detail.*


class BookListDetailFragment : Fragment() {
    private val model: BookListDetailViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository =
                    BookDbRepository.getInstance(AppDatabase.getInstance(context!!).bookDao())
                @Suppress("UNCHECKED_CAST")
                return BookListDetailViewModel(repository, args.id) as T
            }
        }
    })
    private val args: BookListDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookListDetailBinding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_book_list_detail,
            container,
            false
        )

        binding.viewmodel = model
        binding.lifecycleOwner = this@BookListDetailFragment

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.id

        btn_list_detail.setOnClickListener {
            model.deleteBook(id)
            // TODO fabとsnackbarが重なって表示されている
            Snackbar.make(it, getString(R.string.remove_book_message), Snackbar.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

    }


}
