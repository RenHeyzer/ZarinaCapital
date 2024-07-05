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
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.NUMBER_KEY
import com.ren.presentation.utils.USERNAME_KEY
import com.ren.presentation.utils.isErrorEnable
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
        getArgs()
    }

    private fun getArgs() {
        val args: EditProfileFragmentArgs by navArgs()
        args.profile?.let { profile ->
            setupSaveButton(profile)
        }
    }

    private fun setupSaveButton(profile: Profile) {
        binding.btnSave.setOnClickListener {
            val username = binding.etName.text.toString().trim()
            val prefix = binding.tilPhone.prefixText.toString().trim()
            val phone = binding.etNumber.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            viewModel.updateProfile(
                data = PUTProfile(
                    username = username.ifEmpty { profile.username },
                    prefix = prefix,
                    phone = phone.ifEmpty { profile.phone },
                    email = email.ifEmpty { profile.email },
                    avatar = selectedImageUri
                )
            )
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

    private fun subscribeToProfile() = with(binding) {
        val fields = mutableMapOf(
            USERNAME_KEY to tilName,
            EMAIL_KEY to tilEmail,
            NUMBER_KEY to tilPhone,
        )

        viewModel.resultState.subscribeAsState(
            onLoading = { loading ->
                Log.d("EditProfileFragment", "Loading state: $loading")
            },
            onError = { error ->
                error.errorList?.let { errorList ->
                    errorList.forEach {
                        fields[it.key]?.isErrorEnable(true, it.value)
                    }
                }
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
