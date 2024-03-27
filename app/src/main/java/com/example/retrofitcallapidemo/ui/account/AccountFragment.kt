package com.example.retrofitcallapidemo.ui.account

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.retrofitcallapidemo.Constance.IMAGE_REQUEST_CODE
import com.example.retrofitcallapidemo.Constance.REQUEST_IMAGE_CAPTURE
import com.example.retrofitcallapidemo.databinding.FragmentAccountBinding
import java.io.ByteArrayOutputStream

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindView()
        onClickListener()
    }

    private fun initData() {
        sharedPreferences = context?.getSharedPreferences("saveImage", Context.MODE_PRIVATE)!!
    }

    private fun bindView() {
        val uriString = sharedPreferences.getString("uri", null)
        val uriImage = uriString?.let { Uri.parse(it) }
        binding.imgUser.setImageURI(uriImage)
    }

    private fun onClickListener() {
        binding.tvGallery.setOnClickListener {
            pickImageGallery()
        }
        binding.tvCamera.setOnClickListener {
            takePictureCamera()
        }
    }

    private fun takePictureCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Log.e("232323", "Error: ${e.localizedMessage}")
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            saveUri(data?.data)
            Log.d("232323", "uriGallery: ${data?.data}")
            binding.imgUser.setImageURI(data?.data)
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val imageUri = getImageUri(requireContext(), imageBitmap)
            Log.d("232323", "uriCamera: $imageUri")
            saveUri(imageUri)
            binding.imgUser.setImageURI(imageUri)
        }
    }

    private fun getImageUri(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }

    private fun saveUri(uri: Uri?) {
        val editor = sharedPreferences.edit()
        editor.putString("uri", "$uri")
        editor.apply()
    }
}