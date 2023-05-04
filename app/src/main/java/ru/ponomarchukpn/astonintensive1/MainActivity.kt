package ru.ponomarchukpn.astonintensive1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.ponomarchukpn.astonintensive1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}
