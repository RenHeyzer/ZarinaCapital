package com.ren.menu.internal.presentation.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>(R.layout.fragment_edit_profile) {

    override val binding: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)
    override val viewModel: EditProfileViewModel by viewModels()

    private var selectedImageUri: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribeToProfile()
    }

    private fun initialize() {
        setupTextWatchers()
        setupSaveButton()
    }

    private fun setupTextWatchers() {
        binding.etName.addTextChangedListener(createErrorClearingTextWatcher(binding.tilName))
        binding.etNumber.addTextChangedListener(createErrorClearingTextWatcher(binding.tilPhone))
        binding.etEmail.addTextChangedListener(createErrorClearingTextWatcher(binding.tilEmail))
    }

    private fun createErrorClearingTextWatcher(layout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                layout.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val username = binding.etName.text.toString().trim()
            val prefix = binding.tilPhone.prefixText.toString().trim()
            val phone = binding.etNumber.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (validateInput(username, prefix, phone, email)) {
                Log.d(
                    "EditProfileFragment",
                    "Updating profile with data: username=$username, prefix=$prefix, phone=$phone, email=$email, avatar=$selectedImageUri"
                )

                viewModel.updateProfile(
                    data = PUTProfile(
                        username = username,
                        prefix = prefix,
                        phone = phone,
                        email = email,
                        avatar = selectedImageUri
                    )
                )
            }
        }
        binding.imProfile.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            selectedImageUri = imageUri.toString()
            binding.imProfile.setImageURI(imageUri)
        }
    }

    private fun validateInput(
        username: String,
        prefix: String,
        phone: String,
        email: String
    ): Boolean {
        var isValid = true

        if (username.isEmpty()) {
            binding.tilName.error = "Заполните поле"
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if (phone.isEmpty()) {
            binding.tilPhone.error = "Заполните поле"
            isValid = false
        } else {
            val phonePattern = "^[0-9]{9}$".toRegex()
            if (!phonePattern.matches(phone)) {
                binding.tilPhone.error = "Неверный формат номера телефона"
                isValid = false
            } else {
                binding.tilPhone.error = null
            }
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Заполните поле"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Неверный формат email"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        return isValid
    }

    private fun subscribeToProfile() {
        viewModel.resultState.subscribeAsState(
            onLoading = { loading ->
                Log.d("EditProfileFragment", "Loading state: $loading")
            },
            onError = { error ->
                Log.e("EditProfileFragment", "Error: ${error.message}")
                Toast.makeText(requireContext(), "Ошибка обновления профиля", Toast.LENGTH_SHORT)
                    .show()
            },
            onSuccess = { success ->
                Log.d("EditProfileFragmentS", "Success: $success")
                Toast.makeText(requireContext(), "Профиль успешно обновлен", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_editProfileFragment_to_menuFragment)
            }
        )
    }

    private companion object {
        const val REQUEST_IMAGE_PICK = 100
    }
}
