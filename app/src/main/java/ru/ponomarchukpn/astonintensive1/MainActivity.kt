package ru.ponomarchukpn.astonintensive1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.ponomarchukpn.astonintensive1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreInputState(savedInstanceState)

        binding.btnEmployee.setOnClickListener {
            val user = processInput()
            user?.let {
                processRegistration(Registration.EmployeeReg(it))
                return@setOnClickListener
            }
            showRequiredFieldsToast()
        }

        binding.btnEmployer.setOnClickListener {
            val user = processInput()
            user?.let {
                processRegistration(Registration.EmployerReg(it))
                return@setOnClickListener
            }
            showRequiredFieldsToast()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val lastName = binding.etLastName.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val patronymic = binding.etPatronymic.text.toString()

        if (lastName.isNotBlank()) {
            outState.putString(KEY_LAST_NAME, lastName)
        }
        if (firstName.isNotBlank()) {
            outState.putString(KEY_FIRST_NAME, firstName)
        }
        if (patronymic.isNotBlank()) {
            outState.putString(KEY_PATRONYMIC, patronymic)
        }
    }

    private fun restoreInputState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            if (it.containsKey(KEY_LAST_NAME)) {
                binding.etLastName.setText(it.getString(KEY_LAST_NAME))
            }
            if (it.containsKey(KEY_FIRST_NAME)) {
                binding.etFirstName.setText(it.getString(KEY_FIRST_NAME))
            }
            if (it.containsKey(KEY_PATRONYMIC)) {
                binding.etPatronymic.setText(it.getString(KEY_PATRONYMIC))
            }
        }
    }

    private fun processInput(): User? {
        val lastName = binding.etLastName.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val patronymic = binding.etPatronymic.text.toString().ifBlank {
            null
        }

        return if (lastName.isNotBlank() && firstName.isNotBlank()) {
            User(lastName, firstName, patronymic)
        } else {
            null
        }
    }

    private fun processRegistration(reg: Registration) {
        RegistrationMessage().apply {
            message = registrationMessage(reg)
        }.also {
            showToast(it)
        }
    }

    private fun registrationMessage(reg: Registration): String = reg.run {
        when (reg) {
            is Registration.EmployeeReg -> reg.employeeMessage()
            is Registration.EmployerReg -> reg.employerMessage()
        }
    }

    private fun showToast(regMessage: RegistrationMessage) {
        regMessage.message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRequiredFieldsToast() {
        Toast.makeText(
            this,
            getString(R.string.msg_fields_required),
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        private const val KEY_LAST_NAME = "lastName"
        private const val KEY_FIRST_NAME = "firstName"
        private const val KEY_PATRONYMIC = "patronymic"
    }
}
