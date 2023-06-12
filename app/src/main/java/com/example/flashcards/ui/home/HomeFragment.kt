package com.example.flashcards.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.flashcards.MyApp
import com.example.flashcards.databinding.FragmentHomeBinding
import com.example.flashcards.ui.NoteViewModel
import com.example.flashcards.ui.NoteViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory((requireActivity().application as MyApp).database.noteDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}