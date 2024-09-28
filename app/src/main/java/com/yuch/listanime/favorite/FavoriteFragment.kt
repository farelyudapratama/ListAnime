package com.yuch.listanime.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuch.listanime.MyApplication
import com.yuch.listanime.core.ui.AnimeAdapter
import com.yuch.listanime.core.ui.ViewModelFactory
import com.yuch.listanime.databinding.FragmentFavoriteBinding
import com.yuch.listanime.detail.DetailAnimeActivity
import javax.inject.Inject

class FavoriteFragment : Fragment() {
//    private lateinit var favoriteViewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val animeAdapter = AnimeAdapter()
            animeAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailAnimeActivity::class.java)
                intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

//            val factory = ViewModelFactory.getInstance(requireActivity())
//            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteAnime.observe(viewLifecycleOwner) { anime ->
                animeAdapter.submitList(anime)
                binding.viewEmpty.root.visibility =
                    if (anime.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvAnime) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = animeAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}