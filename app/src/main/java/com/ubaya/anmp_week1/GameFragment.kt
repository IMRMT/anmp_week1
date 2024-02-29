package com.ubaya.anmp_week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmp_week1.databinding.FragmentGameBinding
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.textView.text = "$name's Turn"
        }

        val leftNumber = Random.nextInt(20)
        val rightNumber = Random.nextInt(20)

        var ans = (leftNumber + rightNumber).toString()
        var score = 0

        binding.txtLeftNumber.text = leftNumber.toString()
        binding.txtRightNumber.text = rightNumber.toString()


        binding.btnSubmit.setOnClickListener {
            val answer = binding.txtSubmit.text.toString()

            if (ans.toString() == answer){
                binding.txtSubmit.text.clear()
                val leftNumber = Random.nextInt(20)
                val rightNumber = Random.nextInt(20)
                binding.txtLeftNumber.text = leftNumber.toString()
                binding.txtRightNumber.text = rightNumber.toString()
                ans = (leftNumber + rightNumber).toString()

                score += 1
            }
            else {
                val action = GameFragmentDirections.actionGameOverFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }

//        binding.btnBack.setOnClickListener {
//            val action = GameFragmentDirections.actionMainfragment()
//            Navigation.findNavController(it).navigate(action)
//        }
    }
}