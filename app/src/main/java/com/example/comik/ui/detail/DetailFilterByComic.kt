package com.example.comik.ui.detail

import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.comik.R
import com.example.comik.base.BaseFragment
import com.example.comik.data.model.*
import com.example.comik.databinding.FragmentDetailFilterByComicBinding
import com.example.comik.ui.adapter.MultiTypeAdapter
import com.example.comik.utils.BUNDLE
import kotlinx.android.synthetic.main.fragment_detail_filter_by_comic.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilterByComic : BaseFragment<FragmentDetailFilterByComicBinding>() {
    override val layoutResources get() = R.layout.fragment_detail_filter_by_comic
    override val viewModel by viewModel<DetailViewModel>()

    override fun setupViews() {
    }

    override fun initData() {
        var type = ""
        var idComic = 0
        arguments?.let {
            type = it.getString(BUNDLE_TYPE).toString()
            idComic = it.getInt(BUNDLE_ID_COMIC)
        }
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            detailVM = viewModel
        }
        val multiTypeAdapter = when (type) {
            BUNDLE.BUNDLE_CHARACTER -> MultiTypeAdapter(::clickItemCharacter)
            BUNDLE.BUNDLE_EVENT -> MultiTypeAdapter(::clickItemEvent)
            BUNDLE.BUNDLE_STORY -> MultiTypeAdapter(::clickItemStory)
            else -> MultiTypeAdapter(::clickItemCreator)
        }
        recyclerDetailFilterByComic.adapter = multiTypeAdapter
        idComic.let { viewModel.getDetailFilterByComic(type, it) }
    }

    override fun initActions() {
    }

    private fun clickItemCharacter(character: Character) {
        directToListComicFragment(BUNDLE.BUNDLE_CHARACTER, character.id, character.description)
    }

    private fun clickItemEvent(event: Event) {
        event.description?.let { directToListComicFragment(BUNDLE.BUNDLE_EVENT, event.id, it) }
    }

    private fun clickItemStory(story: Story) {
        story.description?.let { directToListComicFragment(BUNDLE.BUNDLE_STORY, story.id, it) }
    }

    private fun clickItemCreator(creator: Creator) {
        directToListComicFragment(BUNDLE.BUNDLE_CREATOR, creator.id, "")
    }

    private fun directToListComicFragment(type: String, id: Int, description: String) {
        val action =
            DetailFragmentDirections.actionDetailFragmentToListComicFragment(id, type, description)
        findNavController().navigate(action)
    }

    companion object {
        private const val BUNDLE_TYPE = "BUNDLE_TYPE"
        private const val BUNDLE_ID_COMIC = "BUNDLE_ID_COMIC"

        fun getInstance(type: String, idComic: Int) = DetailFilterByComic().apply {
            arguments = bundleOf(
                BUNDLE_TYPE to type,
                BUNDLE_ID_COMIC to idComic
            )
        }
    }
}
