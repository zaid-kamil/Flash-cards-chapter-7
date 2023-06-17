package com.example.flashcards.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.flashcards.MyApp
import com.example.flashcards.R
import com.example.flashcards.adapter.NoteAdapter
import com.example.flashcards.data.Note
import com.example.flashcards.databinding.FragmentHomeBinding
import com.example.flashcards.ui.NoteViewModel
import com.example.flashcards.ui.NoteViewModelFactory
import kotlinx.coroutines.launch

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val notesRecyclerView = binding.notesRecyclerView
        notesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        val adapter = NoteAdapter { viewNote(it) }
        notesRecyclerView.adapter = adapter
        lifecycle.coroutineScope.launch {
            viewModel.allNotes.collect() {
                adapter.submitList(it)
            }
        }
    }

    private fun viewNote(note: Note) {
        viewModel.select(note)
        findNavController().navigate(R.id.action_navigation_home_to_viewNoteFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}