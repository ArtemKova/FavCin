package com.ka.favcin.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ka.favcin.R

class UserFragment : Fragment() {

    private lateinit var notificationsViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.name)
        val textView1: TextView = root.findViewById(R.id.textNameEdit)
        val textView2: TextView = root.findViewById(R.id.textMailView)
        val textView3: TextView = root.findViewById(R.id.textMail)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView1.text = it
        })
            notificationsViewModel.text1.observe(viewLifecycleOwner, Observer {
            textView2.text = it
            textView3.text = it

        })

        return root
    }
}