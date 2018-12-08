package jp.androidbook.bookmark


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_book_search_detail.*


class BookSearchDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO apiからisbnでデータ引っ張ってくる

        return inflater.inflate(R.layout.fragment_book_search_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO テスト用
        val isbn = BookSearchDetailFragmentArgs.fromBundle(arguments).isbn
        textView8.text = isbn
    }
}
