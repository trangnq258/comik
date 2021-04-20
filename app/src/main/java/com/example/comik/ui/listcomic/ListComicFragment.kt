package com.example.comik.ui.listcomic

import android.graphics.text.LineBreaker
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.comik.R
import com.example.comik.base.BaseFragment
import com.example.comik.data.model.Comic
import com.example.comik.databinding.FragmentListcomicBinding
import com.example.comik.ui.adapter.ComicAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_comic.recyclerComic
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_listcomic.*
import kotlinx.android.synthetic.main.fragment_listcomic.appBarLayout
import kotlinx.android.synthetic.main.fragment_listcomic.textDescription
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListComicFragment : BaseFragment<FragmentListcomicBinding>() {
    override val layoutResources get() = R.layout.fragment_listcomic
    override val viewModel by viewModel<ListComicViewModel>()

    private val args: ListComicFragmentArgs by navArgs()
    private val comicAdapter = ComicAdapter(::clickItemComic)

    override fun setupViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            textDescription.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
        toolbarBack.setNavigationIcon(R.drawable.ic_back)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, _ ->
            ActivityCompat.invalidateOptionsMenu(
                activity
            )
        })
    }

    override fun initData() {
        recyclerComic.adapter = comicAdapter
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            listComicVM = viewModel
        }
        viewModel.getComicsByType(args.type, args.id, args.description)
    }

    override fun initActions() {
        toolbarBack.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun clickItemComic(comic: Comic) {
        val action = ListComicFragmentDirections.actionListComicFragmentToDetailFragment(comic.id)
        findNavController().navigate(action)
    }
}
