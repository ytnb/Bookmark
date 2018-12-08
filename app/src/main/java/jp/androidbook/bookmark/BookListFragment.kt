package jp.androidbook.bookmark


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import jp.androidbook.bookmark.databinding.FragmentBookListBinding


class BookListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentBookListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_list, container, false)

        binding.booklistFab.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookSearchFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }


}
