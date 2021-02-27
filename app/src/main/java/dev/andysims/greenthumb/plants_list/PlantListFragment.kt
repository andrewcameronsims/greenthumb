package dev.andysims.greenthumb.plants_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.andysims.greenthumb.databinding.FragmentPlantListBinding

class PlantListFragment : Fragment() {
    private var _binding: FragmentPlantListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlantListViewModel by lazy {
        ViewModelProvider(this).get(PlantListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantListBinding.inflate(inflater, container, false)

        viewModel.plants.observe(viewLifecycleOwner, { plants ->
            binding.yeah.text = "${plants.size.toString()} plants retrieved"
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}