package com.example.comik.ui.seemore

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.comik.R
import com.example.comik.base.BaseFragment
import com.example.comik.data.model.*
import com.example.comik.databinding.FragmentSeemoreBinding
import com.example.comik.ui.adapter.MultiTypeAdapter
import com.example.comik.utils.BUNDLE.BUNDLE_CHARACTER
import com.example.comik.utils.BUNDLE.BUNDLE_CREATOR
import com.example.comik.utils.BUNDLE.BUNDLE_EVENT
import com.example.comik.utils.BUNDLE.BUNDLE_SERIES
import com.example.comik.utils.BUNDLE.BUNDLE_STORY
import kotlinx.android.synthetic.main.fragment_seemore.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SeeMoreFragment : BaseFragment<FragmentSeemoreBinding>() {
    override val layoutResources get() = R.layout.fragment_seemore
    override val viewModel by viewModel<SeeMoreViewModel>()

    private val args: SeeMoreFragmentArgs by navArgs()

    override fun setupViews() {
    }

    override fun initData() {
        val multiTypeAdapter = when (args.type) {
            BUNDLE_CHARACTER -> MultiTypeAdapter(::clickItemCharacter)
            BUNDLE_SERIES -> MultiTypeAdapter(::clickItemSeries)
            BUNDLE_EVENT -> MultiTypeAdapter(::clickItemEvent)
            BUNDLE_STORY -> MultiTypeAdapter(::clickItemStory)
            else -> MultiTypeAdapter(::clickItemCreator)
        }
        recyclerSeeMore.adapter = multiTypeAdapter
        recyclerSeeMore.startLayoutAnimation()
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            seeMoreVM = viewModel
        }
        viewModel.getSeeMore(args.type)
    }

    override fun initActions() {
        imageButtonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickItemCharacter(character: Character) {
        if (character.description == null) {
            character.description = ""
        }
        directToListComicFragment(BUNDLE_CHARACTER, character.id, character.description)
    }

    private fun clickItemEvent(event: Event) {
        if (event.description == null) {
            event.description = ""
        }
        directToListComicFragment(BUNDLE_EVENT, event.id, event.description)
    }

    private fun clickItemStory(story: Story) {
        if (story.description == null) {
            story.description = ""
        }
        directToListComicFragment(BUNDLE_STORY, story.id, story.description)
    }

    private fun clickItemCreator(creator: Creator) {
        directToListComicFragment(BUNDLE_CREATOR, creator.id, "")
    }

    private fun clickItemSeries(series: Series) {
        if (series.description == null) {
            series.description = ""
        }
        directToListComicFragment(BUNDLE_SERIES, series.id, series.description)
    }

    private fun directToListComicFragment(type: String, id: Int, description: String) {
        val action = SeeMoreFragmentDirections.actionSeeMoreFragmentToListComicFragment(
            id,
            type,
            description
        )
        findNavController().navigate(action)
    }
}
