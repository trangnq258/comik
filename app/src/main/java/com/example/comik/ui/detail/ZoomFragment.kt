package com.example.comik.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.comik.R
import com.example.comik.utils.loadImage
import kotlinx.android.synthetic.main.fragment_zoom.*

const val DIMENSION_10 = 10f
const val DIMENSION_01 = .1f

class ZoomFragment : Fragment() {
    private val args: ZoomFragmentArgs by navArgs()
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_zoom, container, false)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageZoom.loadImage(args.image)
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
        view.setOnTouchListener { _: View?, event: MotionEvent? ->
            scaleGestureDetector?.onTouchEvent(event)
            true
        }
        initActions()
    }

    private fun initActions() {
        imageCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor =
                DIMENSION_01.coerceAtLeast(scaleFactor.coerceAtMost(DIMENSION_10))
            imageZoom.scaleX = scaleFactor
            imageZoom.scaleY = scaleFactor
            return true
        }
    }
}
