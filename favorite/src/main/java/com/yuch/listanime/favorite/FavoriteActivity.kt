package com.yuch.listanime.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuch.listanime.core.Resource
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.core.ui.AnimeAdapter
import com.yuch.listanime.detail.DetailAnimeActivity
import com.yuch.listanime.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var animeAdapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite"

        loadKoinModules(favoriteModule)

        animeAdapter = AnimeAdapter()
        animeAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailAnimeActivity::class.java)
            intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding.rvAnime) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        favoriteViewModel.favoriteAnime.removeObservers(this)
        favoriteViewModel.favoriteAnime.observe(this) { anime ->
            animeAdapter.submitList(anime)
            binding.viewEmpty.visibility =
                if (anime.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

}
