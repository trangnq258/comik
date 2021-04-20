package com.example.comik.ui.home

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.comik.R
import com.example.comik.data.model.Event
import com.example.comik.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.comik.base.BaseFragment
import com.example.comik.data.model.Comic
import com.example.comik.data.model.Creator
import com.example.comik.ui.adapter.BannerAdapter
import com.example.comik.ui.adapter.CreatorAdapter
import com.example.comik.ui.adapter.EventAdapter
import com.example.comik.utils.BUNDLE.BUNDLE_CHARACTER
import com.example.comik.utils.BUNDLE.BUNDLE_CREATOR
import com.example.comik.utils.BUNDLE.BUNDLE_EVENT
import com.example.comik.utils.BUNDLE.BUNDLE_SERIES
import com.example.comik.utils.BUNDLE.BUNDLE_STORY
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener {
    override val layoutResources get() = R.layout.fragment_home
    override val viewModel by viewModel<HomeViewModel>()

    private val eventAdapter = EventAdapter(::clickItemEvent)
    private val creatorAdapter = CreatorAdapter(::clickItemCreator)
    private val bannerAdapter = BannerAdapter(::clickItemBanner)

    override fun setupViews() {
    }

    override fun initData() {
        recyclerEvent.adapter = eventAdapter
        recyclerCreator.adapter = creatorAdapter
        recyclerBanner.adapter = bannerAdapter
        PagerSnapHelper().attachToRecyclerView(recyclerBanner)
        scrollingBanner.attachToRecyclerView(recyclerBanner)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
        }
    }

    override fun initActions() {
        imageButtonEventNext.setOnClickListener(this)
        imageButtonCreatorNext.setOnClickListener(this)
        imageButtonCharacter.setOnClickListener(this)
        imageButtonStories.setOnClickListener(this)
        imageButtonSeries.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view) {
            imageButtonEventNext -> directFragment(BUNDLE_EVENT)
            imageButtonCreatorNext -> directFragment(BUNDLE_CREATOR)
            imageButtonCharacter -> directFragment(BUNDLE_CHARACTER)
            imageButtonStories -> directFragment(BUNDLE_STORY)
            imageButtonSeries -> directFragment(BUNDLE_SERIES)
        }
    }

    private fun directFragment(bundle: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToSeeMoreFragment(bundle)
        findNavController().navigate(action)
    }

    private fun clickItemEvent(event: Event) {
        if (event.description == null) {
            event.description = ""
        }
        val action =
            HomeFragmentDirections.actionHomeFragmentToListComicFragment(
                event.id,
                BUNDLE_EVENT,
                event.description
            )
        findNavController().navigate(action)
    }

    private fun clickItemCreator(creator: Creator) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToListComicFragment(
                creator.id,
                BUNDLE_CREATOR,
                ""
            )
        findNavController().navigate(action)
    }

    private fun clickItemBanner(comic: Comic) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(comic.id)
        findNavController().navigate(action)
    }
}
