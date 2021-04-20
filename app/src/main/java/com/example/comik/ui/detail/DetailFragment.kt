package com.example.comik.ui.detail

import android.graphics.text.LineBreaker
import android.os.Build
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.comik.R
import com.example.comik.base.BaseFragment
import com.example.comik.data.model.Image
import com.example.comik.databinding.FragmentDetailBinding
import com.example.comik.ui.adapter.DateAdapter
import com.example.comik.ui.adapter.DetailViewPagerAdapter
import com.example.comik.ui.adapter.PosterAdapter
import com.example.comik.utils.BUNDLE.BUNDLE_CHARACTER
import com.example.comik.utils.BUNDLE.BUNDLE_CREATOR
import com.example.comik.utils.BUNDLE.BUNDLE_EVENT
import com.example.comik.utils.BUNDLE.BUNDLE_STORY
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResources get() = R.layout.fragment_detail
    override val viewModel by viewModel<DetailViewModel>()

    private val args: DetailFragmentArgs by navArgs()
    private val posterAdapter = PosterAdapter(::clickItemImage)
    private val dateAdapter = DateAdapter()

    override fun setupViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            textDescription.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
        toolbar.setNavigationIcon(R.drawable.ic_back)
        appBarLayout.addOnOffsetChangedListener(OnOffsetChangedListener { _, _ ->
            invalidateOptionsMenu(
                activity
            )
        })
        setAdapter()
        setTabsWithViewPager()
    }

    override fun initData() {
        recyclerPoster.adapter = posterAdapter
        PagerSnapHelper().attachToRecyclerView(recyclerPoster)
        scrollingBanner.attachToRecyclerView(recyclerPoster)
        recyclerDate.adapter = dateAdapter
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            detailVM = viewModel
        }
        viewModel.initDataDetail(args.idComic)
    }

    override fun initActions() {
        toolbar.setOnClickListener {
            findNavController().popBackStack()
        }
        imageButtonFavorite.setOnClickListener {
            viewModel.updateFavorite()
        }
    }

    private fun setAdapter() {
        DetailViewPagerAdapter(childFragmentManager).apply {
            addFragment(
                DetailViewPagerAdapter.FragmentHolder(
                    DetailFilterByComic.getInstance(BUNDLE_CHARACTER, args.idComic),
                    getString(R.string.text_character)
                )
            )
            addFragment(
                DetailViewPagerAdapter.FragmentHolder(
                    DetailFilterByComic.getInstance(BUNDLE_EVENT, args.idComic),
                    getString(R.string.text_event)
                )
            )
            addFragment(
                DetailViewPagerAdapter.FragmentHolder(
                    DetailFilterByComic.getInstance(BUNDLE_CREATOR, args.idComic),
                    getString(R.string.text_creator)
                )
            )
            addFragment(
                DetailViewPagerAdapter.FragmentHolder(
                    DetailFilterByComic.getInstance(BUNDLE_STORY, args.idComic),
                    getString(R.string.text_story)
                )
            )
            viewPagerComic.adapter = this
        }
    }

    private fun setTabsWithViewPager() {
        tabComic.setupWithViewPager(viewPagerComic)
    }

    private fun clickItemImage(image: Image) {
        val action = DetailFragmentDirections.actionDetailFragmentToZoomFragment(image.path)
        findNavController().navigate(action)
    }
}
